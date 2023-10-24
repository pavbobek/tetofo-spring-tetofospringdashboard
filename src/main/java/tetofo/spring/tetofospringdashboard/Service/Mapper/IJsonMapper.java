package tetofo.spring.tetofospringdashboard.Service.Mapper;

import tetofo.spring.tetofospringdashboard.Service.Mapper.Exception.MapperException;

public interface IJsonMapper {
    public String toJson(Object o) throws MapperException;
    public <T> T fromJson(String json, Class<T> clazz) throws MapperException;
}
