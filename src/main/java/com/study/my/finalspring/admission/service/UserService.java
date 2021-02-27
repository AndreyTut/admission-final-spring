package com.study.my.finalspring.admission.service;

import com.study.my.finalspring.admission.dto.UserTo;
import com.study.my.finalspring.admission.model.Faculty;
import com.study.my.finalspring.admission.model.Role;
import com.study.my.finalspring.admission.model.StudentMark;
import com.study.my.finalspring.admission.model.User;
import com.study.my.finalspring.admission.repository.FacultyRepository;
import com.study.my.finalspring.admission.repository.StudentMarkRepository;
import com.study.my.finalspring.admission.repository.UserRepository;
import com.study.my.finalspring.admission.util.UserUtil;
import com.study.my.finalspring.admission.util.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private UserRepository repository;
    private StudentMarkRepository studentMarkRepository;
    private FacultyRepository facultyRepository;
    private BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository repository, StudentMarkRepository studentMarkRepository, FacultyRepository facultyRepository, @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.studentMarkRepository = studentMarkRepository;
        this.facultyRepository = facultyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll() {
        List<User> list = repository.findAll();
        return list.stream()
                .filter(user -> user.getRoles().contains(Role.ROLE_USER))
                .collect(Collectors.toList());
    }

    public Page<User> getAll(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum - 1, 10, Sort.by("lastName"));
        return repository.findByRoles(Role.ROLE_USER, pageable);
    }

    public User getByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new NotFoundException("user with email: " + email + " not found"));
    }

    public boolean create(User user) {
        if (user.getId() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Collections.singleton(Role.ROLE_USER));
            repository.save(user);
            return true;
        }
        return false;
    }

    public boolean update(User user) {
        if (user.getId() == null) {
            return false;
        }
        repository.save(user);
        return true;
    }

    public boolean createFromTo(UserTo userTo) {
        User user = UserUtil.userFromTo(userTo);
        return create(user);
    }

    @Transactional
    public boolean update(UserTo userTo) {
        User old = repository.findById(userTo.getId()).orElseThrow(() -> new NotFoundException("User you try to update doesn't exist"));
        return repository.save(UserUtil.prepareToUpdate(userTo, old)) != null;
    }


    @Transactional
    public User setEnabled(int id, boolean enabled) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException("User with id=" + id + "not found"));
        user.setEnabled(enabled);
        repository.save(user);
        return user;
    }

    public boolean saveMarks(List<StudentMark> marks, String email) {
        User user = repository.findByEmail(email).orElseThrow(() -> new NotFoundException("user with email: " + email + " not found"));
        marks.forEach(studentMark -> studentMark.setUser(user));
        studentMarkRepository.saveAll(marks);
        return true;
    }

    public boolean addFaculty(int id, String email) {
        User user = repository.findByEmail(email).orElseThrow(() -> new NotFoundException("user with email: " + email + " not found"));
        Faculty faculty = facultyRepository.findById(id).orElseThrow(RuntimeException::new);
        List<Faculty> faculties = user.getFaculties() == null ? new ArrayList<>() : user.getFaculties();
        if (!faculties.contains(faculty)) {
            faculties.add(faculty);
        }
        user.setFaculties(faculties);
        repository.save(user);
        return true;
    }


    @Transactional
    public User saveDiplomaImage(MultipartFile image, String email) {
        byte[] imageBytes;
        try {
            imageBytes = image.getBytes();
        } catch (IOException e) {
            log.error("Error occurred while converting file into bytes");
            throw new RuntimeException("Error saving image to database");
        }
        User user = repository.findByEmail(email).orElseThrow(() -> new NotFoundException("user with email: " + email + " not found"));
        user.setDiplomImage(imageBytes);
        return user;
    }

    @Transactional
    public User addToReport(String email, int facultyId) {
        User user = repository.findByEmail(email).orElseThrow(RuntimeException::new);
        user.setFaculty(facultyRepository.findById(facultyId).orElseThrow(RuntimeException::new));
        repository.save(user);
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getByEmail(email);
        if (user == null) {
            throw new RuntimeException("user not found: " + email);
        }
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getRoles();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getEmail();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return user.isEnabled();
            }
        };
    }

}
