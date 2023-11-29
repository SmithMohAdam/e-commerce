package com.ecommerce.api.services;

import com.ecommerce.api.exceptions.OrderNotFoundException;
import com.ecommerce.api.models.Customer;
import com.ecommerce.api.models.Order;
import com.ecommerce.api.repositores.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }
}