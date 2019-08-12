package io.github.mimsapp.registration.app.controller;

import io.github.mimsapp.registration.app.domain.User;
import io.github.mimsapp.registration.app.exception.ValidationException;
import io.github.mimsapp.registration.app.payload.Response;
import io.github.mimsapp.registration.app.service.UserService;
import io.github.mimsapp.registration.app.util.ResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class Api {

    @Autowired
    private UserService service;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Response<String> register(@RequestBody User user) {

        Response<String> response = ResponseGenerator.getSuccessResponse("OK");;

        try {
            service.save(user);
        } catch (ValidationException e) {
            response = ResponseGenerator.getErrorResponse(e.getMessage());
        }

        return response;
    }

    @GetMapping(value = "/checkMobileNumberExistence", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Response<Boolean> checkMobileNumberExistence(
            @RequestParam("mobileNumber") String mobileNumber) {

        Boolean exists = service.isMobileNumberExists(mobileNumber);

        return ResponseGenerator.getSuccessResponse(exists);
    }

    @GetMapping(value = "/checkEmailExistence", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Response<Boolean> checkEmailExistence(
            @RequestParam("email") String email) {

        Boolean exists = service.isEmailExists(email);

        return ResponseGenerator.getSuccessResponse(exists);
    }
}
