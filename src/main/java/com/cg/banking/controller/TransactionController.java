package com.cg.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.banking.dto.DepositDto;
import com.cg.banking.dto.ShowBalanceDto;
import com.cg.banking.dto.TransferFundDto;
import com.cg.banking.dto.WithDrawDto;
import com.cg.banking.entity.Account;
import com.cg.banking.entity.Transaction;
import com.cg.banking.response.BalanceResponseDto;
import com.cg.banking.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/showBalance/{accountNumber}")
	public ResponseEntity<?> showBalance(@PathVariable Long accountNumber) {

		if (accountNumber == null || accountNumber.toString().length() < 12) {
			return new ResponseEntity<>("please provide valid account number", HttpStatus.BAD_REQUEST);
		}

		Long balance = transactionService.showBalance(accountNumber);
		
		BalanceResponseDto balanceResponseDto = new BalanceResponseDto(accountNumber,balance);
		

		return new ResponseEntity<>(balanceResponseDto, HttpStatus.OK);
	}

	@PostMapping("/withdraw")
	public ResponseEntity<?> withDraw(@RequestBody WithDrawDto withDrawDto) {

		if (withDrawDto == null) {
			return new ResponseEntity<>("please provide some request", HttpStatus.BAD_REQUEST);
		}
		if (withDrawDto.getAccountNumber() == null || withDrawDto.getAccountNumber().toString().length() < 12) {
			return new ResponseEntity<>("please provide valid account number", HttpStatus.BAD_REQUEST);
		}
		if (withDrawDto.getAmount() <= 0) {
			return new ResponseEntity<>("Please provide valid amount", HttpStatus.BAD_REQUEST);
		}

		Account account = transactionService.withDraw(withDrawDto);

		if (account == null) {
			return new ResponseEntity<>("contact admin", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@PostMapping("/deposit")
	public ResponseEntity<?> deposit(@RequestBody DepositDto depositDto) {

		if (depositDto == null) {
			return new ResponseEntity<>("please provide valid request", HttpStatus.BAD_REQUEST);
		}
		if (depositDto.getAccountNumber() == null || depositDto.getAccountNumber().toString().length() < 12) {
			return new ResponseEntity<>("please provide valid account number", HttpStatus.BAD_REQUEST);
		}
		if (depositDto.getAmount() <= 0) {
			return new ResponseEntity<>("Please provide valid amount", HttpStatus.BAD_REQUEST);
		}

		Account account = transactionService.deposit(depositDto);

		if (account == null) {
			return new ResponseEntity<>("contact admin", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(account, HttpStatus.OK);

	}

	@PostMapping("/transferFunds")
	public ResponseEntity<?> transferFund(@RequestBody TransferFundDto transferFundDto) {

		if (transferFundDto == null) {
			return new ResponseEntity<>("please provide valid request", HttpStatus.BAD_REQUEST);
		}
		if (transferFundDto.getSrcAccountNumber() == null
				|| transferFundDto.getSrcAccountNumber().toString().length() < 12) {
			return new ResponseEntity<>("please provide valid source account number", HttpStatus.BAD_REQUEST);
		}

		if (transferFundDto.getDesAccountNumber() == null
				|| transferFundDto.getDesAccountNumber().toString().length() < 12) {
			return new ResponseEntity<>("please provide valid Destination account number", HttpStatus.BAD_REQUEST);
		}

		if (transferFundDto.getAmount() <= 0) {
			return new ResponseEntity<>("Please provide some amount to transfer", HttpStatus.BAD_REQUEST);
		}

		Account account = transactionService.transferFund(transferFundDto);
		if (account == null) {
			return new ResponseEntity<>("contact admin", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(account, HttpStatus.OK);

	}
	
	@GetMapping("/last10Transaction/{accountNumber}")
	public ResponseEntity<?> lastTenTrns(@PathVariable Long accountNumber) {

		List<Transaction> lastTenTrns = transactionService.lastTenTrns(accountNumber);
		return new ResponseEntity<>(lastTenTrns,HttpStatus.OK);
	}
	
}
