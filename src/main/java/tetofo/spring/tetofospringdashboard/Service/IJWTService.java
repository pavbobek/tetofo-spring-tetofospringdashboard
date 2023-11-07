package tetofo.spring.tetofospringdashboard.Service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import tetofo.spring.tetofospringdashboard.Service.DTO.Impl.DataDTO;
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
     * Create JWT from user DataDTO.
     * 
     * @param userDataDTO used user DataDTO
     * @return  JWT DataDTO
     * @throws ServiceException on failure
     * 
     */
    public DataDTO createJWT(DataDTO userDataDTO) throws ServiceException;
    /**
    * 
    * Create JWT from user DataDTO with additional claims.
    *
    * @param claims additional claims used
    * @param userDataDTO used user DataDTO
    * @return JWT DataDTO
    * @throws ServiceException on failure
    *
    */
    public DataDTO createJWT(Map<String, Object> claims, DataDTO userDataDTO) throws ServiceException;
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
