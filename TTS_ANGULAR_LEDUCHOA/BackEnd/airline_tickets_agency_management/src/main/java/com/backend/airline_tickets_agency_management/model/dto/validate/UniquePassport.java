package com.backend.airline_tickets_agency_management.model.dto.validate;

import com.backend.airline_tickets_agency_management.model.entity.customer.Customer;
import com.backend.airline_tickets_agency_management.model.repository.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniquePassportValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePassport {
    String message() default "Passport đã tồn tại.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
class UniquePassportValidator implements ConstraintValidator<UniquePassport, String> {
    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public void initialize(UniquePassport constraintAnnotation) {

    }

    @Override
    public boolean isValid(String passport, ConstraintValidatorContext constraintValidatorContext) {
        Customer customer = this.iCustomerRepository.findByPassport(passport);
        if (customer != null) {
            return false;
        }
        return true;
    }

}