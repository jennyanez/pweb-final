package cu.edu.cujae.pweb.security;

import cu.edu.cujae.pweb.dto.UserAuthenticatedDto;
import cu.edu.cujae.pweb.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private int code;
    private String username;
    private String password;
    private String email;
    private String recoverCode;
    private boolean active;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;


    public UserPrincipal(int code,
                         String username,
                         String password,
                         String email,
                         String recoverCode,
                         boolean active,
                         String token,
                         Collection<? extends GrantedAuthority> authorities) {
        this.code = code;
        this.username = username;
        this.password = password;
        this.email = email;
        this.recoverCode = recoverCode;
        this.active = active;
        this.token = token;
        this.authorities = authorities;
    }

    public static UserPrincipal create(UserAuthenticatedDto user) {
        List<GrantedAuthority> authorities;
        try {
            Collection<String> roleNames = user.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toList());
            authorities = AuthorityUtils.createAuthorityList(roleNames.toArray(new String[0]));
        } catch (Exception e) {
            authorities = Collections.
                    singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        }
        return new UserPrincipal(
                user.getCode(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getRecoverCode(),
                true,
                user.getToken() ,
                authorities);
    }

    public static UserPrincipal create(UserAuthenticatedDto user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        return userPrincipal;
    }

    public int getCode(){
        return code;
    }
    public String getEmail(){
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRecoverCode() {
        return recoverCode;
    }

    public void setRecoverCode(String recoverCode) {
        this.recoverCode = recoverCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
