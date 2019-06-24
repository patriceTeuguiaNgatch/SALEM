package infrastructure.account;

import domain.account.Account;
import domain.account.AccountId;
import domain.account.AccountVolunteer;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.List;

public class AccountDevDataFactory {

    public List<Account> createMockData() {

        List<Account> accounts = Lists.newArrayList();

        String emailYves = "yves@gmail.com";
        String passwordYves = "Yves";
        String firstNameYves = "Yves";
        String lastNameYves = "kem";
        AccountId accountIdYves = new AccountId(emailYves);
        String telephoneNumberYves = "5810001233";
        String roleYves = "VOLUNTEER";
        Account volunteerYves = new AccountVolunteer(accountIdYves, emailYves, passwordYves, firstNameYves, lastNameYves, telephoneNumberYves, roleYves);
        accounts.add(volunteerYves);

        String emailPat = "patrice@gmail.com";
        String passwordPat = "PatriceVolunteer";
        String firstNamePat = "PatriceVolunteer";
        String lastNamePat = "Teug";
        AccountId accountIdPat = new AccountId(emailPat);
        String telephoneNumberPat= "5810001111";
        String rolePat = "VOLUNTEER";
        Account volunteerPat = new AccountVolunteer(accountIdPat, emailPat, passwordPat, firstNamePat, lastNamePat, telephoneNumberPat, rolePat);
        accounts.add(volunteerPat);

        String emailPatAdmin = "pateuguia@gmail.com";
        String passwordPatAdmin = "PatriceAdmin";
        String firstNamePatAdmin = "PatricepatriceAdmin";
        String lastNamePatAdmin = "Teug";
        String telephoneNumberAdmin = "5819221233";
        AccountId accountIdPatAdmin = new AccountId(emailPatAdmin);
        String role = "ADMIN";
        Account admin = new AccountVolunteer(accountIdPatAdmin, emailPatAdmin, passwordPatAdmin, firstNamePatAdmin, lastNamePatAdmin, telephoneNumberAdmin, role);
        accounts.add(admin);

        return accounts;
    }
}
