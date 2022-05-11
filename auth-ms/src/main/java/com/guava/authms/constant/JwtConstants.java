package com.guava.authms.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtConstants {

    public static final String ROLE_PREFIX = "ROLE_";

    public static final String CLAIM_AUTHORITY = "authorities";
    public static final String CLAIM_USERID = "userid";

    public static final String USERNAME_ATTR = "username";
    public static final String PASSWORD_ATTR = "password";
}
