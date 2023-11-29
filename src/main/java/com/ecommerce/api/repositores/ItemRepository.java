package com.ecommerce.api.repositores;

import com.ecommerce.api.models.Category;
import com.ecommerce.api.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByCategory(Category category);

}
