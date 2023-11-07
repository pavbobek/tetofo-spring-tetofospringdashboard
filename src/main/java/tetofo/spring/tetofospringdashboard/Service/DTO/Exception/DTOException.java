package tetofo.spring.tetofospringdashboard.Service.DTO.Exception;

import java.util.Set;

import tetofo.spring.tetofospringdashboard.Service.Exception.ServiceException;
/**
 * 
 * DTOException
 * 
 * @author Pavel Bobek
 * @mail pavbobek@seznam.cz
 * 
 */
public class DTOException extends ServiceException {
    public DTOException() {super();}
    public DTOException(String msg) {super(msg);}
    public DTOException(String msg, Exception e) {super(msg, e);}
    public DTOException(String msg, Set<? extends Exception> exceptions) throws DTOException {
        super(msg);
        DTOException.requireNonNull(exceptions, "No exceptions provided");
        exceptions.forEach(this::addSuppressed);
    }
    public static <R> R requireNonNull(R r, String message) throws DTOException {
        if (r == null) {
            throw new DTOException(message);
        }
        return r;
    }
    public static <R extends Set<?>> R requireNonEmpty(R r, String message) throws DTOException {
        if (requireNonNull(r, message).isEmpty()) {
            throw new DTOException(message);
        }
        return r;
    }
    public static <R extends Set<S>,S> R requirePresence(R r, S s, String message) throws DTOException {
        if (!requireNonNull(r, message).contains(s)) {
            throw new DTOException(message);
        }
        return r;
    }
}
