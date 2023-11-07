package tetofo.spring.tetofospringdashboard.Service.Exception;

import java.util.Set;

public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }
    public ServiceException(String msg) {
        super(msg);
    }
    public ServiceException(String msg, Exception e) {
        super(msg, e);
    }    

    public static <R> R requireNonNull(R r, String message) throws ServiceException {
        if (r == null) {
            throw new ServiceException(message);
        }
        return r;
    }
        public static <R extends Set<S>,S> R requirePresence(R r, S s, String message) throws ServiceException {
        if (!requireNonNull(r, message).contains(s)) {
            throw new ServiceException(message);
        }
        return r;
    }
}
