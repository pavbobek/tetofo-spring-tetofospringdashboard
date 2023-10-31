package tetofo.spring.tetofospringdashboard.Service.Mapper;

import tetofo.spring.tetofospringdashboard.Model.Entity.IEntity;
import tetofo.spring.tetofospringdashboard.Service.DTO.IDTO;

public interface IEntityMapper<R extends IEntity, S extends IDTO> {
    public S fromEntity(R r);
    public R toEntity(S s);
}
