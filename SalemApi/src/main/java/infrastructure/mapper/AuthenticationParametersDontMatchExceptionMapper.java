package infrastructure.mapper;

import infrastructure.exception.AccountEmailAlreadyExistException;
import infrastructure.exception.AuthenticationParametersDontMatchException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AuthenticationParametersDontMatchExceptionMapper implements ExceptionMapper<AuthenticationParametersDontMatchException> {
    private static final String ERROR = "AUTHENTICATION_PARAMETERS_DONT_MATCH";
    private static final String MESSAGE = "Email and password don't match";

    public static class AuthenticationParametersDontMatchResponse {
        public String error;
        public String message;
    }


    @Override
    public Response toResponse(AuthenticationParametersDontMatchException authenticationParametersDontMatchException) {
        AuthenticationParametersDontMatchResponse authenticationParametersDontMatchResponse = new AuthenticationParametersDontMatchResponse();
        authenticationParametersDontMatchResponse.error =  ERROR;
        authenticationParametersDontMatchResponse.message = MESSAGE;
        return Response.status(Response.Status.UNAUTHORIZED).entity(authenticationParametersDontMatchResponse).build();
    }
}
