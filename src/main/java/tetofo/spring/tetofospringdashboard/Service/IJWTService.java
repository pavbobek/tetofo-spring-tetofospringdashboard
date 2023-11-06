package tetofo.spring.tetofospringdashboard.Service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import tetofo.spring.tetofospringdashboard.Service.Exception.ServiceException;

/**
 * 
 * IJWTService for Security.
 * 
 * @author Pavel Bobek
 * @mail pavbobek@seznam.cz
 * 
 */
public interface IJWTService {
    /**
     * 
     * Create JWT from userDetails.
     * 
     * @param userDetails used userDetails
     * @return  JWT
     * @throws ServiceException on failure
     * 
     */
    public String createJWT(UserDetails userDetails) throws ServiceException;
    /**
    * 
    * Create JWT from userDetails with additional claims.
    *
    * @param claims additional claims used
    * @param userDetails used userDetails
    * @return JWT
    * @throws ServiceException on failure
    *
    */
    public String createJWT(Map<String, Object> claims, UserDetails userDetails) throws ServiceException;
    /**
     * 
     * Get username from JWT.
     * 
     * @param jwt used JWT
     * @return username
     * @throws ServiceException on failure
     * 
     */
    public String getUsername(String jwt) throws ServiceException;
    /**
     * 
     * Check if JWT is valid.
     * 
     * @param jwt tested JWT
     * @param userDetails referenced userDetails
     * @return true if JWT is valid false otherwise
     * @throws ServiceException on failure
     */
    public boolean isJWTValid(String jwt, UserDetails userDetails) throws ServiceException;
}
