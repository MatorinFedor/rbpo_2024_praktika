package ru.mtuci.rbpo_2024_praktika.configuration;

import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    //TODO: реализовать методы создания, валидации и получения информации из JWT токена
    public String createToken(String username, Set<GrantedAuthority> authorities) {}
    public boolean validateToken(String token) {}
    public String getUsername(String token) {}
    public Set<GrantedAuthority> getAuthorities(String token) {}
}
