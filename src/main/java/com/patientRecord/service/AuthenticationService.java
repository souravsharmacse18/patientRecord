package com.patientRecord.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patientRecord.model.User;
import com.patientRecord.model.loginModel;
import com.patientRecord.repo.UserRepo;

@Service
public class AuthenticationService {
	
	@Autowired
	UserRepo userRepo;
	
	public User addNewUser(User user) {
		return userRepo.save(user);
	}
	
	public User loginUser(loginModel login) {
		User user = userRepo.findByEmailId(login.getEmail());
		if(user.getPassword().equals(login.getPassword())) {
			return user;
		}
		else {
			return null;
		}
	}

}
