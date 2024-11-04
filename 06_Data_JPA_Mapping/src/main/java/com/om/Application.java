package com.om;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.om.entity.Emp;
import com.om.entity.Mobile;
import com.om.repository.EmpRepo;
import com.om.repository.MobileRepo;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	private EmpRepo empRepo;
	
	@Autowired
	private MobileRepo mobileRepo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Mobile mobile = new Mobile();
		mobile.setMobileName("POCCO M2");

		Emp emp = new Emp();
		emp.setEmpName("Avi");
		
		emp.setMobile(mobile);
		mobile.setEmp(emp);

		empRepo.save(emp);
		mobileRepo.save(mobile);
		
		System.out.println(emp);
		System.out.println("Succesfully Saved");
		
//		Emp emp = empRepo.getById(null)
		
		
		
	}

}
