package com.nikhilsable.bankApp.repository;

import com.nikhilsable.bankApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
