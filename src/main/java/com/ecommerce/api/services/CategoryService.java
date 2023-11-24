package com.ecommerce.api.services;

import com.ecommerce.api.dto.CategoryDto;
import com.ecommerce.api.exceptions.CategoryNotFoundException;
import com.ecommerce.api.models.Category;
import com.ecommerce.api.repositores.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public CategoryDto createCategory(CategoryDto catDto){
        Optional<Category> opCat = findCategory(catDto.getId());
        if(opCat.isPresent()){
            throw new CategoryNotFoundException("This category  Exist !");
        }else {
            Category cat = categoryRepository.save(dtoToCategory(catDto));
            return categoryToDto(cat);
        }
    }
    public CategoryDto getCategory(Long id){

        Optional<Category> opCat = findCategory(id);
        if(!opCat.isPresent()){
            throw new CategoryNotFoundException("This category Not Exist !");
        }else {
            return categoryToDto(opCat.get());
        }

    }
    public List<CategoryDto> getAllCategories(){
        List<Category> cats = categoryRepository.findAll();
        return cats.stream().map(p -> categoryToDto(p)).collect(Collectors.toList());
    }
    public CategoryDto updateCategory(Long id , CategoryDto catDto){

        Optional<Category> opCat = findCategory(id);
        if(!opCat.isPresent()){
            throw new CategoryNotFoundException("This category Not Exist !");
        }else {
            Category cat = opCat.get();
            if(catDto.getName()!=null){
                cat.setName(catDto.getName());
            }
            if(catDto.getDescription() !=null) {
                cat.setDescription(catDto.getDescription());
            }

            Category savedCat = categoryRepository.save(cat);
            return categoryToDto(savedCat);
        }

    }
    public String deleteCategory(Long id){
        Optional<Category> opCat = findCategory(id);
        if(!opCat.isPresent()){
            throw new CategoryNotFoundException("This category Not Exist !");
        }else {
            categoryRepository.delete(opCat.get());
            return "Category deleted successfully !";
        }
    }

    private Optional<Category> findCategory(Long id){
        return categoryRepository.findById(id);
    }

    private CategoryDto categoryToDto(Category cat){
        CategoryDto catDto = new CategoryDto();

        catDto.setId(cat.getId());
        catDto.setName(cat.getName());
        catDto.setDescription(cat.getDescription());
        catDto.setItems(cat.getItems());

        return catDto;
    }
    private Category dtoToCategory(CategoryDto catDto){
        Category cat = new Category();

        cat.setId(catDto.getId());
        cat.setName(catDto.getName());
        cat.setDescription(catDto.getDescription());
        cat.setItems(catDto.getItems());

        return cat;
    }

}
