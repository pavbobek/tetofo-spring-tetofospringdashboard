package tetofo.spring.tetofospringdashboard.Service.Mapper.Exception;

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
}
