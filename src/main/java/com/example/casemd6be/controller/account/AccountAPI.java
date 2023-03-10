package com.example.casemd6be.controller.account;

import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.JwtResponse;
import com.example.casemd6be.model.Roles;
import com.example.casemd6be.repository.sang.AccountRepo;
import com.example.casemd6be.service.JwtService;
import com.example.casemd6be.service.impl.AccountServiceImpl;
import com.example.casemd6be.service.impl.RolesServiceImpl;
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
//them l???i
@PostMapping("/register")
public ResponseEntity<?> createUser(@Valid @RequestBody Account account, BindingResult bindingResult) {
    if (bindingResult.hasFieldErrors()) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Iterable<Account> accounts = accountService.findAll();
    for (Account currentUser : accounts) {
        if (currentUser.getUsername().equals(account.getUsername())) {
            return new ResponseEntity<>("T??n ng?????i d??ng ???? t???n t???i", HttpStatus.BAD_REQUEST);
        }
        if (currentUser.getEmail().equals(account.getEmail())) {
            return new ResponseEntity<>("Email ???? t???n t???i", HttpStatus.BAD_REQUEST);
        }
    }
    if (account.getRoles() != null) {
        Roles role = rolesService.findByName("ROLE_ADMIN");
        Set<Roles> roles = new HashSet<>();
        roles.add(role);
        account.setRoles(role);
    } else {
        Roles role1 = rolesService.findByName("ROLE_USER");
        Set<Roles> roles1 = new HashSet<>();
        roles1.add(role1);
        account.setRoles((Roles) roles1);
    }
    account.setPassword(account.getPassword());
    accountService.save(account);
    return new ResponseEntity<>("l???i", HttpStatus.CREATED);
}

//    aaaaaaaaaaaaaaa
//    @PostMapping("/register")
//    public ResponseEntity<?> createUser(@RequestBody Account account) {
//        Iterable<Account> accounts = accountService.findAll();
//        for (Account currentUser : accounts) {
//            if (currentUser.getUsername().equals(account.getUsername())) {
//                return new ResponseEntity<>("T??n ng?????i d??ng ???? t???n t???i", HttpStatus.BAD_REQUEST);
//            } else if (currentUser.getEmail().equals(account.getEmail())) {
//                return new ResponseEntity<>("Email ???? t???n t???i", HttpStatus.BAD_REQUEST);
//            }else{
//                Roles roles2 = new Roles();
//                roles2.setId(2);
//                roles2.setName("ROLE_USER");
//                account.setRoles(roles2);
//                accountService.save(account);
//                return new ResponseEntity<>("L?????i", HttpStatus.BAD_REQUEST);
//            }
//        }
//        return new ResponseEntity<>(account, HttpStatus.CREATED);
//    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        if (!accountService.isRegister(account)) {
            return new ResponseEntity<>("1", HttpStatus.NOT_FOUND);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.createToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account currentUser = accountService.findByUsername(account.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getEmail(), currentUser.getImg(), userDetails.getAuthorities()));
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

//    @PutMapping("/users/change-img/{id}")
//    public ResponseEntity<Account> updateUserAvatar(@PathVariable Long id, @RequestBody Account account) {
//        Optional<Account> userOptional = this.accountService.findById(id);
//        if (!userOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        account.setId(userOptional.get().getId());
//        account.setUsername(userOptional.get().getUsername());
//        account.setEnabled(userOptional.get().isEnabled());
//        account.setAddress(userOptional.get().getAddress());
//        account.setBirthday(userOptional.get().getBirthday());
//        account.setEmail(userOptional.get().getEmail());
//        account.setPhoneNumber(userOptional.get().getPhoneNumber());
//        account.setRoles(userOptional.get().getRoles());
//        account.setPassword(userOptional.get().getPassword());
//        if (account.getImg().equals("")) {
//            account.setImg(userOptional.get().getImg());
//            accountService.save(account);
//        } else {
//            accountService.save(account);
//        }
//        return new ResponseEntity<>(account, HttpStatus.OK);
//    }

    //    th??m
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
