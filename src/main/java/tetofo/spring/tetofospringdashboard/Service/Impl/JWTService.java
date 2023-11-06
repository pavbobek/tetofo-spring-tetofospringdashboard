package tetofo.spring.tetofospringdashboard.Service.Impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import tetofo.spring.tetofospringdashboard.Service.IJWTService;
import tetofo.spring.tetofospringdashboard.Service.Exception.ServiceException;

/**
 * 
 * JWTService implementation.
 * 
 * @author Pavel Bobek
 * @mail pavbobek@seznam.cz
 * 
 */
public class JWTService implements IJWTService {

    private static final String SECRET_KEY = "dGV0b2Zv";    //security violation! statics keys in code! consicer moving this to DB !!!

    @Override
    public String createJWT(UserDetails userDetails) throws ServiceException {
        ServiceException.requireNonNull(userDetails, "UserDetails is null.");
        return createJWT(Collections.emptyMap(), userDetails);
    }

    @Override
    public String createJWT(Map<String, Object> claims, UserDetails userDetails) throws ServiceException {
        ServiceException.requireNonNull(claims, "Claims is null.");
        ServiceException.requireNonNull(userDetails, "UserDetails is null.");
        try {
            return Jwts.builder()
            .claims(claims)
            .subject(userDetails.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
            .signWith(getSigningKey())
            .compact();
        } catch (InvalidKeyException e) {
            throw new ServiceException("Unable to create JWT", e);
        }
        
    }

    @Override
    public String getUsername(String jwt) throws ServiceException {
        ServiceException.requireNonNull(jwt, "JWT is null.");
        return getClaim(jwt, Claims::getSubject);
    } 

    @Override
    public boolean isJWTValid(String jwt, UserDetails userDetails) throws ServiceException {
        ServiceException.requireNonNull(jwt, "JWT is null.");
        ServiceException.requireNonNull(userDetails, "UserDetails is null.");
        final String username = ServiceException.requireNonNull(getUsername(jwt), "Username is null for token %s".formatted(jwt));
        return username.equals(userDetails.getUsername()) && isTokenExpired(jwt);
    }

    private static Claims getAllClaims(String jwt) throws ServiceException {
        try {
            return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseUnsecuredClaims(jwt)
                .getPayload();
        } catch (IllegalArgumentException | JwtException e) {
            throw new ServiceException("Unable to parse token claims.", e);
        }
        
    }

    private static <T> T getClaim(String jwt, Function<Claims, T> claimsResolver) throws ServiceException {
        final Claims claims = getAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    private static Date getExpiration(String jwt) throws ServiceException {
        return getClaim(jwt, Claims::getExpiration);
    }
    
    private static SecretKey getSigningKey() throws ServiceException {
        final byte[] secretKeyBytes;
        try {
            secretKeyBytes = Decoders.BASE64.decode(SECRET_KEY);
        } catch (DecodingException e) {
            throw new ServiceException("SECRET_KEY is in invalid state.", e);
        }
        try {
            return Keys.hmacShaKeyFor(secretKeyBytes);
        } catch (WeakKeyException e) {
            throw new ServiceException("SERVICE_KEY is weak", e);
        }
    }

    private static boolean isTokenExpired(String jwt) throws ServiceException {
        return getExpiration(jwt).before(new Date());
    }

}
