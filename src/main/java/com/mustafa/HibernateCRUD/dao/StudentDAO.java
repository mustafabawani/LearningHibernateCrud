package com.mustafa.HibernateCRUD.dao;

import com.mustafa.HibernateCRUD.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student theStudent);
    Student findByID(Integer id);
    List<Student> findAll();
    List<Student> findByFirstName(String firstName);

    void update(Student theStudent);
    void delete(Integer id);
    int deleteAllStudent();
 }
