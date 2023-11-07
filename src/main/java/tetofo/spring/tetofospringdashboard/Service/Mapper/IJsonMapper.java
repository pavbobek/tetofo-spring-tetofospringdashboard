package tetofo.spring.tetofospringdashboard.Service.Mapper;

import com.fasterxml.jackson.core.type.TypeReference;

import tetofo.spring.tetofospringdashboard.Service.Mapper.Exception.MapperException;

public interface IJsonMapper {
    public String toJson(Object o) throws MapperException;
    public <T> T fromJson(String json, Class<T> clazz) throws MapperException;
    public <T> T fromJson(String json, TypeReference<T> typeReference) throws MapperException;
}
