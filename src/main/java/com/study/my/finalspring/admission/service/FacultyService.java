package com.study.my.finalspring.admission.service;

import com.study.my.finalspring.admission.dto.FacultyMarksTo;
import com.study.my.finalspring.admission.model.Faculty;
import com.study.my.finalspring.admission.model.StudentMark;
import com.study.my.finalspring.admission.model.Subject;
import com.study.my.finalspring.admission.model.User;
import com.study.my.finalspring.admission.repository.FacultyRepository;
import com.study.my.finalspring.admission.repository.SubjectRepository;
import com.study.my.finalspring.admission.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    //TODO write own exception classes and use them instead of runtime exceptions
    public Faculty getById(int id) {
        return facultyRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Subject> getExistingSubjects(Faculty faculty) {
        List<Subject> result = new ArrayList<>();
        faculty.getSubjects()
                .stream()
                .map(subject -> subject.getNameEn() != null ? subject.getNameEn() : subject.getNameUa())
                .map(s -> subjectRepository.findByNameEnOrNameUa(s, s))
                .forEach(opt -> result.add(opt.orElseThrow(RuntimeException::new)));
        return result;
    }

    public boolean save(Faculty faculty) {
        log.info("saving faculty: {}", faculty);
        return facultyRepository.save(faculty) != null;
    }

    //TODO write own exception classes and use them instead of runtime exceptions
    public FacultyMarksTo getFacultyMarks(String email, int facultyId) {
        FacultyMarksTo facultyMarks = new FacultyMarksTo();
        User user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        List<StudentMark> studentMarks = user.getMarks();
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(RuntimeException::new);

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
}
