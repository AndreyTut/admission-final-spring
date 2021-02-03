package com.study.my.finalspring.admission.repository;

import com.study.my.finalspring.admission.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
}
