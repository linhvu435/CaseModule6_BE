
import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.JwtResponse;
import com.example.casemd6be.model.Roles;
import com.example.casemd6be.service.JwtService;
import com.example.casemd6be.service.impl.AccountServiceImpl;
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
    public ResponseEntity<?> createUser(@Valid @RequestBody Account account, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Iterable<Account> accounts = accountService.findAll();
        for (Account currentUser : accounts) {
            if (currentUser.getUsername().equals(account.getUsername())) {
                return new ResponseEntity<>("Tên người dùng đã tồn tại", HttpStatus.BAD_REQUEST);
            }
            if (currentUser.getEmail().equals(account.getEmail())) {
                return new ResponseEntity<>("Email đã tồn tại", HttpStatus.BAD_REQUEST);
            }
        }
        Roles roles2 = new Roles();
        roles2.setId(1);
        roles2.setName("ROLE_USER");
        account.setRoles(roles2);
        account.setPassword(account.getPassword());
        accountService.save(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

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

//    @PutMapping("/users/change-password/{id}")
//    public ResponseEntity<Account> updateUserPassword(@PathVariable Long id, @RequestBody Account account, @RequestParam("currentPassword") String oldPassword) {
//        Optional<Account> userOptional = this.accountService.findById(id);
//        Account userTest = new Account(userOptional.get().getUsername(), oldPassword);
//        if (!userOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        if (login(userTest).getStatusCode().equals(HttpStatus.OK)) {
//            account.setId(userOptional.get().getId());
//            account.setUsername(userOptional.get().getUsername());
//            account.setEnabled(userOptional.get().isEnabled());
//            account.setImg(userOptional.get().getImg());
//            account.setAddress(userOptional.get().getAddress());
//            account.setBirthday(userOptional.get().getBirthday());
//            account.setEmail(userOptional.get().getEmail());
//            account.setPhoneNumber(userOptional.get().getPhoneNumber());
//            account.setRoles(userOptional.get().getRoles());
//            account.setPassword(account.getPassword());
//            accountService.save(account);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(account, HttpStatus.OK);
//    }

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
//
//
//    @ExceptionHandler({ConstraintViolationException.class})
//    public ResponseEntity<Object> handleConstraintViolation(
//            ConstraintViolationException ex, WebRequest request) {
////        List<String> errors = new ArrayList<String>();
//        Map<String, String> errors = new HashMap<>();
//        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
////            errors.add(violation.getRootBeanClass().getName() + " " +
////                    violation.getPropertyPath() + ": " + violation.getMessage());
//            String path = String.valueOf(violation.getPropertyPath());
//            errors.put(path.replace("createUser.user.", ""), violation.getMessage());
//        }
//
//        ApiError apiError =
//                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
//        return new ResponseEntity<Object>(
//                apiError, new HttpHeaders(), apiError.getStatus());
//    }

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
