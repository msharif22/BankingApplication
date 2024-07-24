package com.cg.banking;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cg.banking.dao.AccountRepository;
import com.cg.banking.dao.CustomerRepository;
import com.cg.banking.dao.TransactionRepository;
import com.cg.banking.entity.Account;
import com.cg.banking.entity.Customer;
import com.cg.banking.entity.Transaction;

@SpringBootApplication
public class BankingApplication implements CommandLineRunner {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Customer customer1 = new Customer("Sharif");
		Customer customer2 = new Customer("Maya");
		
		customerRepository.save(customer1);
		customerRepository.save(customer2);
		
		Account account1 = new Account("saving", 111122223333L, customer1, 500L);
		
		Transaction trxTransaction1 = new Transaction(account1.getAccountNumber(), LocalDateTime.now(),
				account1.getBalance(), account1.getBalance(), "CR", "New Account");
		
		Account account2 = new Account("saving", 111133223322L, customer2, 500L);
		
		Transaction trxTransaction2 = new Transaction(account2.getAccountNumber(), LocalDateTime.now(),
				account2.getBalance(), account2.getBalance(), "CR", "New Account");
		
		accountRepository.save(account1);
		accountRepository.save(account2);

		transactionRepository.save(trxTransaction1);
		transactionRepository.save(trxTransaction2);

	
	}

}
