package com.ecommerce.api.services;

import com.ecommerce.api.dto.ItemDto;
import com.ecommerce.api.exceptions.CategoryNotFoundException;
import com.ecommerce.api.exceptions.ItemNotFoundException;
import com.ecommerce.api.models.Category;
import com.ecommerce.api.models.Item;
import com.ecommerce.api.repositores.CategoryRepository;
import com.ecommerce.api.repositores.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ItemDto createitem(ItemDto itemDto){

        Optional<Item> opItem = findItem(itemDto.getId());
        if(opItem.isPresent()){
            throw new ItemNotFoundException("This item already Exist !");
        }else{

            Optional<Category> catOp =findCategory(itemDto.getCategory().getId());
            if(catOp.isPresent()){
                itemDto.setCategory(catOp.get());
            }
            Item item =itemRepository.save(dtoToItem(itemDto));
            item.getCategory().setItems(null);
            return itemToDto(item);
        }
    }
    public ItemDto getItem(Long id){
        Optional<Item> opItem = findItem(id);
        if(!opItem.isPresent()){
            throw new ItemNotFoundException("This item not found !");
        }else{
            Item it =opItem.get();
            it.getCategory().setItems(null);
            return itemToDto(it);
        }
    }
    public List<ItemDto> getAllItems(){
        List<Item> items = itemRepository.findAll();
       // items.forEach(p -> p.getCategory().setItems(null));
        return items.stream().map(p ->itemToDto(p)).collect(Collectors.toList());
    }
    public ItemDto updateItem(Long id , ItemDto itemDto){

        Optional<Item> opItem = findItem(id);

        if(!opItem.isPresent()){
            throw new ItemNotFoundException("This item not found !");
        }else{
            Item item =opItem.get();
            if(itemDto.getName()!=null){
                item.setName(itemDto.getName());
            }
            if (itemDto.getPrice() !=0 ) {
                item.setPrice(itemDto.getPrice());
            }
            if (itemDto.getDescription()!=null) {
                item.setDescription(itemDto.getDescription());
            }
            if (itemDto.getCategory()!=null) {
                Optional<Category> catOp =findCategory(itemDto.getCategory().getId());
                if(catOp.isPresent()){
                    item.setCategory(catOp.get());
                }

            }
            Item it =itemRepository.save(item);
            it.getCategory().setItems(null);
            return itemToDto(it);
        }

    }
    public String deleteItem(Long id){

        if(!findItem(id).isPresent()){
            throw new ItemNotFoundException("This item not found !");
        }else{
            itemRepository.deleteById(id);
            return "Item deleted Successfully !";
        }
    }

    private Optional<Item> findItem(Long id){
        return itemRepository.findById(id);
    }
    private Optional<Category> findCategory(Long id){
        Optional<Category> cat =categoryRepository.findById(id);
        return cat;
    }

    private Item dtoToItem(ItemDto itemDto){
        Item item = new Item();

        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setDescription(itemDto.getDescription());
        item.setCategory(itemDto.getCategory());

        return item;
    }
    private ItemDto itemToDto(Item item){
        ItemDto itemDto = new ItemDto();

        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setPrice(item.getPrice());
        itemDto.setDescription(item.getDescription());
        itemDto.setCategory(item.getCategory());

        return itemDto;
    }
}
