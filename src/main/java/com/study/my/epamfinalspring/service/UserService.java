package com.study.my.epamfinalspring.service;

import com.study.my.epamfinalspring.dto.UserTo;
import com.study.my.epamfinalspring.model.Role;
import com.study.my.epamfinalspring.model.User;
import com.study.my.epamfinalspring.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.study.my.epamfinalspring.util.UserUtil.userFromTo;

@Service
public class UserService implements UserDetailsService {

    private UserRepository repository;
    private BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository repository, @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll() {
        List<User> list = repository.findAll();
        return list.stream()
                .filter(user -> user.getRoles().contains(Role.ROLE_USER))
                .collect(Collectors.toList());
    }

 //   public User getById(int id) {
//        return repository.findById(id).orElse(new User());
//    }

    public User getByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    //TODO change this method return type to void, if user exist, throw exception
    public boolean create(User user) {
        if (user.getId() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Collections.singleton(Role.ROLE_USER));
            repository.save(user);
            return true;
        }
        return false;
    }

    //TODO change this method return type to void, if user not exist, throw exception
    public boolean update(User user) {
        if (user.getId() == null) {
            return false;
        }
        repository.save(user);
        return true;
    }

    public boolean createFromTo(UserTo userTo) {
        User user = userFromTo(userTo);
        return create(user);
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
