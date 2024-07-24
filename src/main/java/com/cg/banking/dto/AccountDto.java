package com.cg.banking.dto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

	private Integer id;
	private String accountType;
	private Long accountNumber;
	@Nonnull
	private CustomerDto customerDto;
	private Long balance;
}
