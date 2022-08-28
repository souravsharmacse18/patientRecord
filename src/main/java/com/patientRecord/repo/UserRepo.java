package com.patientRecord.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patientRecord.model.User;



@Repository
public interface UserRepo extends JpaRepository<User,String>{
	
	User findByEmailId(String email);
}
