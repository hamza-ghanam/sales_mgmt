package com.hamza.sales.service;

import com.hamza.sales.exception.ResourceNotFoundException;
import com.hamza.sales.model.Seller;
import com.hamza.sales.repository.SellerRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository repo;

    public List<Seller> getAllSellers() {
        List<Seller> sellers = repo.findAll();

        if (sellers.size() > 0) {
            return sellers;
        } else {
            return new ArrayList<>();
        }
    }

    public Seller getSeller(Integer id) {
        Seller seller = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found :: ", "ID", id));

        return seller;
    }

    public Seller createSeller(Seller entity) {
        repo.save(entity);

        return entity;
    }

    public Seller updateSeller(Integer id, Seller entity) {
        Seller seller = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found :: ", "ID", id));

        seller.setName(entity.getName());
        seller.setMobile(entity.getMobile());

        return repo.save(seller);
    }
}
