package com.ecommerce.api.controllers;

import com.ecommerce.api.dto.CategoryDto;
import com.ecommerce.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto catDto){
        return ResponseEntity.ok().body(categoryService.createCategory(catDto));
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id){
        return ResponseEntity.ok().body(categoryService.getCategory(id));
    }
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }
    @PutMapping("/category/{id}/update")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id,@RequestBody CategoryDto catDto){
        return ResponseEntity.ok().body(categoryService.updateCategory(id,catDto));
    }
    @DeleteMapping("/category/{id}/delete")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }
}
