package com.om.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.om.entity.Emp;

public interface EmpRepo extends JpaRepository<Emp, Integer> {

}
