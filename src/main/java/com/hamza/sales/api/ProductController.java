package com.hamza.sales.api;

import com.hamza.sales.exception.ResourceNotFoundException;
import com.hamza.sales.model.Product;
import com.hamza.sales.service.CategoryService;
import com.hamza.sales.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    ProductService prodService;

    @CrossOrigin(origins = "*")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> list = prodService.getAllProducts();

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") Integer id) {
        Product prod = prodService.getProduct(id);

        return new ResponseEntity<>(prod, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws ResourceNotFoundException {
        Product prod = prodService.createProduct(product);

        return new ResponseEntity<>(prod, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable(value = "id") Integer id,
            @RequestBody Product product)
            throws ResourceNotFoundException {

        Product prod = prodService.updateProduct(id, product);

        return new ResponseEntity<>(prod, new HttpHeaders(), HttpStatus.OK);
    }
}
