package tetofo.spring.tetofospringdashboard.Service.DAO;

import tetofo.spring.tetofospringdashboard.Model.Entity.Impl.DataEntity;
import tetofo.spring.tetofospringdashboard.Service.DAO.Exception.DAOException;
import tetofo.spring.tetofospringdashboard.Service.DTO.Impl.DataDTO;
import tetofo.spring.tetofospringdashboard.Service.Mapper.IEntityMapper;
import tetofo.spring.tetofospringdashboard.Service.Mapper.Exception.MapperException;

/**
 * 
 * Helper class for DAO.
 * 
 * @author Pavel Bobek
 * @mail pavbobek@seznam.cz
 * 
 */
public class DAOs {
    /**
     * 
     * Return id of current dataDTO.
     * 
     * @param dataDTO used dataDTO
     * @param dataEntityMapper used mapper
     * @return id of provided dataDTO
     * @throws DAOException on failure
     */
    public static Long getId(DataDTO dataDTO, IEntityMapper<DataEntity, DataDTO> dataEntityMapper) throws DAOException {
        DAOException.requireNonNull(dataDTO, "DataDTO is null.");
        DataEntity dataEntity = null;
        try {
            dataEntity = dataEntityMapper.toEntity(dataDTO);
        } catch (MapperException e) {
            throw new DAOException("Unable to map dto: %s".formatted(dataDTO));
        }
        DAOException.requireNonNull(dataEntity, "Mapped entity is null.");
        DAOException.requireNonNull(dataEntity.getId(), "Mapped entity has no id.");
        return dataEntity.getId();
    }
}
