package tetofo.spring.tetofospringdashboard.Service.Mapper;

import tetofo.spring.tetofospringdashboard.Model.Entity.IEntity;
import tetofo.spring.tetofospringdashboard.Service.DTO.IDTO;
import tetofo.spring.tetofospringdashboard.Service.Mapper.Exception.MapperException;

public interface IEntityMapper<R extends IEntity, S extends IDTO> {
    public S fromEntity(R r) throws MapperException;
    public R toEntity(S s) throws MapperException;
}
