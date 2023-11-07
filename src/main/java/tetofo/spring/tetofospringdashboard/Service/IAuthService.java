package tetofo.spring.tetofospringdashboard.Service;

import tetofo.spring.tetofospringdashboard.Service.DTO.Impl.DataDTO;
import tetofo.spring.tetofospringdashboard.Service.Exception.ServiceException;

/**
 * 
 * AuthService interface.
 * 
 * @author Pavel Bobek
 * @mail pavbobek@seznam.cz
 * 
 */
public interface IAuthService {
    /**
     * 
     * Create user and return its JWT.
     * 
     * @param dataDTO user dataDTO
     * @return JWT dataDTO
     * @throws ServiceException on failure
     * 
     */
    public DataDTO createUser(DataDTO dataDTO) throws ServiceException;
    /**
     * 
     * Login user and create its JWT.
     * 
     * @param dataDTO user dataDTO
     * @return JWT dataDTO
     * @throws ServiceException on failure
     */
    public DataDTO loginUser(DataDTO dataDTO) throws ServiceException;  
}
