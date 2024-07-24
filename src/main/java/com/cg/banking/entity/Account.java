package com.cg.banking.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String accountType;
	
	@Column(name="account_number", unique = true)
	private Long accountNumber;

    @OneToOne()
    @JoinColumn(name = "customer_id", unique = true)
	private Customer customer;
	
	private Long balance;
	
	public Account(String accountType, Long accountNumber, Customer customer, Long balance) {
		
		this.accountType=accountType;
		this.accountNumber=accountNumber;
		this.customer=customer;
		this.balance=balance;
	}
}
