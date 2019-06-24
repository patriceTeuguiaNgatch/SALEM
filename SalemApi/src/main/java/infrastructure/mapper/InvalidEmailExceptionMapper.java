package infrastructure.mapper;

import javax.ws.rs.ext.ExceptionMapper;
import infrastructure.exception.InvalidEmailException;

import javax.ws.rs.core.Response;

public class InvalidEmailExceptionMapper implements ExceptionMapper<InvalidEmailException> {
    private static final String ERROR = "INVALID_EMAIL";
    private static final String EMAIL = "the ";
    private static final String MESSAGE = " is not recognized";

    public static class InvalidEmailResponse {
        public String error;
        public String message;
    }

    @Override
    public Response toResponse(InvalidEmailException invalidEmailException) {
        InvalidEmailResponse invalidEmailResponse = new InvalidEmailResponse();
        invalidEmailResponse.error = ERROR;
        invalidEmailResponse.message = EMAIL + invalidEmailException.getEmail() + MESSAGE;
        return Response.status(Response.Status.UNAUTHORIZED).entity(invalidEmailResponse).build();
    }
}