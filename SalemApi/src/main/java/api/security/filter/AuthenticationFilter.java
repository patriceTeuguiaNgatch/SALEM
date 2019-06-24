package api.security.filter;

import api.security.Secured;
import api.response.ResponseBuilder;
import api.security.JWTPrincipal;
import api.security.JWTSecurityContext;

import api.security.token.JwTokenHelper;
import io.jsonwebtoken.Claims;;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String ACCESS_DENIED = "Not allowed to access this resource!";

    final static Logger logger = Logger.getLogger(String.valueOf(AuthenticationFilter.class));

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method method = resourceInfo.getResourceMethod();
        if (!method.isAnnotationPresent(PermitAll.class)) {
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(
                        ResponseBuilder.createResponse(Response.Status.FORBIDDEN, ACCESS_DENIED));
                return;
            }

            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
            final List<String> authorization = headers.get( AUTHORIZATION_PROPERTY );

            if (authorization == null || authorization.isEmpty()) {
                logger.info("No token provided!");
                requestContext.abortWith(
                        ResponseBuilder.createResponse(Response.Status.UNAUTHORIZED, ACCESS_DENIED)
                );
                return;
            }
            
            String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {

                final String token = authorizationHeader.substring(BEARER.length()).trim();

                if (token != null) {
                    try {
                        Claims claims = JwTokenHelper.validateToken(token);
                        JWTPrincipal principal = JwTokenHelper.buildPrincipal(claims);

                        if (principal != null) {
                            JWTSecurityContext securityContext = new JWTSecurityContext(
                                    principal,
                                    requestContext.getSecurityContext().isSecure());
                            requestContext.setSecurityContext(securityContext);

                            if( method.isAnnotationPresent( RolesAllowed.class ) ){
                                RolesAllowed rolesAnnotation = method.getAnnotation( RolesAllowed.class );
                                Set<String> rolesSet = new HashSet<String>( Arrays.asList( rolesAnnotation.value() ) );

                                if(!this.isUserAllowed(principal,rolesSet)){
                                    logger.info("User does not have the right to access this resource!");
                                    requestContext.abortWith(
                                            ResponseBuilder.createResponse( Response.Status.UNAUTHORIZED, ACCESS_DENIED )
                                    );
                                    return;
                                }
                            }
                        } else {
                            logger.info("Unauthorized: Unable to extract claims from JWT");
                            requestContext.abortWith(
                                    ResponseBuilder.createResponse( Response.Status.UNAUTHORIZED, ACCESS_DENIED )
                            );
                            return;
                        }
                    } catch (Exception e) {
                        logger.info("Invalid token provided!");
                        requestContext.abortWith(
                                ResponseBuilder.createResponse(Response.Status.UNAUTHORIZED, ACCESS_DENIED)
                        );
                        return;
                    }

                } else {
                    logger.info("Unauthorized: Unable to parse Bearer token");
                    requestContext.abortWith(
                            ResponseBuilder.createResponse(Response.Status.UNAUTHORIZED, ACCESS_DENIED)
                    );
                    return;
                }
            }   else{
                logger.info("Unauthorized: No Authorization header was found");
                requestContext.abortWith(
                        ResponseBuilder.createResponse(Response.Status.UNAUTHORIZED, ACCESS_DENIED)
                );
                return;
            }
        }
    }

    private boolean isUserAllowed( final JWTPrincipal principal, final Set<String> rolesSet ) {
        boolean isAllowed = false;
        for (String role: rolesSet)  {
            if( role.equalsIgnoreCase(principal.getRole())){
               isAllowed = true;
               break;
            }
        }
        return  isAllowed;
    }
}