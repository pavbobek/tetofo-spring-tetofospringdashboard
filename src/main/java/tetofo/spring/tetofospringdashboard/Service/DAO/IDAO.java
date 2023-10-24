package tetofo.spring.tetofospringdashboard.Service.DAO;

import java.util.List;

import tetofo.spring.tetofospringdashboard.Service.DAO.Exception.DAOException;

public interface IDAO<R, S> {
    public List<R> getAll() throws DAOException;
    public R get(S s) throws DAOException;
    public void update(R r, S s) throws DAOException;
    public void delete(R r) throws DAOException;
}
