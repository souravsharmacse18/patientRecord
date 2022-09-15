package com.patientRecord.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Entity
@Table(name = "appointment")
@AllArgsConstructor
@NoArgsConstructor

public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	/*
	 * @Column(name="name") private String name;
	 * 
	 * @Column(name="email") private String email;
	 */
	@ManyToOne
	@JoinColumn(name = "userid")
	private User userid;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date appointmentDate;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm:ss")
	private Date appointmentTime;

}
