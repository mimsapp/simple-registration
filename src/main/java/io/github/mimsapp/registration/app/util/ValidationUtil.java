package io.github.mimsapp.registration.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    public static boolean isEmailValid(String email) {

        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isMobileNumberValid(String mobileNumber) {

        String regex = "^(08)(1|2|3|5|7|8|9)(([0-9]{7})|([0-9]{9}))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mobileNumber);

        return matcher.matches();
    }
}
