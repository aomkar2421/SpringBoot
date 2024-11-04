package com.om.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.om.entity.Employee;

public interface EmpRepo extends JpaRepository<Employee, Integer>{
	
	public Employee findByEmail(String email);

}
