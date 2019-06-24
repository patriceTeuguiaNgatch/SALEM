package api.exception;

public class BadEmailAddressException extends Exception {

    private String email;

    public BadEmailAddressException(String email) {
    this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
