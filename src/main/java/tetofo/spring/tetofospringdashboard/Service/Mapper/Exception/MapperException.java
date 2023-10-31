package tetofo.spring.tetofospringdashboard.Service.Mapper.Exception;

import java.util.Set;

import tetofo.spring.tetofospringdashboard.Service.Exception.ServiceException;

public class MapperException extends ServiceException {
    public MapperException() {
        super();
    }
    public MapperException(String msg) {
        super(msg);
    }
    public MapperException(String msg, Exception e) {
        super(msg, e);
    }
    public MapperException(String msg, Set<? extends Exception> exceptions) throws MapperException {
        super(msg);
        MapperException.requireNonNull(exceptions, "No exceptions provided");
        exceptions.forEach(this::addSuppressed);
    }
    public static <R> R requireNonNull(R r, String message) throws MapperException {
        if (r == null) {
            throw new MapperException(message);
        }
        return r;
    }
    public static <R extends Set<?>> R requireNonEmpty(R r, String message) throws MapperException {
        if (requireNonNull(r, message).isEmpty()) {
            throw new MapperException(message);
        }
        return r;
    }
    public static <R extends Set<S>,S> R requirePresence(R r, S s, String message) throws MapperException {
        if (!requireNonNull(r, message).contains(s)) {
            throw new MapperException(message);
        }
        return r;
    }
}
