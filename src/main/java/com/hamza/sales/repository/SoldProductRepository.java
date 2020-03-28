/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamza.sales.repository;

import com.hamza.sales.model.SoldProduct;
import com.hamza.sales.model.SoldProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldProductRepository extends JpaRepository<SoldProduct, SoldProductPK> {
    
}
