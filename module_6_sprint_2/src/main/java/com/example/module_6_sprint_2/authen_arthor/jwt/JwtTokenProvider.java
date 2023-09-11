package com.example.module_6_sprint_2.authen_arthor.jwt;

import com.example.module_6_sprint_2.authen_arthor.security.CustomUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String JWT_SECRET;
    @Value("${jwt.expiration}")
    private int JWT_EXPIRATION;
    // Tạo ra token từ jwt username của account
    public String generateToken(CustomUserDetail customUserDetail){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+JWT_EXPIRATION);
        //Tao chuoi JWT từ username của account
        return Jwts.builder()
                .setSubject(customUserDetail.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,JWT_SECRET)
                .compact();
    }
    // lấy username từ token
        public String getUsernameFromJwt(String token){
            Claims claims = Jwts.parser().setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token).getBody();
            return claims.getSubject();
        }
        public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){

        }
        return false;
        }


}
