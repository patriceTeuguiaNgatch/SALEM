package api.security.token;

import api.security.JWTPrincipal;
import domain.account.Account;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class JwTokenHelper {
    private static JwTokenHelper jwTokenHelper = null;
    private static final long EXPIRATION_LIMIT = 30;
    private static final String EMAIL = "email";
    private static final String ROLE = "role";
    private static final String TOKEN_EXPIRED =  "Unauthorized: too late, token expired";
    private static final String TOKEN = "Authorization";
    private static final String IS_INVALID = "token:";

    private static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final Logger log = Logger.getLogger(String.valueOf(JwTokenHelper.class));

    private JwTokenHelper() { }

    public static JwTokenHelper getInstance() {
        if (jwTokenHelper == null)
            jwTokenHelper = new JwTokenHelper();
        return jwTokenHelper;
    }

    public String generatePrivateKey(Account account) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(account.getAccountId()));
        claims.put(EMAIL, account.getEmail());
        claims.put(ROLE, account.getRole());

        return Jwts
                .builder()
                .setClaims(claims)
                .setExpiration(getExpirationDate())
                .signWith(key)
                .compact();
    }

    public static JWTPrincipal buildPrincipal(final Claims claims) {
        JWTPrincipal principal = null;

        try {
            if (claims != null) {
                String accountNumber  = claims.getSubject();
                String email  = (String) claims.get(EMAIL);
                String role  = (String) claims.get(ROLE);

                principal = new JWTPrincipal(accountNumber, email, role);
            }
        } catch (Exception e) {
            log.warning(e.getMessage());
        }
        return principal;
    }

    public static Claims validateToken( String token ) throws Exception {
        Claims claims = extractClaimsFromToken(token);
        if (claimsIsExpired(claims)) {
            throw new NotAuthorizedException(
                    TOKEN_EXPIRED,
                    Response.status(Response.Status.UNAUTHORIZED));
        }
        return claims;
    }

    private static Claims extractClaimsFromToken(String token){
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setAllowedClockSkewSeconds(30)
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            throw new NotAuthorizedException(TOKEN + token + IS_INVALID);
        }
        return claims;
    }

    private static boolean claimsIsExpired(Claims claims) {
        boolean isExpired = claims.getExpiration().before(new Date());
        return isExpired;
    }
    
    private Date getExpirationDate() {
        long currentTimeMillis = System.currentTimeMillis();
        long expMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT);
        return new Date(currentTimeMillis + expMilliSeconds);
    }
}