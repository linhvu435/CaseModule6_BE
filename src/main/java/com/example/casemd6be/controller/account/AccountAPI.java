package com.example.casemd6be.controller.account;

import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.dto.SocialDTO;
import com.example.casemd6be.model.JwtResponse;
import com.example.casemd6be.model.Roles;
import com.example.casemd6be.model.Shop;
import com.example.casemd6be.repository.IAccountRepo;
import com.example.casemd6be.repository.sang.AccountRepo;
import com.example.casemd6be.repository.son.IRoloesRepoS;
import com.example.casemd6be.service.JwtService;
import com.example.casemd6be.service.impl.AccountServiceImpl;
import com.example.casemd6be.service.impl.RolesServiceImpl;
import com.example.casemd6be.service.linh.ShopAddressService;
import com.example.casemd6be.service.linh.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@Validated
@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin("*")
@RequestMapping("/login")
public class AccountAPI {
    @Autowired

    private Environment env;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private  RolesServiceImpl rolesService;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    ShopService shopService;
    @Autowired
    ShopAddressService shopAddressService;
    @Autowired
    private IAccountRepo iAccountRepo;
    @Autowired
    private IRoloesRepoS iRoloesRepoS;

    @GetMapping("/users")
    public ResponseEntity<Iterable<Account>> showAllUser() {
        Iterable<Account> accounts = accountService.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<Iterable<Account>> showAllUserByAdmin() {
        Iterable<Account> accounts = accountService.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody Account account) {
        Account account1 = iAccountRepo.findByUsername(account.getUsername());
        Account account2 = iAccountRepo.findByPhoneNumber(account.getPhoneNumber());
        Account account3 = iAccountRepo.findAccountByEmail(account.getEmail());
        boolean checkusername= account1 ==null;
        boolean checkphone= account2  ==null;
        boolean checkemail= account3 ==null;
        List<Boolean> resuilt = new ArrayList<>();

        if (checkusername&&checkemail&&checkphone){
            account.setDate(LocalDate.now());
            account.setStatus(1);
            account.setImg("https://st.quantrimang.com/photos/image/2020/02/22/avatar-den-co-don-9.png");
            Roles roles = iRoloesRepoS.findById(Long.valueOf(2)).get();
            account.setRoles(roles);
            accountService.save(account);
            return new ResponseEntity<>(resuilt, HttpStatus.OK);

        }else {
            resuilt.add(checkusername);
            resuilt.add(checkusername);
            resuilt.add(checkemail);
            return new ResponseEntity<>(resuilt, HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        Account account1 = iAccountRepo.findByUsername(account.getUsername());
        if (account1.getStatus()==2) {
            return new ResponseEntity<>("Tài khoản đã bị khóa ", HttpStatus.BAD_REQUEST);
        }else {
            if (!accountService.isRegister(account)) {
                return new ResponseEntity<>("1", HttpStatus.NOT_FOUND);
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtService.createToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Account currentUser = accountService.findByUsername(account.getUsername());
            Shop idShopAddress = shopService.findshopbyidaccount(currentUser.getId());
            if (idShopAddress != null) {
                return ResponseEntity.ok(new JwtResponse(currentUser.getId(), jwt, userDetails.getUsername(),
                        currentUser.getEmail(), currentUser.getImg(), currentUser.getPhoneNumber(), currentUser.getAddress(), idShopAddress.getName(),
                        currentUser.getGender(), currentUser.getDate(), currentUser.getBirthday(), userDetails.getAuthorities()));
            } else {
                return ResponseEntity.ok(new JwtResponse(currentUser.getId(), jwt, userDetails.getUsername(),
                        currentUser.getEmail(), currentUser.getImg(), currentUser.getPhoneNumber(), currentUser.getAddress(), null,
                        currentUser.getGender(), currentUser.getDate(), currentUser.getBirthday(), userDetails.getAuthorities()));
            }
        }
    }


    @PostMapping("/register1")
    public ResponseEntity<?> register1(@RequestBody SocialDTO socialDTO) {
        Account user1 = iAccountRepo.findByUsername(socialDTO.getEmail());

        if (user1==null) {
            Account users = new Account();
            users.setUsername(socialDTO.getEmail());
            users.setPassword("0101010101");
            users.setEmail(socialDTO.getEmail());
            users.setImg(socialDTO.getPhotoUrl());
            users.setRoles(iRoloesRepoS.findById(Long.valueOf(2)).get());
            users.setStatus(1);
            iAccountRepo.save(users);
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = socialDTO.getIdToken();
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                Account currentUser = accountService.findByUsername(users.getUsername());
                    return ResponseEntity.ok(new JwtResponse(currentUser.getId(),jwt, userDetails.getUsername(),
                            currentUser.getEmail(), currentUser.getImg(), currentUser.getPhoneNumber(),currentUser.getAddress(),currentUser.getAddress(),
                            currentUser.getGender(),currentUser.getDate(),currentUser.getBirthday(),userDetails.getAuthorities()));

        }else  {
            Account account = iAccountRepo.findByUsername(socialDTO.getEmail());
            Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(account.getUsername(), "0101010101"));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = socialDTO.getIdToken();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Account currentUser = accountService.findByUsername(socialDTO.getEmail());
            return ResponseEntity.ok(new JwtResponse(currentUser.getId(),jwt, userDetails.getUsername(),
                    currentUser.getEmail(), currentUser.getImg(), currentUser.getPhoneNumber(),currentUser.getAddress(),currentUser.getAddress(),
                    currentUser.getGender(),currentUser.getDate(),currentUser.getBirthday(),userDetails.getAuthorities()));
        }
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<Account> getProfile(@PathVariable Long id) {
        Optional<Account> userOptional = this.accountService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Account> updateUserProfile(@PathVariable Long id, @RequestBody Account account) {
        Optional<Account> userOptional = this.accountService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        account.setId(userOptional.get().getId());
        account.setUsername(userOptional.get().getUsername());
//        account.setEnabled(userOptional.get().isEnabled());
        account.setPassword(userOptional.get().getPassword());
        account.setRoles(userOptional.get().getRoles());
        account.setImg(userOptional.get().getImg());
        accountService.save(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    //    thêm
    @GetMapping("/checkUsername")
    public ResponseEntity<Account> checkUser(@RequestParam String username) {
        Account account1 = accountService.findByName(username);
        if (account1 == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/checkPhonenumber")
    public ResponseEntity<Account> checkPhoneNumber(@RequestParam String phoneNumber) {
        Account account = accountService.findbysdt(phoneNumber);
        if (account==null){
            return new ResponseEntity<>(account,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(account, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/checkemail")
    public ResponseEntity<Account> checkEmail(@RequestParam String email) {
        Account account = accountService.findbyEmail(email);
        if (account==null){
            return new ResponseEntity<>(account,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(account, HttpStatus.BAD_REQUEST);
        }
    }
}
