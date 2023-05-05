package com.sysmap.demo.services.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.kafka.common.protocol.types.Field;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

public class JwtService implements IJwtService {

    private final long EXPIRATION_TIME = 7200000;
    private final String KEY = "9z$C&F)H@McQfTjWnZr4u7x!A%D*G-Ka\n";
    public String generateToken(UUID userId) {
        return Jwts
                .builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(genSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key genSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
    }
}
