package com.patientRecord.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patientRecord.model.Appointment;
import com.patientRecord.model.User;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long>{
	Appointment findById(final long id);
	Appointment findByUserid(User userid);

}
