package io.github.mimsapp.registration.test.util;

import io.github.mimsapp.registration.app.domain.User;

public class UserFactory {

    public static User getUser(String mobileNumber, String firstName, String lastName,
                   String dateOfBirth, String gender, String email) {

        User user = new User();
        user.setMobileNumber(mobileNumber);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(dateOfBirth);
        user.setGender(gender);

        return user;
    }
}
