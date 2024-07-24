package com.cg.banking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.banking.dao.AccountRepository;
import com.cg.banking.dto.AccountDto;
import com.cg.banking.dto.CustomerDto;
import com.cg.banking.entity.Account;
import com.cg.banking.entity.Customer;
import com.cg.banking.exception.ResourceNotFoundException;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account createAccount(AccountDto accountDto) {

		CustomerDto customerDto = accountDto.getCustomerDto();

		Customer customer = new Customer();
		customer.setCustName(customerDto.getCustName());

		Account account = new Account();
		account.setAccountNumber(accountDto.getAccountNumber());
		account.setAccountType(accountDto.getAccountType());
		account.setBalance(accountDto.getBalance());
		account.setCustomer(customer);

		Account savedAccount = accountRepository.save(account);

		return savedAccount;
	}

	public Account getAccount(Integer id) {

		Optional<Account> optionalAccount = accountRepository.findById(id);

		if (!optionalAccount.isPresent()) {
			throw new ResourceNotFoundException("Account Not Found");
		}

		return optionalAccount.get();
	}
}
