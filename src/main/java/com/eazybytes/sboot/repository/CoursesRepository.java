package com.eazybytes.sboot.repository;

import com.eazybytes.sboot.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
    List<Courses> findByOrderByName();
    List<Courses> findByOrderByNameDesc();
}
