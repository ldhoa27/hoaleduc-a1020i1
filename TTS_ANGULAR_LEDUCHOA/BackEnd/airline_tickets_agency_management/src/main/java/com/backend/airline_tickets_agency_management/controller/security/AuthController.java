package com.backend.airline_tickets_agency_management.controller.security;

import com.backend.airline_tickets_agency_management.model.entity.customer.Customer;
import com.backend.airline_tickets_agency_management.model.repository.user.IUserRepository;
import com.backend.airline_tickets_agency_management.model.service.customer.ICustomerService;
import com.backend.airline_tickets_agency_management.model.service.user.userDetail.UserDetailsImpl;
import com.backend.airline_tickets_agency_management.model.entity.user.ERole;
import com.backend.airline_tickets_agency_management.model.entity.user.Role;
import com.backend.airline_tickets_agency_management.model.entity.user.User;
import com.backend.airline_tickets_agency_management.model.repository.user.RoleRepository;
import com.backend.airline_tickets_agency_management.payload.request.LoginRequest;
import com.backend.airline_tickets_agency_management.payload.request.SignupRequest;
import com.backend.airline_tickets_agency_management.payload.response.JwtResponse;
import com.backend.airline_tickets_agency_management.payload.response.MessageResponse;
import com.backend.airline_tickets_agency_management.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@EnableScheduling
public class AuthController {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ICustomerService customerService;


    final String ERORR_MSG = "Error: Role is not found.";

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        Customer customer = this.customerService.findCustomerById(userDetails.getCustomerTempId());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEnabled(),
                userDetails.getEmployee(),
                customer,
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }


        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));
        String newUserCode = this.codeIncrement();
        user.setUserCode(newUserCode);
        user.setEnabled(false);
        List<Customer> customerList = this.customerService.findAllNormal();
        Long newCustomerId;
        System.out.println(customerList == null);
        if (customerList.size() == 0) {
            newCustomerId = 1L;
        } else {
            newCustomerId = customerList.get(customerList.size() - 1).getCustomerId() + 1;
        }
        user.setCustomerTempId(newCustomerId);
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        this.customerService.insertCustomer(signUpRequest.getAddress(), signUpRequest.getBirthday(), newUserCode,signUpRequest.getUsername(),
                signUpRequest.getGender(), signUpRequest.getName(), signUpRequest.getNationality(), signUpRequest.getPhone(), true, signUpRequest.getPassport());

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ERORR_MSG));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(ERORR_MSG));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException(ERORR_MSG));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(ERORR_MSG));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("email/verify/{username}")
    public void sendEmail(@PathVariable Optional<String> username) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dayPlusAWeek = LocalDate.now();
        String day = formatter.format(dayPlusAWeek);

        String sendEmail = username.orElse("");
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(sendEmail);
        message.setSubject("Xác thực tài khoản.");
        message.setText("Chào " + username.orElse("") + "\n"
                + "Vui lòng xác nhận đăng ký tài khoản. " + "\n"
                + "Click đường để hoàn thành đăng ký : http://localhost:8080/api/auth/email/success/" + sendEmail);

        this.emailSender.send(message);
    }

    @GetMapping("email/success/{username}")
    public String accuracyEmail(@PathVariable Optional<String> username) {
        String getEmail = username.orElse("");
        User user = this.userRepository.findByUserName(getEmail);
        user.setEnabled(true);
        this.userRepository.save(user);
        return "Kích hoạt tài khoản thành công!";
    }

    public String codeIncrement() {
        List<Customer> customerList = this.customerService.findAllNormal();
        String code = "";
        if (customerList.isEmpty()) {
            code = "KH001";
        } else {
            Long lastId = customerList.get(customerList.size() - 1).getCustomerId();
            if (lastId < 10) {
                code = "KH00" + (lastId + 1);
            } else if (lastId < 100) {
                code = "KH0" + (lastId + 1);
            }
        }
        return code;
    }
}

//    public String autoIncrement(){
//        String code = "KH-";
//        String regex = "^KH-[0-9]{4}$";
//        int total = this.userRepository.totalOfRecordKH();
//        String id = "";
//        while (true){
//            id = code + (total +1);
//            if (id.matches(regex)){
//                break;
//            }
//            code += 0;
//            id = "";
//        }
//        return id;
//    }


