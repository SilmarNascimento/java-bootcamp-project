package com.silmarfnascimento.bootcampproject.security;

import io.jsonwebtoken.*;

public class JWTCreator {

  public static final String HEADER_AUTHORIZATION = "Authorization";

  public static String create(String prefix, String key, JWTObject jwtObject) {
    String token = Jwts.builder()
        .setSubject(jwtObject.getUsername())
        .setIssuedAt(jwtObject.getCreatedAt())
        .setExpiration(jwtObject.getExpiresAt())
        .signWith(SignatureAlgorithm.HS256, key)
        .compact();
    return prefix + " " + token;
  }

  public static JWTObject create(
      String authToken,
      String prefix, String key
  ) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
    String token = authToken.replace(prefix, "").trim();
    Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

    JWTObject object = new JWTObject(claims.getSubject());
    object.setExpiresAt(claims.getExpiration());
    object.setCreatedAt(claims.getIssuedAt());
    return object;

  }
}
