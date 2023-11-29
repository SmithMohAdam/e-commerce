package com.ecommerce.api.controllers;

import com.ecommerce.api.dto.ItemDto;
import com.ecommerce.api.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public ResponseEntity<List<ItemDto>> getAllItems(){
        return ResponseEntity.ok().body(itemService.getAllItems());
    }
    @PostMapping("/item")
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto){
        System.out.println(itemDto.toString());
        return ResponseEntity.ok().body(itemService.createItem(itemDto));
    }
    @GetMapping("/item/{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable Long id){
        return ResponseEntity.ok().body(itemService.getItem(id));
    }

    @PutMapping("/item/{id}/update")
    public ResponseEntity<ItemDto> updateItem(@PathVariable Long id , @RequestBody ItemDto itemDto){
        return ResponseEntity.ok().body(itemService.updateItem(id,itemDto));
    }
    @DeleteMapping("/item/{id}/delete")
     public ResponseEntity<String> deleteItem(@PathVariable Long id){
        return ResponseEntity.ok().body( itemService.deleteItem(id));
    }

    @GetMapping("/item/{id}/category")
    public ResponseEntity<List<ItemDto>> getItemByCategory(@PathVariable Long id){
        return ResponseEntity.ok().body(itemService.getItemsByCategory(id));
    }

}
