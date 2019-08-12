package io.github.mimsapp.registration.app.service;

import io.github.mimsapp.registration.app.domain.User;
import io.github.mimsapp.registration.app.exception.ValidationException;
import io.github.mimsapp.registration.app.repository.UserRepository;
import io.github.mimsapp.registration.app.util.ErrorMessage;
import io.github.mimsapp.registration.app.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public void save(User user) throws ValidationException {

        if(user.getMobileNumber() == null || user.getMobileNumber().isEmpty()) {
            throw new ValidationException(ErrorMessage.MOBILE_NUMBER_MANDATORY);
        }

        if(user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new ValidationException(ErrorMessage.FIRST_NAME_MANDATORY);
        }

        if(user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new ValidationException(ErrorMessage.LAST_NAME_MANDATORY);
        }

        if(user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ValidationException(ErrorMessage.EMAIL_MANDATORY);
        }

        if(!ValidationUtil.isMobileNumberValid(user.getMobileNumber())) {
            throw new ValidationException(ErrorMessage.MOBILE_NUMBER_INVALID);
        }

        if(!ValidationUtil.isEmailValid(user.getEmail())) {
            throw new ValidationException(ErrorMessage.EMAIL_INVALID);
        }

        if(repo.countMobileNumberAppearances(user.getMobileNumber()) > 0) {
            throw new ValidationException(ErrorMessage.MOBILE_NUMBER_EXISTS);
        }

        if(repo.countEmailAppearances(user.getEmail()) > 0) {
            throw new ValidationException(ErrorMessage.EMAIL_EXISTS);
        }

        repo.save(user);
    }

    public boolean isEmailExists(String email) {

        return repo.countEmailAppearances(email) > 0;
    }

    public boolean isMobileNumberExists(String mobileNumber) {

        return repo.countMobileNumberAppearances(mobileNumber) > 0;
    }
}
