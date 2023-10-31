package tetofo.spring.tetofospringdashboard.Service.DAO.Exception;

import java.util.Set;

import tetofo.spring.tetofospringdashboard.Service.Exception.ServiceException;

public class DAOException extends ServiceException {
    public DAOException() {super();}
    public DAOException(String msg) {super(msg);}
    public DAOException(String msg, Exception e) {super(msg, e);}
    public DAOException(String msg, Set<? extends Exception> exceptions) throws DAOException {
        super(msg);
        DAOException.requireNonNull(exceptions, "No exceptions provided");
        exceptions.forEach(this::addSuppressed);
    }
    public static <R> R requireNonNull(R r, String message) throws DAOException {
        if (r == null) {
            throw new DAOException(message);
        }
        return r;
    }
    public static <R extends Set<?>> R requireNonEmpty(R r, String message) throws DAOException {
        if (requireNonNull(r, message).isEmpty()) {
            throw new DAOException(message);
        }
        return r;
    }
    public static <R extends Set<S>,S> R requirePresence(R r, S s, String message) throws DAOException {
        if (!requireNonNull(r, message).contains(s)) {
            throw new DAOException(message);
        }
        return r;
    }
}
