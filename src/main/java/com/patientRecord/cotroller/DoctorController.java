package com.patientRecord.cotroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patientRecord.model.Doctor;
import com.patientRecord.repo.DoctorRepository;

@RestController
public class DoctorController {
	
	@Autowired
	private DoctorRepository doctorRepo;
	@GetMapping(value = "/get/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	
	public ResponseEntity<?> getListOfDoctor(HttpServletRequest request){
		List<Doctor> getList = doctorRepo.findAll();
		if(getList.size() > 0) {
			return ResponseEntity.ok(getList);
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}

}
