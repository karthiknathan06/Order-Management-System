package com.company.retailprocessor.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration
public class JWTUtil
{
    private String SECRET_KEY = "mysecretkey";
    /***
     * From token username is generated
     * @param token
     * @return username string
     */
    public String extractUserNameFromToken(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    /***
     * return expiration date
     * @param token
     * @return Date
     */
    public Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /***
     * returns all claims based on secret key
     * @param token
     * @return Claims
     */
    public Claims extractAllClaims(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /***
     * checks expiration date with current data
     * @param token
     * @return token expired/not
     */
    private Boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    /***
     * added meta data above subject
     * @param userDetails
     * @return String jwt token
     */
    public String generateToken(UserDetails userDetails)
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities());
        return createToken(claims, userDetails.getUsername());
    }

    /***
     * provided subject encryption based on secret key
     * @param claims
     * @param subject
     * @return String jwt token
     */
    public String createToken(Map<String, Object> claims, String subject)
    {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /***
     * Validate token against user details
     * @param token
     * @param userDetails
     * @return true/false
     */
    public Boolean validateToken(String token, UserDetails userDetails)
    {
        final String userName = extractUserNameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}



