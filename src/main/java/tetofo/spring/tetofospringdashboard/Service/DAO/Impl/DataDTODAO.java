package tetofo.spring.tetofospringdashboard.Service.DAO.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import tetofo.spring.tetofospringdashboard.Model.Entity.Impl.DataEntity;
import tetofo.spring.tetofospringdashboard.Model.Repository.DataEntityRepository;
import tetofo.spring.tetofospringdashboard.Service.DAO.DAOs;
import tetofo.spring.tetofospringdashboard.Service.DAO.IDAO;
import tetofo.spring.tetofospringdashboard.Service.DAO.Exception.DAOException;
import tetofo.spring.tetofospringdashboard.Service.DTO.Impl.DataDTO;
import tetofo.spring.tetofospringdashboard.Service.Mapper.IEntityMapper;
import tetofo.spring.tetofospringdashboard.Service.Mapper.Exception.MapperException;

@Service("DataDTODAO")
public class DataDTODAO implements IDAO<DataDTO, DataDTO> {

    @Autowired
    private IEntityMapper<DataEntity, DataDTO> dataEntityMapper;
    @Autowired
    private DataEntityRepository dataEntityRepository;

    @Override
    public void save(DataDTO r) throws DAOException {
        DAOException.requireNonNull(r, "DataDTO is null.");
        try {
            dataEntityRepository.save(dataEntityMapper.toEntity(r));
        } catch (IllegalArgumentException | MapperException | OptimisticLockingFailureException e) {
            throw new DAOException("Unable to save entity %s.".formatted(r), e);
        }
    }

    @Override
    public List<DataDTO> getAll() throws DAOException {
        final List<DataDTO> dataDTOs = new ArrayList<>();
        final Iterable<DataEntity> dataEntities =  dataEntityRepository.findAll();
        for (DataEntity dataEntity : dataEntities) {
            try {
                dataDTOs.add(dataEntityMapper.fromEntity(dataEntity));
            } catch (MapperException e) {
                throw new DAOException("Unable to map entity: %s".formatted(dataEntity), e);
            }
        }     
        return dataDTOs;
    }

    @Override
    public DataDTO get(DataDTO s) throws DAOException {        
        try {
            return dataEntityMapper.fromEntity(dataEntityRepository.findById(DAOs.getId(s, dataEntityMapper)).orElseThrow(() -> new DAOException("Entity is missing.")));
        } catch (IllegalArgumentException | MapperException e) {
            throw new DAOException("Unable to retrieve entity", e);
        }
    }

    @Override
    public void update(DataDTO s) throws DAOException {
        delete(s);
        save(s);
    }

    @Override
    public void delete(DataDTO s) throws DAOException {
        try {
            dataEntityRepository.deleteById(DAOs.getId(s, dataEntityMapper));
        } catch (IllegalArgumentException e) {
            throw new DAOException("Unable to delete entity", e);   
        }
    }
    
}
