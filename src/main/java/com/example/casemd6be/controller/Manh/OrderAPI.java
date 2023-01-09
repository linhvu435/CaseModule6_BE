package com.example.casemd6be.controller.Manh;

import com.example.casemd6be.model.*;
import com.example.casemd6be.repository.manh.IBillRepoM;
import com.example.casemd6be.repository.manh.IBillStatusM;
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
    private IAccountRepoS iAccountRepo;

    @Autowired
    private IProductRepoM iProductRepoM;
    @Autowired
    IProductRepoS iProductRepoS;
    @Autowired
    IShopRepoM iShopRepoM;
    @Autowired
    private IBillStatusM iBillStatusM;
    @Autowired
    private IBillRepoM iBillRepoM;

    @GetMapping("/getallp")
    public ResponseEntity<List<Product>> getallPByShop() {
        return new ResponseEntity<>(iProductRepoM.findAllP(), HttpStatus.OK);
    }

    @GetMapping("/getallbill")
    public ResponseEntity<List<Bill>> getall() {
        return new ResponseEntity<>(iBillRepoM.findAllB(), HttpStatus.OK);
    }

    @GetMapping("/getallbillstatus")
    public ResponseEntity<List<BillStatus>> getallbillstatus() {
        return new ResponseEntity<>(iBillStatusM.findAllBillStatus(), HttpStatus.OK);
    }

    @GetMapping("/showBillShop")
    public ResponseEntity<?> showbillshop() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        List<Bill> bills1 = iBillRepoM.findAllB();
        List<Bill> billList=new ArrayList<>();
        for (int i = 0; i < bills1.size(); i++) {
            for (int j = 0; j < bills1.get(i).getProduct().size(); j++) {
                if (bills1.get(i).getProduct().get(j).getShop().getAccount().getId()==account.getId()){
                    billList.add(bills1.get(i));
                    break;
                }
            }
        }
        return new ResponseEntity<>(billList, HttpStatus.OK);
    }


    @PostMapping("/createbill")
    public ResponseEntity<?> searchbill(@RequestBody Bill bill) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        List<Product> p = bill.getProduct();
        List<Product> products1 = new ArrayList<>();
//        thêm các sp từ id truyen vao
        for (int i = 0; i < p.size(); i++) {
            products1.add(iProductRepoS.findProductById(p.get(i).getId()));
        }
        List<Product> products = new ArrayList<>();
        List<Shop> shops1 = new ArrayList<>();
        List<Shop> shops = iShopRepoM.findAllShop();
        LocalDateTime dateTime = LocalDateTime.now();
        //kiểm tra shop của các sp rồi thêm các shop vòa mảng
        for (int i = 0; i < shops.size(); i++) {
            for (int j = 0; j < products1.size(); j++) {
                if (products1.get(j).getShop().getId() == shops.get(i).getId()) {
                    shops1.add(shops.get(i));
                    break;
                }
            }
        }
        //tạo bill cho các sp của từng shop
        for (int i = 0; i < shops1.size(); i++) {
            for (int j = 0; j < products1.size(); j++) {
                if (shops1.get(i).getId() == products1.get(j).getShop().getId()) {
                    products.add(products1.get(j));
                }
            }
            bill.setProduct(products);
            bill.setAccount(account);
            bill.setDate(dateTime);
            BillStatus billStatus =new BillStatus();
            billStatus.setId(1);
            billStatus.setName("Chờ xác nhận");
            bill.setBillStatus(billStatus);
            iBillRepoM.save(bill);
            products = new ArrayList<>();
            bill=new Bill();
        }
            return new ResponseEntity<>("Tạo bill thành công", HttpStatus.BAD_REQUEST);
        }



    @PostMapping("/setbill/{idbill}/{idstatus}")
    public ResponseEntity<Bill> setbill(@PathVariable long idbill,@PathVariable long idstatus) {
        Bill bill = iBillRepoM.findBillById(idbill);
        BillStatus billStatus = iBillStatusM.findBillStatusById(idstatus);
        bill.setBillStatus(billStatus);
        iBillRepoM.save(bill);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

}