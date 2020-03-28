/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamza.sales.service;

import com.hamza.sales.exception.ResourceNotFoundException;
import com.hamza.sales.model.Category;
import com.hamza.sales.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repo;

    public List<Category> getAllCategories() {
        List<Category> categories = repo.findAll();

        if (categories.size() > 0) {
            return categories;
        } else {
            return new ArrayList<>();
        }
    }
    
    public Category getCategoryById(Integer id) throws ResourceNotFoundException{
        Category category = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found :: ", "ID", id));
        
        return category;
    }
}
