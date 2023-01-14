package com.example.casemd6be.model.dto;


public class ProductBillDTO{

    long idproduct;
    long amount;


    public ProductBillDTO() {
    }

    public ProductBillDTO(long idproduct, long amount) {
        this.idproduct = idproduct;
        this.amount = amount;
    }

    public long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(long idproduct) {
        this.idproduct = idproduct;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}