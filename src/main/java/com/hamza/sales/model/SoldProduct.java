package com.hamza.sales.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sold_product")
public class SoldProduct implements Serializable {

    @EmbeddedId
    protected SoldProductPK soldProductPK;
    @Basic(optional = false)
    @Column(name = "quantity")
    private short quantity;
    @Basic(optional = false)
    @Column(name = "sale_price")
    private BigDecimal salePrice;
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;
    @JoinColumn(name = "sale_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonIgnoreProperties("soldProductList")
    private Sale sale;

    public SoldProduct() {
    }

    public SoldProduct(SoldProductPK soldProductPK) {
        this.soldProductPK = soldProductPK;
    }

    public SoldProduct(SoldProductPK soldProductPK, short quantity, BigDecimal salePrice) {
        this.soldProductPK = soldProductPK;
        this.quantity = quantity;
        this.salePrice = salePrice;
    }

    public SoldProduct(int saleId, int productId) {
        this.soldProductPK = new SoldProductPK(saleId, productId);
    }

    public SoldProductPK getSoldProductPK() {
        return soldProductPK;
    }

    public void setSoldProductPK(SoldProductPK soldProductPK) {
        this.soldProductPK = soldProductPK;
    }

    public short getQuantity() {
        return quantity;
    }
    
    public BigDecimal getSalePrice(){
        return salePrice;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (soldProductPK != null ? soldProductPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SoldProduct)) {
            return false;
        }
        SoldProduct other = (SoldProduct) object;
        return !((this.soldProductPK == null && other.soldProductPK != null) || (this.soldProductPK != null && !this.soldProductPK.equals(other.soldProductPK)));
    }

    @Override
    public String toString() {
        return "com.hamza.sales.model.SoldProduct[ soldProductPK=" + soldProductPK + " ]";
    }
    
}
