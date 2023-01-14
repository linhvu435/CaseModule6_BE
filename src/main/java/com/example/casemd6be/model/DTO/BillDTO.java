package com.example.casemd6be.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO implements Serializable {
    List<com.example.casemd6be.model.dto.ProductBillDTO> productBillDTOS;

}
