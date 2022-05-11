package com.guava.authms.mapper;

import static com.guava.authms.constant.JwtConstants.ROLE_PREFIX;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class MapperHelper {

    private final PasswordEncoder passwordEncoder;

    @Named("encodePassword")
    public String encodePassword(String password) {

        return passwordEncoder.encode(password);
    }

    @Named("mapRoleToGrantedAuthority")
    public List<GrantedAuthority> mapRoleToGrantedAuthority(String role) {

        return List.of(new SimpleGrantedAuthority(ROLE_PREFIX + role));
    }

}
