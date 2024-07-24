package com.cg.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferFundDto {

	private Long srcAccountNumber;
	private Long desAccountNumber;
	private Long amount;
}
