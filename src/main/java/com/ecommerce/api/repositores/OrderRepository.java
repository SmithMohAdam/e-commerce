package com.ecommerce.api.repositores;

import com.ecommerce.api.models.Customer;
import com.ecommerce.api.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order ,Long> {
    List<Order>  findByCustomer(Customer customer);
}
