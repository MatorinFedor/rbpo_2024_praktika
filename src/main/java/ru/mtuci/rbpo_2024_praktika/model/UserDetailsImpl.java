package ru.mtuci.rbpo_2024_praktika.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Data
public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;
    private boolean isActive;

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromApplicationUser(ApplicationUser user) {
        return new User(
                user.getEmail(),
                user.getPassword(),
                user.getRole().getGrantedAuthorities()
        );
    }
}
