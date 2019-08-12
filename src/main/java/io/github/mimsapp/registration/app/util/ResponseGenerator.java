package io.github.mimsapp.registration.app.util;

import io.github.mimsapp.registration.app.payload.Response;

public class ResponseGenerator {

    public static <T> Response<T> getSuccessResponse(T payload) {

        Response<T> response = new Response<T>();
        response.setStatusCode(0);
        response.setDescription("Success");
        response.setPayload(payload);

        return response;
    }

    public static Response<String> getErrorResponse(String message) {

        Response<String> response = new Response<String>();
        response.setStatusCode(-1);
        response.setDescription(message);

        return response;
    }
}
