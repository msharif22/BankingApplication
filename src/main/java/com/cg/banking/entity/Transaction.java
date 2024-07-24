package com.cg.banking.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer txdId;
	private Long accountNumber;
	private LocalDateTime txnDate;
	private Long amount;
	private Long availabeAmount;
	private String txnType;
	private String txnDesc;
	public Transaction(Long accountNumber, LocalDateTime txnDate, Long amount, Long availabeAmount, String txnType,
			String txnDesc) {
		super();
		this.accountNumber = accountNumber;
		this.txnDate = txnDate;
		this.amount = amount;
		this.availabeAmount = availabeAmount;
		this.txnType = txnType;
		this.txnDesc = txnDesc;
	}
	
	
}
