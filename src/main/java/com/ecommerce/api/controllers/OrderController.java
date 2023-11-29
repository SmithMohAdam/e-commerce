package com.ecommerce.api.controllers;

import com.ecommerce.api.models.Customer;
import com.ecommerce.api.models.Order;
import com.ecommerce.api.services.CustomerService;
import com.ecommerce.api.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private  OrderService orderService;
    @Autowired
    private  CustomerService customerService;


    @PostMapping
    public Order saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/customers/{customerId}")
    public List<Order> getOrdersByCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return orderService.getOrdersByCustomer(customer);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        orderService.deleteOrder(order);
    }
}