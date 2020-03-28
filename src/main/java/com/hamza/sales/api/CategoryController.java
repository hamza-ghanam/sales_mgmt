package com.hamza.sales.api;

import com.hamza.sales.exception.ResourceNotFoundException;
import com.hamza.sales.model.Category;
import com.hamza.sales.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    CategoryService service;

    @CrossOrigin(origins = "*")
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> list = service.getAllCategories();

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Category cat = service.getCategoryById(id);

        return new ResponseEntity<>(cat, new HttpHeaders(), HttpStatus.OK);
    }
}
