package com.patientRecord.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patientRecord.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String>{
	
}
