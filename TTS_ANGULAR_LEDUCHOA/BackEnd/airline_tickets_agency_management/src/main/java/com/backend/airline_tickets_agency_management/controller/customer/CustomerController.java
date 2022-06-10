package com.backend.airline_tickets_agency_management.controller.customer;

import com.backend.airline_tickets_agency_management.model.dto.customer.CustomerDto;
import com.backend.airline_tickets_agency_management.model.dto.password.Message;
import com.backend.airline_tickets_agency_management.model.dto.password.PasswordDto;
import com.backend.airline_tickets_agency_management.model.entity.customer.Customer;
import com.backend.airline_tickets_agency_management.model.entity.user.User;
import com.backend.airline_tickets_agency_management.model.service.customer.ICustomerService;
import com.backend.airline_tickets_agency_management.model.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("api/customer")
public class CustomerController {
    private static String REGEX_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$";
    @Autowired
    private IUserService userService;
    @Autowired
    ICustomerService iCustomerService;

    @GetMapping("/findCustomerById")
    public ResponseEntity<User> getAdminById(@RequestParam(value = "id") Long id) {
        User user = this.userService.findById(id).orElse(null);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<Message> updatePasswordAdmin1(@RequestParam(value = "id") Long id, @RequestBody PasswordDto passwordDto) {
        User user = this.userService.findById(id).orElse(null);
        System.out.println(passwordDto.getNewPassword());
        System.out.println(passwordDto.getOldPassword());
        System.out.println(user.getPassword());
        System.out.println(passwordDto.getConfirmPassword());
        if (user != null) {
            if (!checkRegex(passwordDto)) {
                return new ResponseEntity<>(new Message("Mật khẩu không đúng định dạng"), HttpStatus.BAD_REQUEST);
            } else {
                Boolean comparePassword = compareRawPasswordAndEncoderPassword(passwordDto.getOldPassword(), user.getPassword());
                if (comparePassword) {
                    Boolean comparePassword1 = compareRawPasswordAndEncoderPassword(passwordDto.getNewPassword(), user.getPassword());
                    if (comparePassword1) {
                        return new ResponseEntity<>(new Message("Mật khẩu mới trùng với mật khẩu cũ"), HttpStatus.BAD_REQUEST);
                    } else {
                        String newPasswordEncoder =encoderPassword(passwordDto.getNewPassword());
                        user.setPassword(newPasswordEncoder);
                        this.userService.save(user);
                        return new ResponseEntity<>(new Message("Đổi mật khẩu thành công"), HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity<>(new Message("Sai mật khẩu"), HttpStatus.BAD_REQUEST);

                }
            }
        } else {
            return new ResponseEntity<>(new Message("không tìm thấy tài khoản"), HttpStatus.NOT_FOUND);
        }
    }

    Boolean compareRawPasswordAndEncoderPassword(String rawPassword, String encoderPassword) {
        BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
        return cryptPasswordEncoder.matches(rawPassword, encoderPassword);
    }

    Boolean checkRegex(PasswordDto passwordDto) {
        return Pattern.compile(REGEX_PASSWORD).matcher(passwordDto.getOldPassword()).matches() && Pattern.compile(REGEX_PASSWORD).matcher(passwordDto.getNewPassword()).matches() && Pattern.compile(REGEX_PASSWORD).matcher(passwordDto.getConfirmPassword()).matches();
    }

    String encoderPassword(String password) {
        BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
        return cryptPasswordEncoder.encode(password);
    }

    @GetMapping("/list")
    public Page<Customer> ShowPageCustomer(@RequestParam(defaultValue = "0") int page) {
        return iCustomerService.showListCustomer(PageRequest.of(page, 5));
    }

    @GetMapping("/search")
    public Page<Customer> searchPageCustomer(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam Optional<String> field,
                                             @RequestParam Optional<String> search) {
        String fieldSearch = "";
        String valueSearch = "";
        if (field.isPresent()) {
            fieldSearch = field.get();
        }
        if (search.isPresent()) {
            valueSearch = search.get();
        }
        return iCustomerService.searchPageCustomer(PageRequest.of(page, 5), fieldSearch, valueSearch);
    }

    @PatchMapping("/delete")
    public void DeleteCustomer(@RequestBody Customer customer) {
        customer.setFlag(false);
        iCustomerService.saveCustomer(customer);
    }

    @PostMapping("/create")
    public Map<String,Object> createCustomer(@Valid @RequestBody CustomerDto customerDto,
                                             BindingResult bindingResult) {
        return this.iCustomerService.createCustomer(customerDto,bindingResult);
    }

    @PutMapping("/update/{id}")
    public Map<String, Object> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto,
                                              BindingResult bindingResult) {
        return this.iCustomerService.updateCustomer(id,customerDto,bindingResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id){
        Customer customer = this.iCustomerService.findCustomerById(id);
        if (customer == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(customer,HttpStatus.OK);
        }
    }
}