package com.ecommerce.api.controllers;

import com.ecommerce.api.models.Customer;
import com.ecommerce.api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private  CustomerService customerService;


    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        customerService.deleteCustomer(customer);
    }
}