package cu.edu.cujae.pweb.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserUtils {

    public CurrentUserUtils() {
        super();
    }

    private static final String ANONYMOUS_USER = "anonymousUser";

    public static String getUsername() {
        String username = null;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            username = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        }
        return username;
    }

    public static String getEmail() {
        String email = null;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            email = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        }
        return email;
    }

    public static String getTokenBearer() {
        String token = null;
        if (isLogged()) {
            token = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getToken();
        }
        return token;
    }

    public static String getUserId() {
        String userId = null;
        if (isLogged()) {
            userId = String.valueOf(((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCode());
        }
        return userId;
    }

    public static boolean isLogged() {
        boolean logged = false;
        if ((SecurityContextHolder.getContext().getAuthentication() != null) && (!getUsername().equals(ANONYMOUS_USER))) {
            logged = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        }
        return logged;
    }

    public static String getPassword() {
        String password = null;
        if (isLogged()) {
            password = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        }
        return password;
    }

}
