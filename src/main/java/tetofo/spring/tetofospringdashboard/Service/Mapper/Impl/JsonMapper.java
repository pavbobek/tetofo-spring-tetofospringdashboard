package tetofo.spring.tetofospringdashboard.Service.Mapper.Impl;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import tetofo.spring.tetofospringdashboard.Service.Mapper.IJsonMapper;
import tetofo.spring.tetofospringdashboard.Service.Mapper.Exception.MapperException;

@Service
public class JsonMapper implements IJsonMapper{
    @Override
    public String toJson(Object o) throws MapperException {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new MapperException("Mapping to json failed.", e);
        }
    }
    @Override
    public <T> T fromJson(String json, Class<T> clazz) throws MapperException {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new MapperException("Mapping from json failed.", e);
        }
    }
    @Override
    public <T> T fromJson(String json, TypeReference<T> typeReference) throws MapperException {
        try {
            return new ObjectMapper().readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new MapperException("Mapping from json failed.", e);
        }
    }
}
