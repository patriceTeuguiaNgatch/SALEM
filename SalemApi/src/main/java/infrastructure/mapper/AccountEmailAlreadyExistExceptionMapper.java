package infrastructure.mapper;

import infrastructure.exception.AccountEmailAlreadyExistException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AccountEmailAlreadyExistExceptionMapper implements ExceptionMapper<AccountEmailAlreadyExistException> {
    private static final String ERROR = "EMAIL_ALREADY_USED";
    private static final String USERNAME = "The email ";
    private static final String MESSAGE = " is already used";

    public static class AccountEmailAlreadyExistResponse {
        public String error;
        public String message;
    }


    @Override
    public Response toResponse(AccountEmailAlreadyExistException accountEmailAlreadyExistException) {
        AccountEmailAlreadyExistResponse accountEmailAlreadyExistResponse = new AccountEmailAlreadyExistResponse();
        accountEmailAlreadyExistResponse.error =  ERROR;
        accountEmailAlreadyExistResponse.message = USERNAME + accountEmailAlreadyExistException.getEmail() + MESSAGE;
        return Response.status(Response.Status.BAD_REQUEST).entity(accountEmailAlreadyExistResponse).build();
    }
}
