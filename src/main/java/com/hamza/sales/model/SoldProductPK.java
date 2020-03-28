/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamza.sales.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SoldProductPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "sale_id")
    private int saleId;
    @Basic(optional = false)
    @Column(name = "product_id")
    private int productId;

    public SoldProductPK() {
    }

    public SoldProductPK(int saleId, int productId) {
        this.saleId = saleId;
        this.productId = productId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
        @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) saleId;
        hash += (int) productId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SoldProductPK)) {
            return false;
        }
        SoldProductPK other = (SoldProductPK) object;
        if (this.saleId != other.saleId) {
            return false;
        }
        if (this.productId != other.productId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hamza.sales.model.SoldProductPK[ saleId=" + saleId + ", productId=" + productId + " ]";
    }
}
