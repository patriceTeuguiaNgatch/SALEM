package api.security;

import java.security.Principal;

public class JWTPrincipal implements Principal {
    private String accountNumber;
    private String email;
    private String role;

    public JWTPrincipal(String accountNumber, String email, String role) {
        this.accountNumber = accountNumber;
        this.email = email;
        this.role = role;
    }

    @Override
    public String getName() {
        return accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setName(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("JWTPrincipal {")
                .append("accountNumber:").append(accountNumber).append(",")
                .append("email:").append(email).append(",")
                .append("role:").append(role).append(",")
                .append("}");

        return builder.toString();
    }
}