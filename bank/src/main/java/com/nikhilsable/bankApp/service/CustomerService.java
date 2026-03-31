package com.nikhilsable.bankApp.service;

import com.nikhilsable.bankApp.model.Customer;
import com.nikhilsable.bankApp.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getallCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setFirstName(updatedCustomer.getFirstName());
                    customer.setLastName(updatedCustomer.getLastName());
                    customer.setEmail(updatedCustomer.getEmail());
                    customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

    public void deleteCustomer(Long id) {
        if(!customerRepository.existsById(id)){
           throw new RuntimeException("Customer not found with id " + id);
        }
        customerRepository.deleteById(id);
    }
}
