package tetofo.spring.tetofospringdashboard.Service.DAO;

import tetofo.spring.tetofospringdashboard.Service.DAO.Exception.DAOException;
import tetofo.spring.tetofospringdashboard.Service.DTO.Impl.DataDTO;

/**
 * 
 * UserDataDTODAO interface.
 * 
 * @author Pavel Bobek
 * @mail pavbobek@seznam.cz
 * 
 */
public interface IUserDataDTODAO extends IDAO<DataDTO, DataDTO> {
    /**
     * 
     * Get DataDTO of user by username.
     * 
     * @param dataDTO dataDTO holding username.
     * @return DataDTO for current user.
     * @throws DAOException on failure
     * 
     */
    public DataDTO getUserDataDTOByUsername(DataDTO dataDTO) throws DAOException;
}
