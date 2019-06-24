package service.account;

import domain.account.Account;
import domain.account.AccountAdmin;
import domain.account.AccountId;
import domain.account.AccountVolunteer;

public class AccountFactory {

    public static String ROLE_ADMIN = "admin";
    public static String ROLE_VOLUNTEER = "volunteer";

    private AccountIdGenerator accountIdGenerator;

    public AccountFactory(AccountIdGenerator accountIdGenerator){
        this.accountIdGenerator = accountIdGenerator;
    }

    public Account create(String email, String password, String firstName, String lastName, String telephoneNumber, String role) {
        AccountId accountId = new AccountId(email);
        if (role.equals(ROLE_ADMIN)) {
            return new AccountAdmin(accountId, email, password, firstName, lastName, telephoneNumber, ROLE_ADMIN);
        } else if (role.equals(ROLE_VOLUNTEER)) {
            return new AccountVolunteer(accountId, email, password, firstName, lastName, telephoneNumber, ROLE_VOLUNTEER);
        }
        return null;
    }
}
