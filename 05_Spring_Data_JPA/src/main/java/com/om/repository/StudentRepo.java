package com.om.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.om.entities.Student;

//public interface StudentRepo extends CrudRepository<Student, Integer> {
public interface StudentRepo extends JpaRepository<Student, Integer> {
	
	List<Student> findByName(String name);
	
	List<Student> findByAddress(String address);
	
	List<Student> findByNameAndAddress(String name, String address);
	
	List<Student> findByNameOrAddress(String name, String address);
	
	//Custom Query 
	@Query("select u from Student u where u.name=?1 or u.address=?2")
	List<Student> customQueryForFindingStudentUsingXY(String name, String address);
	//custom query name can be anything

}
