package service.mapper;

import service.exception.AddressMailUnreachableException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AddressMailUnreachableExceptionMapper implements ExceptionMapper<AddressMailUnreachableException> {
    private static final String ERROR = "UNREACHABLE_ADDRESS";
    private static final String ADDRESS = "The address ";
    private static final String INVALID = " is unreachable";

    public static class AddressMailUnreachableResponse {
        public String error;
        public String message;
    }

    @Override
    public Response toResponse(AddressMailUnreachableException addressMailUnreachableException) {
        AddressMailUnreachableResponse addressMailUnreachableResponse = new AddressMailUnreachableResponse();
        addressMailUnreachableResponse.error = ERROR;
        addressMailUnreachableResponse.message =  ADDRESS + (addressMailUnreachableException.getEmail())+INVALID;
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(addressMailUnreachableResponse).build();
    }
}
