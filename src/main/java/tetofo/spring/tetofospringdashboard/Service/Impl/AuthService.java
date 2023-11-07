package tetofo.spring.tetofospringdashboard.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import tetofo.spring.tetofospringdashboard.Service.IAuthService;
import tetofo.spring.tetofospringdashboard.Service.IJWTService;
import tetofo.spring.tetofospringdashboard.Service.DAO.IUserDataDTODAO;
import tetofo.spring.tetofospringdashboard.Service.DTO.DTOs;
import tetofo.spring.tetofospringdashboard.Service.DTO.Enum.TagDTO;
import tetofo.spring.tetofospringdashboard.Service.DTO.Impl.DataDTO;
import tetofo.spring.tetofospringdashboard.Service.Exception.ServiceException;
import tetofo.spring.tetofospringdashboard.Service.Mapper.IJsonMapper;
/**
 * 
 * AuthService implementation.
 * 
 * @author Pavel Bobek
 * @mail pavbobek@seznam.cz
 * 
 */
@Service
public class AuthService implements IAuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IJsonMapper jsonMapper;
    @Autowired
    private IJWTService jwtService;
    @Autowired
    private IUserDataDTODAO userDataDTODAO;   

    @Override
    public DataDTO createUser(DataDTO dataDTO) throws ServiceException {
        ServiceException.requireNonNull(dataDTO, "DataDTO is null.");
        ServiceException.requirePresence(dataDTO.getTags(), TagDTO.USER, "DataDTO is not user.");
        userDataDTODAO.save(dataDTO);
        return jwtService.createJWT(dataDTO);
    }

    @Override
    public DataDTO loginUser(DataDTO dataDTO) throws ServiceException {
        ServiceException.requireNonNull(dataDTO, "DataDTO is null.");
        ServiceException.requirePresence(dataDTO.getTags(), TagDTO.USER, "DataDTO is not user.");
        final String username = DTOs.getUserDataDTOUsername(dataDTO, jsonMapper);
        final String passwotd = DTOs.getUserDataDTOPassword(dataDTO, jsonMapper);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, passwotd));
        } catch (AuthenticationException e) {
            throw new ServiceException("Unable to authenticate with user.", e);
        }
        final DataDTO usernameDataDTO = DTOs.createUsernameDataDTO(username);
        final DataDTO userDataDTO = userDataDTODAO.getUserDataDTOByUsername(usernameDataDTO);
        return jwtService.createJWT(userDataDTO);
    }
    
}
