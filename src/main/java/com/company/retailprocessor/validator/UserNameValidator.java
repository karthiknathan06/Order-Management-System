package com.company.retailprocessor.validator;

import com.company.retailprocessor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.temporal.ValueRange;

public class UserNameValidator implements ConstraintValidator<ValidUserName, String>
{
    @Autowired
    private UserService userService;

    /***
     * To validate username- exist or not
     * @param name
     * @param context
     * @return boolean flag
     */
    @Override
    public boolean isValid(String name, ConstraintValidatorContext context)
    {
        ValueRange range = ValueRange.of(1, 20);
        return name!=null && range.isValidValue(name.length()) && !userService.ifUserExistForUserName(name);
    }
}
