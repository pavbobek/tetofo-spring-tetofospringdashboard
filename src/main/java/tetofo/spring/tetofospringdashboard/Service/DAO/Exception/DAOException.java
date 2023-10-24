package tetofo.spring.tetofospringdashboard.Service.DAO.Exception;

import tetofo.spring.tetofospringdashboard.Service.Exception.ServiceException;

public class DAOException extends ServiceException {
    public DAOException() {super();}
    public DAOException(String msg) {super(msg);}
    public DAOException(String msg, Exception e) {super(msg, e);}
}
