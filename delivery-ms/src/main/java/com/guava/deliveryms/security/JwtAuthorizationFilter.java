package com.guava.deliveryms.security;

import com.guava.deliveryms.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            Optional.ofNullable(request.getHeader(jwtConfig.getAuthorizationHeader()))
                    .filter(header -> header.startsWith(jwtConfig.getTokenPrefix()))
                    .map(header -> header.substring(jwtConfig.getTokenPrefix().length()))
                    .map(this::extractPayload)
                    .ifPresent(this::setAuthenticationContext);

        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token cannot be trusted"));
        }

        filterChain.doFilter(request, response);
    }

    private Claims extractPayload(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token).getBody();
    }

    private void setAuthenticationContext(Claims body) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                body.getSubject(),
                null,
                getSimpleGrantedAuthorities(body)
        ));
    }


    private Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities(Claims body) {
        var authorities = (List<Map<String, String>>) body.get("authorities");

        return authorities.stream()
                .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                .collect(Collectors.toSet());
    }
}
