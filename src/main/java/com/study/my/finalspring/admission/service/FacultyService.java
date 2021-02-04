package com.study.my.finalspring.admission.service;

import com.study.my.finalspring.admission.model.Faculty;
import com.study.my.finalspring.admission.model.Subject;
import com.study.my.finalspring.admission.repository.FacultyRepository;
import com.study.my.finalspring.admission.repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FacultyService {
    private FacultyRepository facultyRepository;
    private SubjectRepository subjectRepository;

    public FacultyService(FacultyRepository facultyRepository, SubjectRepository subjectRepository) {
        this.facultyRepository = facultyRepository;
        this.subjectRepository = subjectRepository;
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
}
