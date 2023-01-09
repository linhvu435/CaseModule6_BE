package com.example.casemd6be.model.DTO;

import java.util.List;

public class BillDTO extends ProductBillDTO{
    List<ProductBillDTO> productBillDTOS;

    public BillDTO(List<ProductBillDTO> productBillDTOS) {
        this.productBillDTOS = productBillDTOS;
    }

    public BillDTO(long idproduct, long amount, List<ProductBillDTO> productBillDTOS) {
        super(idproduct, amount);
        this.productBillDTOS = productBillDTOS;
    }

    public BillDTO() {
    }

    public List<ProductBillDTO> getProductBillDTOS() {
        return productBillDTOS;
    }

    public void setProductBillDTOS(List<ProductBillDTO> productBillDTOS) {
        this.productBillDTOS = productBillDTOS;
    }
}
