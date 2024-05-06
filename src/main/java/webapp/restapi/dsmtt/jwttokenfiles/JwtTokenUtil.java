package webapp.restapi.dsmtt.jwttokenfiles;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenUtil {

	public static final int JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	private final String SECRET = "Yb6gYDyeRV4ejyjDb0G6mWpJdKo4xZG0mWjB8kngFJw8cOTMbLOr0tJdSgcT9dmJkz7tQO2dOzluUqjO0Z0h6Q==";
	
	public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
	
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET.getBytes()).build().parseClaimsJws(token).getBody();
    }
    
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
    	log.info("Generating JWT token for user: {}", userDetails.getUsername());
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }
    
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS512).compact();
    }
    
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        log.debug("Validating JWT token for user: {}", username);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
}
