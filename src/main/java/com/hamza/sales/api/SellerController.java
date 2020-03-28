package com.hamza.sales.api;

import com.hamza.sales.exception.ResourceNotFoundException;
import com.hamza.sales.model.Seller;
import com.hamza.sales.service.SellerService;
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
public class SellerController {

    @Autowired
    SellerService service;

    @CrossOrigin(origins = "*")
    @GetMapping("/sellers")
    public ResponseEntity<List<Seller>> getAllProducts() {
        List<Seller> list = service.getAllSellers();

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/sellers/{id}")
    public ResponseEntity<Seller> getSeller(@PathVariable(value = "id") Integer id) {
        Seller seller = service.getSeller(id);

        return new ResponseEntity<>(seller, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/sellers")
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws ResourceNotFoundException {
        Seller s1 = service.createSeller(seller);

        return new ResponseEntity<>(s1, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/sellers/{id}")
    public ResponseEntity<Seller> updateSeller(
            @PathVariable(value = "id") Integer id,
            @RequestBody Seller seller)
            throws ResourceNotFoundException {

        Seller s1 = service.updateSeller(id, seller);

        return new ResponseEntity<>(s1, new HttpHeaders(), HttpStatus.OK);
    }

}
