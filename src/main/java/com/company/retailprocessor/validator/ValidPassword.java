package com.company.retailprocessor.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidPassword
{

    String message() default "Invalid Password. Password must contain minimum 8 characters with upper case, special characters and numbers";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}