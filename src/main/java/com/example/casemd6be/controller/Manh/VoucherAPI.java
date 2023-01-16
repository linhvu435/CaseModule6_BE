package com.example.casemd6be.controller.Manh;


import com.example.casemd6be.model.*;
import com.example.casemd6be.repository.IAccountRepo;
import com.example.casemd6be.repository.manh.IShopRepoM;
import com.example.casemd6be.repository.manh.IVoucherRepoM;
import com.example.casemd6be.repository.manh.IVoucherTypeRepoM;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class VoucherAPI {
    private final IAccountRepo iAccountRepo;
    private final IVoucherTypeRepoM iVoucherTypeRepoM;
    private final IShopRepoM iShopRepoM;
    private final IVoucherRepoM iVoucherRepoM;

    public VoucherAPI(IAccountRepo iAccountRepo,
                      IVoucherTypeRepoM iVoucherTypeRepoM,
                      IShopRepoM iShopRepoM,
                      IVoucherRepoM iVoucherRepoM) {
        this.iAccountRepo = iAccountRepo;
        this.iVoucherTypeRepoM = iVoucherTypeRepoM;
        this.iShopRepoM = iShopRepoM;
        this.iVoucherRepoM = iVoucherRepoM;
    }

    @GetMapping("/getallvouchertype")
    public ResponseEntity<?> getallvouchertype() {
        return new ResponseEntity<>(iVoucherTypeRepoM.findAll(), HttpStatus.OK);
    }


    @GetMapping("/showvoucher")
    public ResponseEntity<?> showvoucher() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        Shop shop = iShopRepoM.findShopByAccountId(account.getId());
        List<Voucher> voucher = iVoucherRepoM.findAllByShop_ID(shop.getId());
        return new ResponseEntity<>(voucher, HttpStatus.OK);
    }

    @GetMapping("/showvoucher/{idShop}")
    public ResponseEntity<?> showvoucherbyidshop(@PathVariable long idShop) {
        List<Voucher> voucher = iVoucherRepoM.findAllByShop_ID(idShop);
        return new ResponseEntity<>(voucher, HttpStatus.OK);
    }

    @GetMapping("/deletevoucher/{idvoucher}")
    public ResponseEntity<?> deletevoucher(@PathVariable long idvoucher) {


                iVoucherRepoM.deleteById(idvoucher);


        return new ResponseEntity<>("Xoa thanh cong", HttpStatus.OK);
    }

    @GetMapping("/createvoucher/{idvoucher}/{amount}")
    public ResponseEntity<?> createvoucher(@PathVariable long idvoucher,@PathVariable long amount) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        Shop shop = iShopRepoM.findShopByAccountId(account.getId());
        VoucherType voucherType = iVoucherTypeRepoM.findById(idvoucher).get();
        Voucher voucher = new Voucher();
        voucher.setVoucherType(voucherType);
        voucher.setShop(shop);
        voucher.setAmount(amount);
        double randomDouble = Math.random();
        randomDouble = randomDouble * 1000000000 + 1;
        int vouchername = (int) randomDouble;
        String namevoucher= ""+vouchername;
        voucher.setName(namevoucher);
        iVoucherRepoM.save(voucher);
        return new ResponseEntity<>(voucher, HttpStatus.OK);
    }
}
