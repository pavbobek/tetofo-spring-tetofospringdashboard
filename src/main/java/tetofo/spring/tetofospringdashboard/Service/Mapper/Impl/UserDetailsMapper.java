package tetofo.spring.tetofospringdashboard.Service.Mapper.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;

import io.jsonwebtoken.lang.Collections;
import tetofo.spring.tetofospringdashboard.Service.DTO.DTOs;
import tetofo.spring.tetofospringdashboard.Service.DTO.Exception.DTOException;
import tetofo.spring.tetofospringdashboard.Service.DTO.Impl.DataDTO;
import tetofo.spring.tetofospringdashboard.Service.Mapper.IJsonMapper;
import tetofo.spring.tetofospringdashboard.Service.Mapper.IUserDetailsMapper;
import tetofo.spring.tetofospringdashboard.Service.Mapper.Exception.MapperException;

/**
 * UserDetailsMapper implementation.
 * 
 * @author Pavel Bobek
 * @mail pavbobek@seznam.cz
 * 
 */
@Service
public class UserDetailsMapper implements IUserDetailsMapper{

    @Autowired
    private IJsonMapper jsonMapper;

    private static final TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};

    @Override
    public DataDTO fromUserDetails(UserDetails userDetails) throws MapperException {
        if (userDetails == null) {
            return null;
        }
        try {
            return DTOs.createUserDataDTO(userDetails.getUsername(), userDetails.getPassword(), jsonMapper);
        } catch (DTOException e) {
            throw new MapperException("Unable to create DataDTO from userDetails.", e);
        }
    }
    @Override
    public UserDetails toUserDetails(DataDTO dataDTO) throws MapperException {
        if (dataDTO == null) {
            return null;
        }
        final Map<String, Object> payload = jsonMapper.fromJson(dataDTO.getPayload(), typeRef);
        final String username = MapperException.requireNonNull(payload.get("username"), "Username is null.").toString();
        final String password = MapperException.requireNonNull(payload.get("password"), "Password is null.").toString();
        return new User(username, password, Collections.emptySet());
    }
    
}
