package api;

import api.exception.BadEmailAddressException;
import api.exception.BadPasswordException;
import org.apache.commons.validator.routines.EmailValidator;

public class AccountResourceValidator {

    public AccountResourceValidator(){
    }

    public void validateVolunteerAccountCreationParameters(String email, String password) throws Exception {
        this.validateEmail(email);
        this.validatePassword(password);
    }

    private void validateEmail(String email) throws BadEmailAddressException {
        if(!EmailValidator.getInstance(true).isValid(email)) {
            throw new BadEmailAddressException(email);
        }
    }

    private void validatePassword(String password) throws BadPasswordException {
        if(password.isEmpty()) {
            throw new BadPasswordException(password);
        }
    }
}
