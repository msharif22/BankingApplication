package com.cg.banking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.banking.entity.Transaction;
import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	//List<Transaction> findByAccountNumber(Long accountNumber);
	
	//@Query("SELECT t FROM Transaction t WHERE t.accountNumber = :accountNumber ORDER BY t.timestamp DESC")
	List<Transaction> findTop10ByAccountNumberOrderByTxnDateDesc(Long accountNumber);
}
