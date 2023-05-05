package com.sysmap.demo.services.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService implements IJwtService {

    private final long EXPIRATION_TIME = 7200000;
    private final String KEY = "28482B4D6251655468566D597133743677397A24432646294A404E635266556A";
    public String generateToken(UUID userId) {
        return Jwts
                .builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(genSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isValidToken(String token, String userId) {
        var claims = Jwts
                        .parserBuilder()
                        .setSigningKey(genSignInKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

        var sub = claims.getSubject();
        var tExpiration = claims.getExpiration();

        return (sub.equals(userId) && !tExpiration.before(new Date()));
    }
    private Key genSignInKey() {

        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
    }
}
