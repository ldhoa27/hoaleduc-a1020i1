package com.backend.airline_tickets_agency_management.model.dto.validate;

import com.backend.airline_tickets_agency_management.model.dto.customer.CustomerDto;
import com.backend.airline_tickets_agency_management.model.entity.customer.Customer;
import com.backend.airline_tickets_agency_management.model.repository.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "Email đã tồn tại.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, CustomerDto> {
    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(CustomerDto customer, ConstraintValidatorContext constraintValidatorContext) {
        Customer customer1 = this.iCustomerRepository.findCustomerById(customer.getCustomerId());
        if (customer1 != null){
            Customer customer2 = this.iCustomerRepository.findByEmail(customer.getCustomerEmail());
            if (customer2 != null){
                if (customer2.getCustomerId() == customer1.getCustomerId()){
                    return true;
                }else {
                    return false;
                }
            }else {
                return true;
            }
        }else {
            Customer customer3 = this.iCustomerRepository.findByEmail(customer.getCustomerEmail());
            if (customer3 != null) {
                return false;
            }
            return true;
        }
    }

}