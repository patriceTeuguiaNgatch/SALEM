package api;


import api.dto.AccountLoginCredentialsDto;
import api.dto.VolunteerAccountDto;
import api.security.token.JwTokenHelper;
import domain.account.Account;
import service.account.AccountService;
import service.dto.AccountsListDtos;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON})

public class AccountResource {

    private AccountService accountService;
    private AccountResourceValidator accountResourceValidator;

    public AccountResource(AccountService accountService, AccountResourceValidator accountResourceValidator){
        this.accountService = accountService;
        this.accountResourceValidator = accountResourceValidator;
    }

    @POST
    @Path("/volunteer/create")
    public Response createVolunteerAccount(VolunteerAccountDto volunteerAccountDto) throws Exception {
        accountResourceValidator.validateVolunteerAccountCreationParameters(volunteerAccountDto.email, volunteerAccountDto.password);
        String accountId = this.accountService.createVolunteerAccount(volunteerAccountDto);
        return Response.ok(accountId).build();
    }

    @POST
    @Path("/login")
    public Response authentificateAccount(AccountLoginCredentialsDto accountLoginCredentialsDto) throws Exception {
        Account account = this.accountService.authentificateAccount(accountLoginCredentialsDto);
        String token = JwTokenHelper.getInstance().generatePrivateKey(account);
        return Response.ok(token).build();
    }

    @GET
    @Path("/collection")
    public Response findAllAccount(){
        AccountsListDtos accountsListDtos = this.accountService.findAllAccount();
        return Response.ok(accountsListDtos).build();
    }
}
