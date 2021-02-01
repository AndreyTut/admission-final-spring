package com.study.my.finalspring.admission.repository;

import com.study.my.finalspring.admission.model.Diploma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomaRepository extends JpaRepository<Diploma, Integer> {
}
