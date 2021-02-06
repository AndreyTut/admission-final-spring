package com.study.my.finalspring.admission.repository;

import com.study.my.finalspring.admission.model.StudentMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentMarkRepository extends JpaRepository<StudentMark, Integer> {
}
