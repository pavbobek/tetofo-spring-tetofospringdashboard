package tetofo.spring.tetofospringdashboard.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tetofo.spring.tetofospringdashboard.Service.DAO.IUserDataDTODAO;
import tetofo.spring.tetofospringdashboard.Service.DAO.Exception.DAOException;
import tetofo.spring.tetofospringdashboard.Service.DTO.DTOs;
import tetofo.spring.tetofospringdashboard.Service.DTO.Exception.DTOException;
import tetofo.spring.tetofospringdashboard.Service.DTO.Impl.DataDTO;
import tetofo.spring.tetofospringdashboard.Service.Exception.UsernameNotFoundExtensionException;
import tetofo.spring.tetofospringdashboard.Service.Mapper.IUserDetailsMapper;
import tetofo.spring.tetofospringdashboard.Service.Mapper.Exception.MapperException;
/**
 * 
 * UserDetailsService implementation with userDataDTO.
 * 
 * @author Pavel Bobek
 * @mail pavbobek@seznam.cz
 * 
 */
@Service
public class UserDataDTOUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserDataDTODAO userDataDTODAO;
    @Autowired
    private IUserDetailsMapper userDetailsMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsernameNotFoundExtensionException.requireNonNull(username, "Username is null.");
        final DataDTO usernameDataDTO;
        try {
            usernameDataDTO = DTOs.createUsernameDataDTO(username);
        } catch (DTOException e) {
            throw new UsernameNotFoundException("Username is null.", e);
        }
        final DataDTO dataDTO;
        try {
            dataDTO = userDataDTODAO.getUserDataDTOByUsername(usernameDataDTO);
        } catch (DAOException e) {
            throw new UsernameNotFoundException("User is missing.", e);
        }
        try {
            return userDetailsMapper.toUserDetails(dataDTO);
        } catch (MapperException e) {
            throw new UsernameNotFoundException("Unable to map dataDTO to userDetails", e);
        }
    }
    
}
