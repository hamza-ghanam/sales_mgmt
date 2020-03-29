package com.hamza.sales.service;

import com.hamza.sales.exception.ResourceNotFoundException;
import com.hamza.sales.model.Client;
import com.hamza.sales.model.Product;
import com.hamza.sales.model.Sale;
import com.hamza.sales.model.Seller;
import com.hamza.sales.model.SoldProduct;
import com.hamza.sales.model.SoldProductPK;
import com.hamza.sales.repository.ClientRepository;
import com.hamza.sales.repository.ProductRepository;
import com.hamza.sales.repository.SaleRepository;
import com.hamza.sales.repository.SellerRepository;
import com.hamza.sales.repository.SoldProductRepository;
import java.math.BigDecimal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepo;
    @Autowired
    ClientRepository clientRepo;
    @Autowired
    SellerRepository sellerRepo;
    @Autowired
    ProductRepository prodRepo;
    @Autowired
    SoldProductRepository soldProdRepo;

    private final static Logger LOGGER = LoggerFactory.getLogger(SaleService.class);

    public List<Sale> gettAllSales() {
        return saleRepo.findAll();
    }

    @Transactional
    public Sale createSale(List<SoldProduct> list, Integer clientId, Integer sellerId) {
        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found: ", "ID", clientId));
        Seller seller = sellerRepo.findById(sellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found: ", "ID", sellerId));

        Sale sale = new Sale();
        sale.setClientId(client);
        sale.setSellerId(seller);

        sale = saleRepo.save(sale);

        double total = 0;

        for (SoldProduct soldProduct : list) {
            SoldProductPK soldProductPK = new SoldProductPK();
            soldProductPK.setSaleId(sale.getId());
            Product p = prodRepo.findById(soldProduct.getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found: ", "ID", clientId));
            total += (p.getPrice().doubleValue() * soldProduct.getQuantity());
            soldProductPK.setProductId(p.getId());

            soldProduct.setSoldProductPK(soldProductPK);
            soldProduct.setQuantity(soldProduct.getQuantity());
            soldProduct.setSalePrice(p.getPrice());
            soldProduct.setSale(sale);
            soldProdRepo.save(soldProduct);
            sale.getSoldProductList().add(soldProduct);
        }

        sale.setTotal(new BigDecimal(total));
        return saleRepo.save(sale);

    }

    public Sale upsateQP(SoldProduct sp) {
        SoldProduct mySP = soldProdRepo.findById(new SoldProductPK(sp.getSale().getId(), sp.getProduct().getId()))
                .orElseThrow(() -> new ResourceNotFoundException("SoldProduct not found: Sale ID " + sp.getSale().getId(), ", Product ID:", sp.getProduct().getId()));

        Sale sale = saleRepo.findById(mySP.getSale().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found: ", "ID", sp.getSale().getId()));

        BigDecimal tempTotal = sale.getTotal().subtract(new BigDecimal(mySP.getQuantity() * mySP.getSalePrice().doubleValue()));

        sale.setTotal(tempTotal.add(new BigDecimal(sp.getQuantity() * sp.getSalePrice().doubleValue())));

        saleRepo.save(sale);

        LOGGER.info("Product ID: " + sp.getProduct().getId() + " in Sale ID: " + sp.getSale().getId() + "[ Quantity: " + mySP.getQuantity() + " =====> " + sp.getQuantity() + ", Price: " + mySP.getSalePrice() + " =====> " + sp.getSalePrice() + " ]");

        mySP.setQuantity(sp.getQuantity());
        mySP.setSalePrice(sp.getSalePrice());
        soldProdRepo.save(mySP);

        return sale;
    }

}
