package com.patientRecord.cotroller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patientRecord.model.Appointment;
import com.patientRecord.model.User;
import com.patientRecord.repo.AppointmentRepo;
import com.patientRecord.repo.UserRepo;

@RestController
@CrossOrigin(allowedHeaders = "*" , origins = "*")
public class AppointmentController {
	@Autowired
	AppointmentRepo appointmentRepo;
	@Autowired
	UserRepo userRepo;
	/******************* Book Appointment API ********************/
	@RequestMapping(value="/book-slot", method = {RequestMethod.GET, RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Appointment> bookAppointmentSlot(HttpServletRequest request,
			@RequestParam("email") String email,
			@RequestParam("appointmentdate") String appointDate,
			@RequestParam("appointmenttime") String appointmentTime){
		try {
			System.out.println(email+" / "+appointDate+" / "+appointmentTime);
			if(email != null || email != "") {
				User getUserById = userRepo.findByEmailId(email);
				System.out.println("User data >> "+getUserById.getEmailId()+" || and "+getUserById.getId());
				Appointment appointment = new Appointment();
				if(appointDate != null || appointDate != "") {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					Date aptDate = sdf.parse(appointDate);
					System.out.println("Appointment Date : "+aptDate);
					appointment.setAppointmentDate(aptDate);
				}
				if(appointmentTime != null || appointmentTime != "") {
					SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss"); 
					Date aptTime = sdf2.parse(appointmentTime);
					System.out.println("System local time || "+aptTime);
					appointment.setAppointmentTime(aptTime);
				  
				  }
				 
				
				appointment.setUserid(getUserById);
				
				Appointment apt = appointmentRepo.save(appointment);
				
				return ResponseEntity.ok(apt);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
