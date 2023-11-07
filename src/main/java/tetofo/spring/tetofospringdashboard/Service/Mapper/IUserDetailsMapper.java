package tetofo.spring.tetofospringdashboard.Service.Mapper;

import org.springframework.security.core.userdetails.UserDetails;

import tetofo.spring.tetofospringdashboard.Service.DTO.Impl.DataDTO;
import tetofo.spring.tetofospringdashboard.Service.Mapper.Exception.MapperException;

/**
 * 
 * UserDetailsMapper interface.
 * 
 * @author Pavel Bobek
 * @mail pavbobek@seznam.cz
 * 
 */
public interface IUserDetailsMapper {
    /**
     * 
     * Maps UserDetails to DataDTO.
     * 
     * @param userDetails used userDetails
     * @return DataDTO object
     * @throws MapperException on failure
     */
    public DataDTO fromUserDetails(UserDetails userDetails) throws MapperException;
    /**
     * 
     * Maps DataDTO to userDetails.
     * 
     * @param dataDTO used dataDTO 
     * @return UserDetails object
     * @throws MapperException on failure
     */
    public UserDetails toUserDetails(DataDTO dataDTO) throws MapperException;
}
