package com.cg.banking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.banking.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
