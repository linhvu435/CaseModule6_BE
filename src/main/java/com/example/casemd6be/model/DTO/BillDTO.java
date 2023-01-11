package com.example.casemd6be.model.DTO;


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
    List<ProductBillDTO> productBillDTOS;

}
