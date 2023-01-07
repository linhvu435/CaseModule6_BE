package com.example.casemd6be.controller.Manh;

import com.example.casemd6be.model.*;
import com.example.casemd6be.repository.manh.IBillRepoM;
import com.example.casemd6be.repository.manh.IProductRepoM;
import com.example.casemd6be.repository.manh.IShopRepoM;
import com.example.casemd6be.repository.son.IAccountRepoS;
import com.example.casemd6be.repository.son.IProductRepoS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/order")
public class OrderAPI {


    @Autowired
    IBillRepoM iBillRepoM;
    @Autowired
    private IAccountRepoS iAccountRepo;

    @Autowired
    private IProductRepoM iProductRepoM;

    @Autowired
    IProductRepoS iProductRepoS;

    @Autowired
    IShopRepoM iShopRepoM;

    @GetMapping("/getallp")
    public ResponseEntity<List<Product>> getallPByShop(){
        return new ResponseEntity<>(iProductRepoM.findAllP(),HttpStatus.OK);
    }

    @GetMapping("/getallbill")
    public ResponseEntity<List<Bill>> getall() {
        return new ResponseEntity<>(iBillRepoM.findAllB(), HttpStatus.OK);
    }

    @GetMapping("/showBillShop")
    public ResponseEntity<?> showbillshop() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        Shop shop = iShopRepoM.findShopByAccountId(account.getId());
        List<Bill> bills1 = iBillRepoM.findAllByShop_Id(shop.getId());
        List<Bill> billList ;
        for (int i = 0; i < bills1.size(); i++) {
            for (int j = 1; j < bills1.size() ; j++) {
                if (bills1.get(i).getId()==bills1.get(j).getId()){
                    bills1.remove(bills1.get(i));
                }
            }
        }
        for (int i = 0; i < bills1.size(); i++) {
            for (int j = 0; j < bills1.get(i).getProduct().size(); j++) {
                if(bills1.get(i).getProduct().get(j).getShop().getId() != shop.getId() ){
                    bills1.get(i).getProduct().remove(bills1.get(i).getProduct().get(j));
                }
            }
        }

        return new ResponseEntity<>(shop.getId(), HttpStatus.OK);
    }

    @GetMapping("/sapxep")
    public ResponseEntity<?> sapxep() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        Shop shop = iShopRepoM.findShopByAccountId(account.getId());
        List<Bill> bills1 = iBillRepoM.findAllByShop_IdDesc(shop.getId());
        return new ResponseEntity<>(bills1, HttpStatus.OK);
    }
    @GetMapping("/searchBill/{name}")
    public ResponseEntity<List<Bill>> searchbill(@PathVariable String name) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        List<Bill> bills = iBillRepoM.findAllB();
        List<Product> products = iProductRepoM.findByName(name);
        List<Bill> billList =new ArrayList<>();

        return new ResponseEntity<>(iBillRepoM.findAllB(), HttpStatus.OK);
    }




    @PostMapping("/createbill")
    public ResponseEntity<?> searchbill(@RequestBody Bill bill) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        List<Product> products1 = bill.getProduct();
        List<Product> products = new ArrayList<>();
        boolean check = true;
        for (int i = 0; i < products1.size(); i++) {
            products.add(iProductRepoS.findProductById(products1.get(i).getId()));
        }
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getShop().getAccount().getId() == account.getId()){
                check = false;
                break;
            }
        }
        if(check){
            bill.setProduct(products);
            bill.setAccount(account);
            bill.setDate(LocalDateTime.now());
            BillStatus billStatus =new BillStatus();
            billStatus.setId(1);
            billStatus.setName("Chờ xác nhận");
            bill.setBillStatus(billStatus);
            iBillRepoM.save(bill);
            return new ResponseEntity<>(products, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Ồ đéo được rồi", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/setbill/{id}")
    public ResponseEntity<Bill> setbill(@PathVariable long id,@RequestBody Bill bill) {
        BillStatus billStatus = iBillRepoM.findStatusById(id);
        bill.setBillStatus(billStatus);
        iBillRepoM.save(bill);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

}