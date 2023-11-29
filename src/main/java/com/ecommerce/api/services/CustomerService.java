package com.ecommerce.api.services;

import com.ecommerce.api.exceptions.OrderNotFoundException;
import com.ecommerce.api.models.Customer;
import com.ecommerce.api.repositores.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        // Perform any necessary business logic/validation before saving
        // ...
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new OrderNotFoundException("Customer not found with ID: " + customerId));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }
}