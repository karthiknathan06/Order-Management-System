package com.company.retailprocessor.validator;

import org.springframework.data.mongodb.core.mapping.Document;
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
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidEmail
{
    String message() default "{email.invalid or already exist}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}