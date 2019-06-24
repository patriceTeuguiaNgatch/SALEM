package api.security;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class JWTSecurityContext implements SecurityContext {
    private JWTPrincipal principal;
    private boolean  isSecure;
    private String role;

    public JWTSecurityContext(JWTPrincipal principal, boolean isSecure) {
        this.principal = principal;
        this.isSecure = isSecure;
        this.role = principal.getRole();
    }

    @Override
    public Principal getUserPrincipal() {
        return principal;
    }

    @Override
    public boolean isUserInRole(String role) {
        return this.role.equalsIgnoreCase(role);
    }

    @Override
    public boolean isSecure() {
        return isSecure;
    }

    @Override
    public String getAuthenticationScheme() {
        return "BEARER";
    }
}