package com.study.my.finalspring.admission.repository;

import com.study.my.finalspring.admission.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Optional<Subject> findByNameEnOrNameUa(String nameEn, String nameUa);
}
