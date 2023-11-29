package com.ecommerce.api.repositores;

import com.ecommerce.api.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer , Long> {
}
