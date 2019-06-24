package infrastructure.exception;

public class InvalidEmailException extends Exception {
    private String email;

    public InvalidEmailException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
