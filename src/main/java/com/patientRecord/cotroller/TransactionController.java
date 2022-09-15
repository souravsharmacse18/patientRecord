package com.patientRecord.cotroller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.patientRecord.model.TransactionDetails;
import com.patientRecord.repo.TransactionRepo;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class TransactionController {
	private final Logger logger = LoggerFactory.getLogger(TransactionDetails.class);
	@Autowired
	TransactionRepo transactionrepo;
	
	@RequestMapping(value = "/api/dailyrevenue", method = {RequestMethod.GET, RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Double> getDailyRevenue(HttpServletRequest request){
		logger.info("Get daily revenue function is calling");
		try {
			double sum = 0;
			LocalDate localDate = LocalDate.now();
			System.out.println("Local date : "+localDate);
			Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			System.out.println("Date : "+date);
			List<TransactionDetails> list = transactionrepo.findByPaymentdate(date);
			System.out.println("Daily data list size : "+list.size());
			if(list.size()>0) {
				for(int i = 0; i < list.size(); i++) {
					double am = list.get(i).getAmount();
					sum = sum + am;
				}
				System.out.println("Total amount : "+sum);
				return ResponseEntity.ok(sum);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/weeklyrevenue", method = {RequestMethod.GET, RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Double> getWeeklyRevenue(HttpServletRequest request){
		logger.info("Weekly data function is calling !!");
		try {
			double sum = 0;
			LocalDate localDate = LocalDate.now();
			System.out.println("Local date : " + localDate);
			Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			LocalDate after7Days = localDate.plusDays(7);
			System.out.println("Date from now after 7 days : "+after7Days);
			Date after7daydate = Date.from(after7Days.atStartOfDay(ZoneId.systemDefault()).toInstant());
			System.out.println("Date :" + after7daydate);
			
			List<TransactionDetails> weeklyList = transactionrepo.findByPaymentdateGreaterThanEqualAndPaymentdateLessThanEqual(currentDate,after7daydate);
			System.out.println("Weekly data list size: "+weeklyList);
			if(weeklyList.size() > 0) {
				for(int i = 0; i < weeklyList.size(); i++) {
					sum = sum + weeklyList.get(i).getAmount();
				}
				System.out.println("Weekly generated revenue : "+sum);
				return ResponseEntity.ok(sum);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@RequestMapping(value = "/api/monthlyrevenue", method = {RequestMethod.GET, RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Double> getMonthlyRevenue(HttpServletRequest request){
		logger.info("Monthly data function is calling !!");
		try {
			double sum = 0;
			org.joda.time.LocalDate beginMonth = new org.joda.time.LocalDate().withDayOfMonth(1);
			System.out.println(beginMonth);
			Date monthBeginDate = beginMonth.toDateTimeAtStartOfDay().toDate();
			System.out.println("Date of starting month : "+monthBeginDate);
			
			org.joda.time.LocalDate monthEnd = new org.joda.time.LocalDate().plusMonths(1)
					.withDayOfMonth(1).minusDays(1);
			System.out.println(monthEnd);
			Date monthEndDate = monthEnd.toDateTimeAtStartOfDay().toDate();
			System.out.println("Last date of month : "+monthEndDate);
			
			List<TransactionDetails> monthlyList = transactionrepo.findByPaymentdateGreaterThanEqualAndPaymentdateLessThanEqual(monthBeginDate,monthEndDate);
			System.out.println("Monthly data list size: "+monthlyList);
			if(monthlyList.size() > 0) {
				for(int i = 0; i < monthlyList.size(); i++) {
					sum = sum + monthlyList.get(i).getAmount();
				}
				System.out.println("Monthly generated revenue : "+sum);
				return ResponseEntity.ok(sum);
			}	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/api/yearlyrevenue", method = {RequestMethod.GET , RequestMethod.POST} , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Double> getYearlyRevenue(HttpServletRequest request){
		logger.info("Yearly data function is calling !!");
		try {
			
			Calendar calender = Calendar.getInstance();
			Date today = calender.getTime();
			calender.add(Calendar.YEAR, 1);
			Date nextYear = calender.getTime();
			System.out.println("Current year : "+today);
			System.out.println("next year : "+nextYear);
			double sum = 0;
			List<TransactionDetails> annualList = transactionrepo.findByPaymentdateGreaterThanEqualAndPaymentdateLessThanEqual(today, nextYear);
			System.out.println("List size : "+annualList.size());
			for(int i = 0; i < annualList.size(); i++) {
				sum = sum + annualList.get(i).getAmount();
			}
			System.out.println("Annual revenue sum : "+sum);
			return ResponseEntity.ok(sum);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}