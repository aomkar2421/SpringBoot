package com.om;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.om.entities.Student;
import com.om.repository.StudentRepo;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		
		StudentRepo stdRepo = context.getBean(StudentRepo.class);
		
//		//CREATE OPERATION
//		Student st = new Student();
//		st.setName("avi");
//		st.setAddress("rajewadi");
//		stdRepo.save(st);
//		
//		Student st1 = new Student();
//		st1.setName("demo");
//		st1.setAddress("demo");
//		stdRepo.save(st1);
//		
//		System.out.println("User Saved");
//		
//		
//		//READ ALL
//		List<Student> list = (List<Student>) stdRepo.findAll();
//		
//		for (Student st : list) {
//			System.out.println(st);
//		}
//		System.out.println("Read All Completed");
//		
//		
//		//READ ONE
//		Student st = stdRepo.findById(2).get();
//		System.out.println("Read One Completed");
//		
//		
//		//UPDATE OPERATION
//		Student stu = stdRepo.findById(5).get();
//		stu.setName("DEMO UP");
//		stu.setAddress("DEMO UP");
//		stdRepo.save(stu);
//		System.out.println("Update Operation Completed");
//		
//		
//		//DELETE OPERATION
//		stdRepo.deleteById(5);
//		System.out.println("Delete Operation Completed");
//		
//		Student stu = stdRepo.findById(6).get();
//		stdRepo.delete(stu);
//		System.out.println("Delete Operation Completed");
		
		
	}

}
