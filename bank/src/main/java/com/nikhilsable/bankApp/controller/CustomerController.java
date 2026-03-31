package com.nikhilsable.bankApp.controller;

import com.nikhilsable.bankApp.model.Customer;
import com.nikhilsable.bankApp.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.status(201)
                .body(customerService.createCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getallCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,
                                                   @RequestBody Customer updatedCustomer){
        try {
            Customer updated = customerService.updateCustomer(id, updatedCustomer);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}