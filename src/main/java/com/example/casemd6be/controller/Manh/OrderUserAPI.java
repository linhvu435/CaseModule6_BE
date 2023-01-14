package com.example.casemd6be.controller.Manh;

import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.Bill;
import com.example.casemd6be.model.dto.ProductInBillDTO;
import com.example.casemd6be.model.ImgProduct;
import com.example.casemd6be.model.Product;
import com.example.casemd6be.repository.IAccountRepo;
import com.example.casemd6be.repository.manh.IBillRepoM;
import com.example.casemd6be.repository.manh.IImgProductRepoM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/orderuser")
public class OrderUserAPI {
    @Autowired
    IAccountRepo iAccountRepo;
    @Autowired
    IBillRepoM iBillRepoM;

    @Autowired
    private IImgProductRepoM iImgProductRepo;
    @GetMapping("/showBillShop")
    public ResponseEntity<?> showBillShopByAccount() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        List<Bill> bills1 = iBillRepoM.findAllBbyIdAccount(account.getId());
        List<Bill> billList = new ArrayList<>();
        //check bill của account có trạng thái khác 7
        for (int i = 0; i < bills1.size(); i++) {
            if (bills1.get(i).getBillStatus().getId() != 7) {
                billList.add(bills1.get(i));
            }
        };
        return new ResponseEntity<>(billList, HttpStatus.OK);
    }

    @GetMapping("/showBillShopbyidbill/{id}")
    public ResponseEntity<?> showbilldetailbyidbill(@PathVariable long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        List<Bill> bills1 = iBillRepoM.findAllBbyIdAccount(account.getId());
        List<Bill> billList=new ArrayList<>();
        //check bill của account có trạng thái khác 6
        for (int i = 0; i < bills1.size(); i++) {
           if(bills1.get(i).getBillStatus().getId()!=7){
               billList.add(bills1.get(i));
           }
        }
        // trả về bill theo id bill
        Bill bill =new Bill();
        for (int i = 0; i < billList.size(); i++) {
            if (billList.get(i).getId()==id){
                bill=billList.get(i);
            }
        }
        // trả về list product theo bill trên
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < bill.getProduct().size(); i++) {
            products.add(bill.getProduct().get(i));
        }

        ProductInBillDTO productInBillDTO = new ProductInBillDTO(bill.getId(),bill.getAccount().getName(),products,bill.getTotalprice());
        return new ResponseEntity<>(productInBillDTO, HttpStatus.OK);
    }

    @GetMapping("/showBillShop/{id}")
    public ResponseEntity<?> showbillshopbystatus(@PathVariable long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        List<Bill> bills1 = iBillRepoM.findAllBbyIdAccount(account.getId());

        List<Bill> billList1 = new ArrayList<>();
        for (int i = 0; i < bills1.size(); i++) {
            if (bills1.get(i).getBillStatus().getId()==id){
                billList1.add(bills1.get(i));
            }
        }
        return new ResponseEntity<>(billList1, HttpStatus.OK);
    }



}
