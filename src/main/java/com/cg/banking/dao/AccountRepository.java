package com.cg.banking.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.banking.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	public Optional<Account> findByAccountNumber(Long accountNumber);
}
