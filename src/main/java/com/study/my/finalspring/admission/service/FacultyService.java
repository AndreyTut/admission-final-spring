package com.study.my.finalspring.admission.service;

import com.study.my.finalspring.admission.dto.FacultyMarksTo;
import com.study.my.finalspring.admission.model.Faculty;
import com.study.my.finalspring.admission.model.StudentMark;
import com.study.my.finalspring.admission.model.Subject;
import com.study.my.finalspring.admission.model.User;
import com.study.my.finalspring.admission.repository.FacultyRepository;
import com.study.my.finalspring.admission.repository.SubjectRepository;
import com.study.my.finalspring.admission.repository.UserRepository;
import com.study.my.finalspring.admission.util.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.study.my.finalspring.admission.util.UserUtil.setUserRatingForFaculty;

@Service
@Slf4j
public class FacultyService {
    private FacultyRepository facultyRepository;
    private SubjectRepository subjectRepository;
    private UserRepository userRepository;

    public FacultyService(FacultyRepository facultyRepository, SubjectRepository subjectRepository, UserRepository userRepository) {
        this.facultyRepository = facultyRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public boolean delete(int id) {
        facultyRepository.deleteById(id);
        return true;
    }

    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    public Faculty getById(int id) {
        return facultyRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Subject> getExistingSubjects(Faculty faculty) {
        List<Subject> result = new ArrayList<>();
        faculty.getSubjects()
                .stream()
                .map(subject -> subject.getNameEn() != null ? subject.getNameEn() : subject.getNameUa())
                .map(s -> subjectRepository.findByNameEnOrNameUa(s, s))
                .forEach(opt -> result.add(opt.orElseThrow(() -> new NotFoundException("Subject doesn't exist"))));
        return result;
    }

    public boolean save(Faculty faculty) {
        log.info("saving faculty: {}", faculty);
        return facultyRepository.save(faculty) != null;
    }

    public FacultyMarksTo getFacultyMarks(String email, int facultyId) {
        FacultyMarksTo facultyMarks = new FacultyMarksTo();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("user with email: " + email + " not found"));
        List<StudentMark> studentMarks = user.getMarks();
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(() -> new NotFoundException("Faculty with id " + facultyId + "not found"));

        if (studentMarks == null) {
            faculty.getSubjects().forEach(subject -> facultyMarks.getMarks().add(new StudentMark(subject)));
            return facultyMarks;
        }

        for (Subject subject : faculty.getSubjects()) {
            boolean found = false;
            for (StudentMark mark : studentMarks) {
                if (mark.getSubject() == subject) {
                    facultyMarks.getMarks().add(mark);
                    found = true;
                    break;
                }
            }
            if (!found) {
                facultyMarks.getMarks().add(new StudentMark(subject));
            }
        }
        return facultyMarks;
    }

    @Transactional
    public boolean finalyzeReport(int facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(() -> new NotFoundException("Faculty with id " + facultyId + "not found"));
        List<User> students = faculty.getStudents();
        students = students.stream()
                .filter(User::isEnabled)
                .collect(Collectors.toList());

        students.forEach(s -> setUserRatingForFaculty(s, faculty));
        students.sort((s1, s2) -> s2.getRating() - s1.getRating());

        AtomicInteger counter = new AtomicInteger(0);
        students.forEach(student -> {
            int num = counter.incrementAndGet();
            if (num <= faculty.getVacancyBudge()) {
                student.setStatus(1);
            }
            if (num > faculty.getVacancyBudge() && num <= faculty.getVacancyContr() + faculty.getVacancyBudge()) {
                student.setStatus(2);
            }
            if (num > faculty.getVacancyContr() + faculty.getVacancyBudge()) {
                student.setStatus(3);
            }
        });
        faculty.setFinalized(true);
        return true;
    }
}
