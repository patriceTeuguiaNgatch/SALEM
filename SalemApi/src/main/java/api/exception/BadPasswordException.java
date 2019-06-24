package api.exception;

public class BadPasswordException extends Exception {

    private String password;

    public BadPasswordException(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
