package com.backend.airline_tickets_agency_management.controller.employee;

import com.backend.airline_tickets_agency_management.model.dto.employee.AddRequest;
import com.backend.airline_tickets_agency_management.model.dto.employee.EmployeeDto;
import com.backend.airline_tickets_agency_management.model.dto.employee.IEmployeeDto;
import com.backend.airline_tickets_agency_management.model.entity.employee.Employee;
import com.backend.airline_tickets_agency_management.model.entity.user.ERole;
import com.backend.airline_tickets_agency_management.model.entity.user.Role;
import com.backend.airline_tickets_agency_management.model.entity.user.User;
import com.backend.airline_tickets_agency_management.model.repository.user.IUserRepository;
import com.backend.airline_tickets_agency_management.model.repository.user.RoleRepository;
import com.backend.airline_tickets_agency_management.model.service.employee.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/employee")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeRestController {
    @Autowired
    IEmployeeService service;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;
    final String ERORR_MSG = "Error: Role is not found.";

    @GetMapping("/list")
    public ResponseEntity<Page<IEmployeeDto>> getList(@RequestParam Optional<String> typeSearch,
                                                      @RequestParam Optional<String> valueSearch,
                                                      @RequestParam Optional<Integer> page) {
        String check = typeSearch.orElse("");
        if ("".equals(check)) check = "employee_code";
        Page<IEmployeeDto> employeeList = service.getAllEmployee(check, valueSearch.orElse(""), page.orElse(0));
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long id) {
        Employee employee = service.findById(id).orElse(null);
        if (employee == null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        if (!employee.isFlag()) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        employee.setFlag(false);
        service.save(employee);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = service.findById(id).orElse(null);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping(value = "/update-employee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(employeeDto, HttpStatus.NOT_FOUND);
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        service.save(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/add-employee")
    public ResponseEntity<User> addEmployee(@Valid @RequestBody AddRequest addRequest, BindingResult bindingResult) {
        if (userRepository.existsByUserName(addRequest.getUsername())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        User userDto = new User(addRequest.getUsername(), encoder.encode(addRequest.getPassword()));
        userDto.setUserCode(this.codeIncrement());
        userDto.setEnabled(true);

//        if (bindingResult.hasErrors()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        Employee employee = new Employee(addRequest.getEmployeeCode(),
                addRequest.getEmployeeName(),
                addRequest.getEmployeeBirthday(),
                addRequest.getEmployeeGender(),
                addRequest.getEmployeePhoneNumber(),
                addRequest.getEmployeeAddress(),
                addRequest.getEmployeeImage());
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        service.save(employee);
        userDto.setEmployee(employee);

        String strRoles = addRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = (Role) roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException(ERORR_MSG));
            roles.add(userRole);
        } else if (strRoles.equals("ROLE_MODERATOR")) {
            Role userRole = (Role) roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(() -> new RuntimeException(ERORR_MSG));
            roles.add(userRole);
        } else if (strRoles.equals("ROLE_ADMIN")) {
            Role userRole = (Role) roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException(ERORR_MSG));
            roles.add(userRole);
        }

        userDto.setRoles(roles);
        userRepository.save(userDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update-employee")
    public ResponseEntity<User> updateEmployee(@Valid @RequestBody AddRequest addRequest, BindingResult bindingResult) {
        if (userRepository.existsByUserName(addRequest.getUsername())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        User userDto = new User(addRequest.getUsername(), addRequest.getPassword());
        userDto.setUserCode(this.codeIncrement());
        userDto.setEnabled(true);

        Employee employee = new Employee(addRequest.getEmployeeCode(), addRequest.getEmployeeName(), addRequest.getEmployeeBirthday(), addRequest.getEmployeeGender(), addRequest.getEmployeePhoneNumber(), addRequest.getEmployeeAddress(), addRequest.getEmployeeImage());
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        service.save(employee);
        userDto.setEmployee(employee);

        String strRoles = addRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = (Role) roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException(ERORR_MSG));
            roles.add(userRole);
        } else if (strRoles.equals(ERole.ROLE_MODERATOR)) {
            Role userRole = (Role) roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(() -> new RuntimeException(ERORR_MSG));
            roles.add(userRole);
        } else if (strRoles.equals(ERole.ROLE_ADMIN)) {
            Role userRole = (Role) roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException(ERORR_MSG));
            roles.add(userRole);
        }

        userDto.setRoles(roles);
        userRepository.save(userDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public String codeIncrement() {
        List<Employee> employeeList = this.service.findAllNormal();
        String code = "";
        if (employeeList.isEmpty()) {
            code = "NV001";
        } else {
            Long lastId = employeeList.get(employeeList.size() - 1).getEmployeeId();
            if (lastId < 10) {
                code = "NV00" + (lastId + 1);
            } else if (lastId < 100) {
                code = "NV0" + (lastId + 1);
            }
        }
        return code;
    }
}