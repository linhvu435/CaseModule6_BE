package com.example.casemd6be.controller.Manh;

import com.example.casemd6be.model.*;

import com.example.casemd6be.model.dto.BillDTO;
import com.example.casemd6be.repository.manh.IBillRepoM;
import com.example.casemd6be.repository.manh.IBillStatusM;
import com.example.casemd6be.repository.manh.IProductRepoM;
import com.example.casemd6be.repository.manh.IShopRepoM;
import com.example.casemd6be.model.dto.ProductInBillDTO;
import com.example.casemd6be.repository .manh.*;
import com.example.casemd6be.repository.son.IAccountRepoS;
import com.example.casemd6be.repository.son.IProductRepoS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private ICategoryM iCategoryM;

    @Autowired
    private IImgProductRepoM iImgProductRepo;
    @Autowired
    private IVoucherRepoM iVoucherRepoM;


    @GetMapping("/getshop")
    public ResponseEntity<?> getshop() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        Shop shop = iShopRepoM.findShopByAccountId(account.getId());
        if (shop == null ){
            return new ResponseEntity<>("Chưa đki dịch vụ bán hàng !", HttpStatus.OK);
        }else {
            return new ResponseEntity<>(shop, HttpStatus.OK);
        }
    }
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
        List<BillStatus> billStatuses =iBillStatusM.findAllBillStatus();
        for (int i = 0; i < billStatuses.size(); i++) {
            if (billStatuses.get(i).getId()==7){
                billStatuses.remove(billStatuses.get(i));
            }
        }
        return new ResponseEntity<>(billStatuses, HttpStatus.OK);
    }

    @GetMapping("/showBillShop")
    public ResponseEntity<?> showbillshop() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        List<Bill> bills1 = iBillRepoM.findAllB();
        List<Bill> billList = new ArrayList<>();
        for (int i = 0; i < bills1.size(); i++) {
            for (int j = 0; j < bills1.get(i).getProduct().size(); j++) {
                if (bills1.get(i).getProduct().get(j).getShop().getAccount().getId()==account.getId()&&bills1.get(i).getBillStatus().getId()!=6){
                    billList.add(bills1.get(i));
                    break;
                }
            }
        }
        return new ResponseEntity<>(billList, HttpStatus.OK);
    }

    @GetMapping("/showBillShopbyidbill/{id}")
    public ResponseEntity<?> showbilldetailbyidbill(@PathVariable long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        List<Bill> bills1 = iBillRepoM.findAllB();
        List<Bill> billList=new ArrayList<>();
        for (int i = 0; i < bills1.size(); i++) {
            for (int j = 0; j < bills1.get(i).getProduct().size(); j++) {
                if (bills1.get(i).getProduct().get(j).getShop().getAccount().getId()==account.getId()&&bills1.get(i).getBillStatus().getId()!=6){
                    billList.add(bills1.get(i));
                    break;
                }
            }
        }
        Bill bill =new Bill();
        for (int i = 0; i < billList.size(); i++) {
            if (billList.get(i).getId()==id){
                bill=billList.get(i);
            }
        }
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
        List<Bill> bills1 = iBillRepoM.findAllB();
        List<Bill> billList = new ArrayList<>();
        for (int i = 0; i < bills1.size(); i++) {
            for (int j = 0; j < bills1.get(i).getProduct().size(); j++) {
                if (bills1.get(i).getProduct().get(j).getShop().getAccount().getId() == account.getId()) {
                    billList.add(bills1.get(i));
                    break;
                }
            }
        }
        List<Bill> billList1 = new ArrayList<>();
        for (int i = 0; i < billList.size(); i++) {
            if (billList.get(i).getBillStatus().getId() == id) {
                billList1.add(billList.get(i));
            }
        }
        return new ResponseEntity<>(billList1, HttpStatus.OK);
    }

    @PostMapping("/createbill/{namevoucher}")
    public ResponseEntity<?> searchbill(@RequestBody BillDTO billDTO,@PathVariable String namevoucher) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        List<Product> products1 = new ArrayList<>();
        Bill bills = new Bill();
        boolean check = true;
        //thêm các sp từ id truyen vao
        for (int i = 0; i < billDTO.getProductBillDTOS().size(); i++) {
            Product product =iProductRepoS.findProductById(billDTO.getProductBillDTOS().get(i).getIdproduct());
            products1.add(product);
            if (product.getAmount()<billDTO.getProductBillDTOS().get(i).getAmount()){
                check=false;
            }
        }
        if (check){
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
        double totalprice = 0;
        List<Bill> toSaveList = new ArrayList<>();
            Voucher voucher = iVoucherRepoM.findVoucherByName(namevoucher);

            //tạo bill cho các sp của từng shop
        for (int i = 0; i < shops1.size(); i++) {
            //them sp của từng shop vào
            for (int j = 0; j < products1.size(); j++) {
                if (shops1.get(i).getId() == products1.get(j).getShop().getId()) {
                    products.add(products1.get(j));
                }
            }
            for (int j = 0; j < products.size(); j++) {
                for (int k = 0; k < billDTO.getProductBillDTOS().size(); k++) {
                    if (products.get(j).getId() == billDTO.getProductBillDTOS().get(k).getIdproduct()) {
                        totalprice += products.get(j).getPrice() * billDTO.getProductBillDTOS().get(k).getAmount();
                        long amountsell = products.get(j).getAmount()+billDTO.getProductBillDTOS().get(k).getAmount();
                        products.get(j).setAmountsell(amountsell);
                        long amount= products.get(j).getAmount()-billDTO.getProductBillDTOS().get(k).getAmount() ;
                        products.get(j).setAmount(amount);
                        iProductRepoM.save(products.get(j));

                    }
                    if (voucher!=null){
                        if (voucher.getShop().getId()==products.get(j).getShop().getId()){
                            if (voucher.getVoucherType().getId()==1){
                                totalprice = totalprice-((totalprice*10)/100);
                                long amount =voucher.getAmount();
                                amount--;
                                voucher.setAmount(amount);
                                iVoucherRepoM.save(voucher);
                            } else if(voucher.getVoucherType().getId()==2) {
                                totalprice = totalprice-((totalprice*5)/100);
                                long amount =voucher.getAmount();
                                amount--;
                                voucher.setAmount(amount);
                                iVoucherRepoM.save(voucher);
                            }
                        }
                    }

                }
            }
            bills.setVoucher(voucher);
            bills.setProduct(products);
            bills.setAccount(account);
            bills.setDate(dateTime);
            BillStatus billStatus =new BillStatus();
            billStatus.setId(1);

            billStatus.setName("Chờ xác nhận");
            bills.setBillStatus(billStatus);

            bills.setTotalprice(totalprice);
            toSaveList.add(bills);
//            iBillRepoM.save(bills);
            products = new ArrayList<>();
            bills = new Bill();
            totalprice = 0;
        }
        List<Bill> saved = (List<Bill>)  iBillRepoM.saveAll(toSaveList);
        return ResponseEntity.ok(saved);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/setbill/{idbill}/{idstatus}")
    public ResponseEntity<Bill> setbill(@PathVariable long idbill, @PathVariable long idstatus) {
        Bill bill = iBillRepoM.findBillById(idbill);
        BillStatus billStatus = iBillStatusM.findBillStatusById(idstatus);
        bill.setBillStatus(billStatus);
        iBillRepoM.save(bill);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

}