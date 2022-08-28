package br.com.stefaninifood.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenJwtService {

    @Value("${foodstefanini.jwt.expiration}")
    private String expiration;

    @Value("${foodstefanini.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        UserDetailsImpl cliente = (UserDetailsImpl) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiration = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API FoodStefanini")
                .setSubject(Integer.toString(cliente.getId()))
                .setIssuedAt(hoje)
                .setExpiration(dataExpiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getIdCliente(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Integer.parseInt(body.getSubject());
    }
}
