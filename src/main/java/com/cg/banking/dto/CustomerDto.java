package com.cg.banking.dto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

	private Integer custId;
	private String custName;
	@Nonnull
	private AccountDto accountDto;
}
