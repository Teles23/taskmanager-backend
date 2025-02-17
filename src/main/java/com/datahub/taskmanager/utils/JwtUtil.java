package com.datahub.taskmanager.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "minhaSuperChaveSecretaDeSegurancaJWT123!";

    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes()); // Converte para uma chave segura
    private static final long EXPIRATION_TIME = 86400000; // 1 dia (em milissegundos)

    private JwtUtil() {

    }

    // Gera um token JWT atualizado
    public static String generateToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) // ✅ NOVO MÉTODO
                .compact();
    }

    // Valida um token JWT
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY) // ✅ NOVO MÉTODO
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Obtém o nome de usuário a partir do token
    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // ✅ NOVO MÉTODO
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
