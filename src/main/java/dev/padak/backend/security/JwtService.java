package dev.padak.backend.security;

import dev.padak.backend.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {

    private static final String SECRET_KEY = "BArHrbA7NgzvO78EoM1scdKj3eBlseeQ9jhh6TAyJWIDxXe3gTXD9EzfPdrZ5EVm";
    private static final Integer TOKEN_DURATION = 1440000;

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String jwt, UserEntity userEntity){
        final String username = extractUsername(jwt);
        return username.equals(userEntity.getUsername()) && !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    public String generatetoken(UserEntity userEntity){
        return generateToken(userEntity);
    }

    public String generateToken(UserEntity userEntity) {

        List<String> roles = userEntity.getAuthorities().stream()
                .map(authority -> ((SimpleGrantedAuthority) authority).getAuthority())
                .collect(Collectors.toList());

        return Jwts
                .builder()
                .claim("roles", roles)
                .setSubject(userEntity.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_DURATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
