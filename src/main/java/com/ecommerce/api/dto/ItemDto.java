package com.ecommerce.api.dto;

import com.ecommerce.api.models.Category;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDto {

    private Long id;
    private String name;
    private double price;
    private String description;
    private Category category;

}
