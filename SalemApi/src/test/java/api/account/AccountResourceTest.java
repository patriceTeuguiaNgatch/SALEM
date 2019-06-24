package api.account;

import api.AccountResource;
import api.AccountResourceValidator;
import api.dto.VolunteerAccountDto;
import domain.account.Account;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import service.account.AccountService;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

public class AccountResourceTest {

    private static final String EMAIL = "pateuguia@yahoo.com";
    private static final String PASSWORD = "PatriceAdmin";
    private static final String ACCOUNT_ID = "pateu";
//    private static final String FIRST_NAME = "Patrice";
//    private static final String LAST_NAME = "Teug";
//    private static final String TELEPHONE_NUMBER = "5819221233";
//    private static final String ROLE = "VOLUNTEER";
//    private AccountId accountIMock;

    private Account accountMock;
    private AccountService accountServiceMock;
    private AccountResourceValidator accountResourceValidatorMock;
    private VolunteerAccountDto volunteerAccountDto;



//    private Account accountVolunteer;
    private AccountResource accountResource;


    @Before
    public void setUp(){

        this.volunteerAccountDto = new VolunteerAccountDto();
        this.volunteerAccountDto.email = EMAIL;
        this.volunteerAccountDto.password = PASSWORD;

        this.accountMock = mock(Account.class);
        this.accountServiceMock = mock(AccountService.class);
        this.accountResourceValidatorMock = mock(AccountResourceValidator.class);
        this.accountResource = new AccountResource(accountServiceMock, accountResourceValidatorMock);
    }

    @Test
    public void givenVolunteerAccountInformation_whenCreatingNewVolunteerAccount_thenValidatedVolunteerAccountCreationParameters() throws Exception {

        accountResource.createVolunteerAccount(volunteerAccountDto);

        verify(accountResourceValidatorMock, times(1)).validateVolunteerAccountCreationParameters(volunteerAccountDto.email, volunteerAccountDto.password);
    }

    @Test
    public void givenVolunteerAccountInformation_whenCreatingNewVolunteerAccount_thenAccountServiceShouldCreatedVolunteerAccount() throws Exception {

        doNothing().when(accountResourceValidatorMock).validateVolunteerAccountCreationParameters(volunteerAccountDto.email, volunteerAccountDto.password);
        accountResource.createVolunteerAccount(volunteerAccountDto);

        verify(accountServiceMock, times(1)).createVolunteerAccount(volunteerAccountDto);
    }

    @Test
    public void givenVolunteerAccountInformation_whenCreatingNewVolunteerAccount_thenAOKResponseIsReturned() throws Exception {

        doNothing().when(accountResourceValidatorMock).validateVolunteerAccountCreationParameters(volunteerAccountDto.email, volunteerAccountDto.password);
        willReturn(ACCOUNT_ID).given(accountServiceMock).createVolunteerAccount(volunteerAccountDto);

        Response response =  accountResource.createVolunteerAccount(volunteerAccountDto);
        assertEquals(HttpStatus.OK_200, response.getStatus());
    }
}
