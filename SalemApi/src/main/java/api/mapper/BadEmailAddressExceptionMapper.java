package api.mapper;

import api.exception.BadEmailAddressException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class BadEmailAddressExceptionMapper implements ExceptionMapper<BadEmailAddressException> {

    private static final String ERROR = "BAD_EMAIL_ADDRESS";
    private static final String THE_ADDRESS = "The address ";
    private static final String MESSAGE = " is invalid";

    public static class BadEmailAddressResponse {
        public String error;
        public String message;
    }

    @Override
    public Response toResponse(BadEmailAddressException badEmailAddressException) {
        BadEmailAddressResponse badEmailAddressResponse = new BadEmailAddressResponse();
        badEmailAddressResponse.error = ERROR;
        badEmailAddressResponse.message = THE_ADDRESS + (badEmailAddressException.getEmail()) + MESSAGE;
        return Response.status(Response.Status.BAD_REQUEST).entity(badEmailAddressResponse).build();
    }
}
