package com.study.my.finalspring.admission.repository;

import com.study.my.finalspring.admission.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
