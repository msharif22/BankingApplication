package com.cg.banking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.banking.dao.AccountRepository;
import com.cg.banking.dao.TransactionRepository;
import com.cg.banking.dto.DepositDto;
import com.cg.banking.dto.ShowBalanceDto;
import com.cg.banking.dto.TransferFundDto;
import com.cg.banking.dto.WithDrawDto;
import com.cg.banking.entity.Account;
import com.cg.banking.entity.Transaction;
import com.cg.banking.exception.InsufficientBalanceException;
import com.cg.banking.exception.ResourceNotFoundException;

@Service
public class TransactionService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	public Long showBalance(Long accountNumber) {

		Optional<Account> optionalAccountNumber = accountRepository.findByAccountNumber(accountNumber);

		if (!optionalAccountNumber.isPresent()) {
			throw new ResourceNotFoundException("Account Not Found");
		}

		Account account = optionalAccountNumber.get();

		return account.getBalance();

	}

	@Transactional
	public Account withDraw(WithDrawDto withDrawDto) {

		Optional<Account> optionalAccountNumber = accountRepository.findByAccountNumber(withDrawDto.getAccountNumber());

		if (!optionalAccountNumber.isPresent()) {
			throw new ResourceNotFoundException("Account Not Found");
		}

		Account account = optionalAccountNumber.get();

		if (account.getBalance() == null || account.getBalance() <= 0) {
			throw new InsufficientBalanceException("Insufficient Balance Exception");
		}
		if (withDrawDto.getAmount() > account.getBalance()) {
			throw new InsufficientBalanceException("Insufficient Balance Exception");
		}

		account.setBalance(account.getBalance() - withDrawDto.getAmount());

		Account savedAccount = accountRepository.save(account);

		Transaction trxTransaction = new Transaction(account.getAccountNumber(), LocalDateTime.now(),
				withDrawDto.getAmount(), account.getBalance(), "DR", "withdraw");

		transactionRepository.save(trxTransaction);

		return savedAccount;
	}

	@Transactional
	public Account deposit(DepositDto depositDto) {

		Optional<Account> optionalAccountNumber = accountRepository.findByAccountNumber(depositDto.getAccountNumber());

		if (!optionalAccountNumber.isPresent()) {
			throw new ResourceNotFoundException("Account Not Found");
		}

		Account account = optionalAccountNumber.get();

		account.setBalance(account.getBalance() + depositDto.getAmount());

		Account savedAccount = accountRepository.save(account);

		Transaction trxTransaction = new Transaction(account.getAccountNumber(), LocalDateTime.now(),
				depositDto.getAmount(), account.getBalance(), "CR", "deposit");

		transactionRepository.save(trxTransaction);

		return savedAccount;

	}

	@Transactional
	public Account transferFund(TransferFundDto transferFundDto) {

		Optional<Account> optionalSrcAccountNumber = accountRepository
				.findByAccountNumber(transferFundDto.getSrcAccountNumber());

		if (!optionalSrcAccountNumber.isPresent()) {
			throw new ResourceNotFoundException("Source Account Not Found");
		}

		Account srcAccount = optionalSrcAccountNumber.get();

		if (srcAccount.getBalance() == null || srcAccount.getBalance() <= 0) {
			throw new InsufficientBalanceException("Insufficient Balance Exception");
		}
		if (transferFundDto.getAmount() > srcAccount.getBalance()) {
			throw new InsufficientBalanceException("Insufficient Balance Exception");
		}

		Optional<Account> optionalDesAccountNumber = accountRepository
				.findByAccountNumber(transferFundDto.getDesAccountNumber());

		if (!optionalDesAccountNumber.isPresent()) {
			throw new ResourceNotFoundException("Destination Account Not Found");
		}

		Account desAccount = optionalDesAccountNumber.get();

		srcAccount.setBalance(srcAccount.getBalance() - transferFundDto.getAmount());

		Account savedSrcAccount = accountRepository.save(srcAccount);

		Transaction srcTrxTransaction = new Transaction(srcAccount.getAccountNumber(), LocalDateTime.now(),
				transferFundDto.getAmount(), srcAccount.getBalance(), "DR", "withdraw");

		desAccount.setBalance(desAccount.getBalance() + transferFundDto.getAmount());

		accountRepository.save(desAccount);

		Transaction desTrxTransaction = new Transaction(desAccount.getAccountNumber(), LocalDateTime.now(),
				transferFundDto.getAmount(), desAccount.getBalance(), "CR", "deposit");

		transactionRepository.save(srcTrxTransaction);
		transactionRepository.save(desTrxTransaction);

		return savedSrcAccount;
	}

	public List<Transaction> lastTenTrns(Long accountNumber) {
		
		Optional<Account> optionalAccountNumber = accountRepository.findByAccountNumber(accountNumber);

		if (!optionalAccountNumber.isPresent()) {
			throw new ResourceNotFoundException("Account Not Found");
		}

		List<Transaction> last10Transaction = transactionRepository
				.findTop10ByAccountNumberOrderByTxnDateDesc(accountNumber);

		return last10Transaction;

	}

}
