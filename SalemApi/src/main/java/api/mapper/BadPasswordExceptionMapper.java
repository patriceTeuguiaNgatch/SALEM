package api.mapper;

import api.exception.BadPasswordException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class BadPasswordExceptionMapper implements ExceptionMapper<BadPasswordException> {

    private static final String ERROR = "BAD_PASSWORD_ADDRESS";
    private static final String THE_PASSWORD = "The password ";
    private static final String MESSAGE = " is invalid";

    public static class BadPasswordResponse {
        public String error;
        public String message;
    }

    @Override
    public Response toResponse(BadPasswordException badPasswordException) {
        BadPasswordResponse badPasswordResponse = new BadPasswordResponse();
        badPasswordResponse.error = ERROR;
        badPasswordResponse.message = THE_PASSWORD + badPasswordException.getPassword() + MESSAGE;
        return Response.status(Response.Status.BAD_REQUEST).entity(badPasswordResponse).build();
    }
}
