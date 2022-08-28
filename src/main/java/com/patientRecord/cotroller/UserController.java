package com.patientRecord.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patientRecord.model.User;
import com.patientRecord.model.loginModel;
import com.patientRecord.service.AuthenticationService;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class UserController {
	@Autowired
	AuthenticationService authservice;
	
	@PostMapping("/signup")
	
	public ResponseEntity<User> usersignup(@RequestBody User user){
		User getUser = authservice.addNewUser(user);
		return ResponseEntity.ok(getUser);
	}
	
	@PostMapping("/login")
	
	public ResponseEntity<User> userlogin(@RequestBody loginModel loginuser){
		User login = authservice.loginUser(loginuser);
		return ResponseEntity.ok(login);
	}
	

}
