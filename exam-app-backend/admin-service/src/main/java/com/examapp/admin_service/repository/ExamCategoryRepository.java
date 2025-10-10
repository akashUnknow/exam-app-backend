package com.examapp.admin_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examapp.admin_service.model.ExamCategory;

@Repository
public interface ExamCategoryRepository extends JpaRepository<ExamCategory, Long> {
}