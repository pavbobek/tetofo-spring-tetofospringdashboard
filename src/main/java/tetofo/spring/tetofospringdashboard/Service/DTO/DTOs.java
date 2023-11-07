package tetofo.spring.tetofospringdashboard.Service.DTO;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

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
    private static final TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};
    /**
     * 
     * Creates JWT DataDTO.
     * 
     * @param jwt used jwt
     * @return DataDTO object
     * @throws DTOException on failure
     */
    public static DataDTO createJWTDataDTO(String jwt) throws DTOException {
        DTOException.requireNonNull(jwt, "JWT is null.");
        return new DataDTO(Collections.singleton(TagDTO.JWT), jwt, null);
    }
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
    /**
     * 
     * Creates user DataDTO.
     * 
     * @param username used username
     * @param password used password
     * @param jsonMapper used jsonMapper
     * @return user DataDTO
     * @throws DTOException on failure
     */
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
    /**
     * 
     * Get user DataDTO username.
     * 
     * @param dataDTO used dataDTO
     * @param jsonMapper used jsonMapper
     * @return username
     * @throws DTOException on failure
     */
    public static String getUserDataDTOUsername(DataDTO dataDTO, IJsonMapper jsonMapper) throws DTOException {
        DTOException.requireNonNull(dataDTO, "DataDTO is null.");
        DTOException.requirePresence(dataDTO.getTags(), TagDTO.USER, "DataDTO is not user.");
        DTOException.requireNonNull(jsonMapper, "JsonMapper is null.");
        return getUserDataDTOItem(dataDTO, jsonMapper, "username");
    }
    /**
     * 
     * Get user DataDTO password.
     * 
     * @param dataDTO used dataDTO
     * @param jsonMapper used jsonMapper
     * @return password
     * @throws DTOException on failure
     */
    public static String getUserDataDTOPassword(DataDTO dataDTO, IJsonMapper jsonMapper) throws DTOException {
        DTOException.requireNonNull(dataDTO, "DataDTO is null.");
        DTOException.requirePresence(dataDTO.getTags(), TagDTO.USER, "DataDTO is not user.");
        DTOException.requireNonNull(jsonMapper, "JsonMapper is null.");
        return getUserDataDTOItem(dataDTO, jsonMapper, "password");
    }
    /**
     * 
     * Get user DataDTO item.
     * 
     * @param dataDTO used dataDTO
     * @param jsonMapper used jsonMapper
     * @param item required item
     * @return item from payload
     * @throws DTOException on failure
     */
    private static String getUserDataDTOItem(DataDTO dataDTO, IJsonMapper jsonMapper, String item) throws DTOException {
        final Map<String, Object> payload;
        try{
            payload = jsonMapper.fromJson(dataDTO.getPayload(), typeRef);
        } catch (MapperException e) {
            throw new DTOException("Unable to map payload", e);
        }
        return DTOException.requireNonNull(payload.get(item), "Item %s is missing in payload.".formatted(item)).toString();
    }
}
