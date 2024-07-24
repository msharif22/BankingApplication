package com.cg.banking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.banking.dao.CustomerRepository;
import com.cg.banking.dto.AccountDto;
import com.cg.banking.dto.CustomerDto;
import com.cg.banking.entity.Account;
import com.cg.banking.entity.Customer;
import com.cg.banking.exception.ResourceNotFoundException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer createCustomer(CustomerDto customerDto) {

		AccountDto accountDto = customerDto.getAccountDto();

		Account account = new Account();
		account.setAccountNumber(accountDto.getAccountNumber());
		account.setAccountType(accountDto.getAccountType());
		account.setBalance(accountDto.getBalance());

		Customer customer = new Customer();

		customer.setCustName(customerDto.getCustName());
		customer.setAccount(account);

		Customer savedCustomer = customerRepository.save(customer);

		return savedCustomer;
	}

	public Customer getCustomer(Integer id) {

		Optional<Customer> optionalCustomer = customerRepository.findById(id);

		if (!optionalCustomer.isPresent()) {
			throw new ResourceNotFoundException("Resource Not Found");
		}
		return optionalCustomer.get();
	}

}
