package com.example.clase2.entitys;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productid;
    @Column(nullable = false)
    private String productname;
    @ManyToOne
    @JoinColumn(name="SupplierID")
    private SupplierEntity supplier;
    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private CategoryEntity category;
    private String quantityperunit;
    private BigDecimal unitprice;
    private short unitsinstock;
    private short unitsonorder;
    private short reorderlevel;
    @Column(nullable = false)
    private byte discontinued;

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public String getQuantityperunit() {
        return quantityperunit;
    }

    public void setQuantityperunit(String quantityperunit) {
        this.quantityperunit = quantityperunit;
    }

    public BigDecimal getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public short getUnitsinstock() {
        return unitsinstock;
    }

    public void setUnitsinstock(short unitsinstock) {
        this.unitsinstock = unitsinstock;
    }

    public short getUnitsonorder() {
        return unitsonorder;
    }

    public void setUnitsonorder(short unitsonorder) {
        this.unitsonorder = unitsonorder;
    }

    public short getReorderlevel() {
        return reorderlevel;
    }

    public void setReorderlevel(short reorderlevel) {
        this.reorderlevel = reorderlevel;
    }

    public byte getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(byte discontinued) {
        this.discontinued = discontinued;
    }
}
