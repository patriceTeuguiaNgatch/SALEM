package infrastructure.exception;

public class AccountEmailAlreadyExistException extends Exception {

    private  String email;

    public AccountEmailAlreadyExistException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
