package com.company.retailprocessor.validator;

import org.springframework.messaging.handler.annotation.Payload;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, ANNOTATION_TYPE, TYPE_USE })
@Constraint(validatedBy = UserNameValidator.class)
public @interface ValidUserName
{
    String message() default "{userName already exist}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
