package com.ibandorta.FinanciaBank.FinanciaBank.config;

import com.ibandorta.FinanciaBank.FinanciaBank.model.CustomUserDetails;
import com.ibandorta.FinanciaBank.FinanciaBank.model.User;
import com.ibandorta.FinanciaBank.FinanciaBank.model.UserRole;
import com.ibandorta.FinanciaBank.FinanciaBank.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.catalina.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private  String SECRET_KEY;

    private Key getSignigKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(UserDetails userDetails) {
        CustomUserDetails customUser = (CustomUserDetails) userDetails;

        String role = customUser.getUser().getRoles().stream()
                .map(userRole -> userRole.getName().name()) // aquí NO hay conflicto
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(customUser.getUsername())
                .claim("role", role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
                .signWith(getSignigKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignigKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}