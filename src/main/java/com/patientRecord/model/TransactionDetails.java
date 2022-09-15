package com.patientRecord.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "transaction_details")
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class TransactionDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date paymentdate;
	
	private String cardholdername;
	private String cardnumber;
	private String cardtype;
	private int cvv;
	private String expiry;
	private double amount;
	

}
