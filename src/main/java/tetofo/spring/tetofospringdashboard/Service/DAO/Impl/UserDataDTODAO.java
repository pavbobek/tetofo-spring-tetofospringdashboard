package tetofo.spring.tetofospringdashboard.Service.DAO.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import tetofo.spring.tetofospringdashboard.Model.Entity.Impl.DataEntity;
import tetofo.spring.tetofospringdashboard.Model.Entity.Impl.TagEntity;
import tetofo.spring.tetofospringdashboard.Model.Repository.DataEntityRepository;
import tetofo.spring.tetofospringdashboard.Service.DAO.DAOs;
import tetofo.spring.tetofospringdashboard.Service.DAO.IUserDataDTODAO;
import tetofo.spring.tetofospringdashboard.Service.DAO.Exception.DAOException;
import tetofo.spring.tetofospringdashboard.Service.DTO.Enum.TagDTO;
import tetofo.spring.tetofospringdashboard.Service.DTO.Impl.DataDTO;
import tetofo.spring.tetofospringdashboard.Service.Mapper.IEntityMapper;
import tetofo.spring.tetofospringdashboard.Service.Mapper.Exception.MapperException;
/**
 * 
 * DAO for user data.
 * 
 * @author Pavel Bobek
 * @mail pavbobek@seznam.cz
 * 
 */
@Service
public class UserDataDTODAO implements IUserDataDTODAO {
    @Autowired
    private IEntityMapper<DataEntity, DataDTO> dataEntityMapper;
    @Autowired
    private DataEntityRepository dataEntityRepository;

    @Override
    public void save(DataDTO r) throws DAOException {
        DAOException.requireNonNull(r, "Saving null object.");
        DAOException.requirePresence(r.getTags(), TagDTO.USER, "Input is not user.");
        try {
            dataEntityRepository.save(dataEntityMapper.toEntity(r));
        } catch (IllegalArgumentException | MapperException | OptimisticLockingFailureException e) {
            throw new DAOException("Unable to save entity %s.".formatted(r), e);
        }
    }
    @Override
    public List<DataDTO> getAll() throws DAOException {
        final List<DataDTO> dataDTOs = new ArrayList<>();
        final Iterable<DataEntity> dataEntities =  dataEntityRepository.findByTags(TagEntity.USER);
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
        DAOException.requireNonNull(s, "Getting null object.");
        DAOException.requirePresence(s.getTags(), TagDTO.USER, "Input is not user.");
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
        DAOException.requireNonNull(s, "Deleting null object.");
        DAOException.requirePresence(s.getTags(), TagDTO.USER, "Input is not user.");
        try {
            dataEntityRepository.deleteById(DAOs.getId(s, dataEntityMapper));
        } catch (IllegalArgumentException e) {
            throw new DAOException("Unable to delete entity", e);   
        }
    }
    @Override
    public DataDTO getUserDataDTOByUsername(DataDTO dataDTO) throws DAOException {
        DAOException.requireNonNull(dataDTO, "Getting with object.");
        DAOException.requireNonNull(dataDTO.getPayload(), "Getting user with null username.");
        DAOException.requirePresence(dataDTO.getTags(), TagDTO.USERNAME, "Input is not username.");
        final String usernamePart = "\"username\":\"%s\"".formatted(dataDTO.getPayload()); //important!!! avoid using ":" in username to prevent injection attacks
        try {
            return dataEntityMapper.fromEntity(dataEntityRepository.findByTagsIdAndPayloadContains(TagEntity.USER.getId(), usernamePart).orElseThrow(() -> new DAOException("User is missing.")));
        } catch (IllegalArgumentException | MapperException e) {
            throw new DAOException("Unable to retrieve entity", e);
        }
    }
}
