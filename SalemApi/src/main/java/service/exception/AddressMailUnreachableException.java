package service.exception;

public class AddressMailUnreachableException extends Exception{
    private String email;

    public AddressMailUnreachableException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
