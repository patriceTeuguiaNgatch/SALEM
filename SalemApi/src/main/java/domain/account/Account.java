package domain.account;

import domain.contribution.Contribution;

import java.time.LocalDate;
import java.util.List;

abstract public class Account {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String telephoneNumber;
    private AccountId accountId;
    private String role;

    protected Account(AccountId accountId, String email, String password, String firstName, String lastName, String telephoneNumber, String role){
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.role = role;
    }

    public abstract List<Contribution> findContributionByPeriod(LocalDate startDate, LocalDate endDate);

    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }

    public boolean hasPassword(String password){
        return this.password.equals(password);
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public   String formateEmailTextAccountSuccessCreation(){
        String newLigne = System.getProperty("line.separator");
        String text = "Dear customer, your have successfully accomplished creating your account." + newLigne;
        text += newLigne;
        text += "---------------------------------------------------------------------------------";
        text += newLigne;
        text += "This is your information:" + newLigne;
        text += newLigne;
        text += "---------------------------------------------------------------------------------";
        text += newLigne;
        text += "First name : " + this.firstName;
        text += newLigne;
        text += "Last name : " + this.lastName;
        text += newLigne;
        text += "Email : " + this.email;
        text += newLigne;
        text += "Telephone Number : " + this.telephoneNumber;
        text += newLigne;
        text += "---------------------------------------------------------------------------------";
        text += newLigne;
        return text;
    }

}

