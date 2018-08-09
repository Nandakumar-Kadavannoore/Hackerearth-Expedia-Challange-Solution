package com.expedia.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expedia.invoice.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
