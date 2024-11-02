package com.om.repository;

import org.springframework.data.repository.CrudRepository;

import com.om.entities.Student;

public interface StudentRepo extends CrudRepository<Student, Integer> {

}
