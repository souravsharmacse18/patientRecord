package com.patientRecord.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patientRecord.model.TransactionDetails;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionDetails, Long>{
	List<TransactionDetails> findByPaymentdate(Date paymentDate);
	List<TransactionDetails> findByPaymentdateGreaterThanEqualAndPaymentdateLessThanEqual(Date startDate, Date endDate);
	

}
