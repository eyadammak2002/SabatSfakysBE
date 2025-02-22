package tn.SabatSfakys.security.jwt;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import tn.SabatSfakys.security.services.UserDetailsImpl;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  //@Value("${bezkoder.app.jwtSecret}")
  //private String jwtSecret;

 @Value("${bezkoder.app.jwtExpirationMs}")
  private int jwtExpirationMs;
  
  private static final String SECRET_KEY="404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

  /*private SecretKey getSigningKey() {
	  
	   return Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}*/
  private Key getSignKey() {
	  
      byte[] KeyBytes= Decoders.BASE64.decode(SECRET_KEY);
      return Keys.hmacShaKeyFor(KeyBytes);
  }
  
  public String generateJwtToken(Authentication authentication) {
      UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
      
      // Generate JWT Token
      return Jwts.builder()
              .setSubject(userPrincipal.getUsername())
              .setIssuedAt(new Date())
              .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
              .signWith(getSignKey(), SignatureAlgorithm.HS256)
              .compact();
  }
  



  public String getUserNameFromJwtToken(String token) {
      return Jwts.parserBuilder()
              .setSigningKey(getSignKey())
              .build()
              .parseClaimsJws(token)
              .getBody()
              .getSubject();
  }

  public boolean validateJwtToken(String authToken) {
      try {
    	  Key key = getSignKey();
          Jwts.parserBuilder()
              .setSigningKey(key)
              .build()
              .parseClaimsJws(authToken);
          return true;
      } catch (Exception e) {
          logger.error("JWT validation failed: {}", e.getMessage());
      }
      return false;
  }
}