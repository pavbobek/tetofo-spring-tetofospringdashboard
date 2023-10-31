package tetofo.spring.tetofospringdashboard.Service.DAO;

import java.util.List;

import tetofo.spring.tetofospringdashboard.Service.DAO.Exception.DAOException;

public interface IDAO<R, S> {
    public void save(R r) throws DAOException;
    public List<S> getAll() throws DAOException;
    public R get(S s) throws DAOException;
    public void update(S s) throws DAOException;
    public void delete(S s) throws DAOException;
}
