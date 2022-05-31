package com.joaoandrade.delivery.core.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Autowired
    private JwtConfigProperty jwtConfigProperty;


    public String gerarTokenJwt(Long id) {
        return Jwts.builder().setSubject(id.toString()).setExpiration(new Date(System.currentTimeMillis() + jwtConfigProperty.getTempoDeExpiracaoTokenJwt())).signWith(SignatureAlgorithm.HS512, jwtConfigProperty.getSenhaToken().getBytes()).compact();
    }

    public Long getSubject(String tokenJwt) {
        Claims claims = getClaims(tokenJwt);

        if (claims != null && claims.getSubject() != null) {
            Long id;
            try {
                id = Long.parseLong(claims.getSubject());

                return id;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        return null;
    }

    public boolean isTokenJwtValido(String tokenJwt) {
        Claims claims = getClaims(tokenJwt);

        if (claims != null) {
            Long id;
            try {
                id = Long.parseLong(claims.getSubject());
            } catch (Exception e) {
                id = null;
            }
            Date now = new Date();
            Date expiration = claims.getExpiration();

            if (id != null && expiration != null && now.before(expiration)) {
                return true;
            }
        }

        return false;
    }

    private Claims getClaims(String tokenJwt) {
        try {
            return Jwts.parser().setSigningKey(jwtConfigProperty.getSenhaToken().getBytes()).parseClaimsJws(tokenJwt).getBody();
        } catch (Exception e) {
            return null;
        }
    }


}
