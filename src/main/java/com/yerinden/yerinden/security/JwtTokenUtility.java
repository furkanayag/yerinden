package com.yerinden.yerinden.security;

import com.yerinden.yerinden.entity.User;
import com.yerinden.yerinden.model.BusinessException;
import com.yerinden.yerinden.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenUtility {

    private final SecretKey secretKey;

    public String generateAccessToken(User user) {
        Set<Role> authorities = Collections.singleton(user.getRole());
        return generateAccessToken(
                new UserSession(
                        user.getId(),
                        user.getName(),
                        user.getSurname(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.getRole(),
                        authorities));
    }

    public String generateAccessToken(UserSession userSession) {
        return Jwts.builder()
                .subject(String.format("%s", userSession.getEmail()))
                .issuedAt(new Date())
                .claim("userId", userSession.getUserId())
                .claim("name", userSession.getName())
                .claim("surname", userSession.getSurname())
                .claim("email", userSession.getEmail())
                .claim("phoneNumber", userSession.getPhoneNumber())
                .claim("role", userSession.getRole())
                .claim("authorities", userSession.getRoles())
                .signWith(secretKey)
                .compact();
    }

    Set<Role> getAuthorities(Claims claims){
        Collection<?> roles = claims.get("authorities", Collection.class);

        List<Role> authorities;
        if (null != roles) {
            ArrayList<Role> authsList = new ArrayList<>(roles.size());

            for (Object role : roles) {
                authsList.add(Role.valueOf(role.toString()));
            }

            authorities = Collections.unmodifiableList(authsList);
        } else {
            authorities = Collections.emptyList();
        }
        return new HashSet<>(authorities);
    }

    public Claims decode(String token) {
        JwtParser parser = Jwts.parser().verifyWith(secretKey).build();
        return (Claims) parser.parse(token).getPayload();
    }

    public boolean validate(String token) {
        try {
            Claims claims = decode(token);
            return claims.getSubject() != null;
        } catch (Exception e) {
            throw BusinessException.jwtValidationException();
        }
    }
}
