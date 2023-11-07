package tetofo.spring.tetofospringdashboard.Service.DTO;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import tetofo.spring.tetofospringdashboard.Service.DTO.Enum.TagDTO;
import tetofo.spring.tetofospringdashboard.Service.DTO.Exception.DTOException;
import tetofo.spring.tetofospringdashboard.Service.DTO.Impl.DataDTO;
import tetofo.spring.tetofospringdashboard.Service.Mapper.IJsonMapper;
import tetofo.spring.tetofospringdashboard.Service.Mapper.Exception.MapperException;

/**
 * 
 * Helper methods for DTO.
 * 
 * @author Pavel Bobek
 * @mail pavbobek@seznam.cz
 * 
 */
public class DTOs {
    /**
     * 
     * Creates username DataDTO.
     * 
     * @param username used username
     * @return  DataDTO object
     * @throws DTOException on failure
     */
    public static DataDTO createUsernameDataDTO(String username) throws DTOException {
        DTOException.requireNonNull(username, "Username is null.");
        return new DataDTO(Collections.singleton(TagDTO.USERNAME), username, null);
    }
    public static DataDTO createUserDataDTO(String username, String password, IJsonMapper jsonMapper) throws DTOException {
        DTOException.requireNonNull(username, "Username is null.");
        DTOException.requireNonNull(password, "Password is null.");
        DTOException.requireNonNull(jsonMapper, "JsonMapper is null.");
        final Map<String, Object> payload = new HashMap<>();
        payload.put("username", username);
        payload.put("password", password);
        try {
            return new DataDTO(Collections.singleton(TagDTO.USER), jsonMapper.toJson(payload),null);
        } catch (MapperException e) {
            throw new DTOException("Unable to create user DataDTO", e);
        }
    }
}
