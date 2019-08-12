package io.github.mimsapp.registration.app.util;

public interface ErrorMessage {

    String MOBILE_NUMBER_EXISTS = "Mobile number is already registered";

    String MOBILE_NUMBER_MANDATORY = "Mobile number is required";

    String MOBILE_NUMBER_INVALID = "Mobile number is not valid indonesian number";

    String FIRST_NAME_MANDATORY = "First name is required";

    String LAST_NAME_MANDATORY = "Last name is required";

    String EMAIL_MANDATORY = "Email is required";

    String EMAIL_INVALID = "Email is not valid";

    String EMAIL_EXISTS = "Email is already registered";
}
