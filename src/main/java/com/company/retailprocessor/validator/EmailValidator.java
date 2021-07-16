package com.company.retailprocessor.validator;

import com.company.retailprocessor.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String>
{
    @Autowired
    private UserService userService;

    private String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /***
     * validates email- email exist-regex check
     * @param email
     * @param context
     * @return boolean
     */
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context)
    {
        if (StringUtils.isBlank(email))
            return false;
        Pattern pat = Pattern.compile(regex);
        return email != null && !userService.ifUserExistForEmail(email) && pat.matcher(email).matches();
    }
}
