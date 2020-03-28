package com.hamza.sales.api;

import com.hamza.sales.model.Sale;
import com.hamza.sales.model.SoldProduct;
import com.hamza.sales.service.SaleService;
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
public class SaleController {

    @Autowired
    SaleService saleService;

    @CrossOrigin(origins = "*")
    @GetMapping("/sales")
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.gettAllSales();

        return new ResponseEntity<>(sales, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/sales/{cid}/{sid}")
    public ResponseEntity<Sale> createSale(@RequestBody List<SoldProduct> list,
            @PathVariable(value = "cid") Integer clientId,
            @PathVariable(value = "sid") Integer sellerId) {
        Sale sale = saleService.createSale(list, clientId, sellerId);

        return new ResponseEntity<>(sale, new HttpHeaders(), HttpStatus.OK);
    }
    
    
    @CrossOrigin(origins = "*")
    @PostMapping("/sales/updateqp")
    public ResponseEntity<Sale> updateQP(@RequestBody SoldProduct sp){
        Sale sale = saleService.upsateQP(sp);
        
        return new ResponseEntity<>(sale, new HttpHeaders(), HttpStatus.OK);
    }
    
}
