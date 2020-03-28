package com.hamza.sales.service;

import com.hamza.sales.exception.ResourceNotFoundException;
import com.hamza.sales.model.Category;
import com.hamza.sales.model.Product;
import com.hamza.sales.repository.CategoryRepository;
import com.hamza.sales.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository prodRepo;
    @Autowired
    CategoryRepository catRepo;

    public List<Product> getAllProducts() {
        List<Product> products = prodRepo.findAll();

        if (products.size() > 0) {
            return products;
        } else {
            return new ArrayList<>();
        }
    }

    public Product getProduct(Integer id) {
        Product product = prodRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found :: ", "ID", id));

        return product;
    }

    public Product createProduct(Product entity) {
        Product p = new Product(entity.getName(), entity.getDescription(), entity.getPrice());
        for (Category category : entity.getCategoryList()) {
            Category c = catRepo.findById(category.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found :: ", "ID", category.getId()));
            p.getCategoryList().add(c);
            c.getProductList().add(p);
        }

        return prodRepo.save(p);

    }

    public Product updateProduct(Integer id, Product entity) {
        Product p = prodRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found :: ", "ID", id));
        p.setName(entity.getName());
        p.setDescription(entity.getDescription());
        p.setPrice(entity.getPrice());

        p.getCategoryList().stream().filter((category) -> (!entity.getCategoryList().contains(category))).forEachOrdered((category) -> {
            category.getProductList().remove(p);
        });

        p.getCategoryList().clear();

        for (Category category : entity.getCategoryList()) {
            Category c = catRepo.findById(category.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found :: ", "ID", category.getId()));
            p.getCategoryList().add(c);
            if (!c.getProductList().contains(p)) {
                c.getProductList().add(p);
            }
        }

        return prodRepo.save(p);
    }
}
