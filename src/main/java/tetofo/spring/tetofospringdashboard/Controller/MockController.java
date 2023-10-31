package tetofo.spring.tetofospringdashboard.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tetofo.spring.tetofospringdashboard.Service.DAO.IDAO;
import tetofo.spring.tetofospringdashboard.Service.DTO.Impl.DataDTO;
import tetofo.spring.tetofospringdashboard.Service.Exception.ServiceException;

@RestController
public class MockController {

    @Autowired
    @Qualifier("FSDataDTODAO")
    private IDAO<DataDTO, DataDTO> fSDataDTODAO;
    @Autowired
    @Qualifier("JPADataDTODAO")
    private IDAO<DataDTO, DataDTO> jPADataDTODAO;

    @GetMapping("/mock/JPA/getAll")
        public List<DataDTO> getAllJPA() throws ServiceException {
        return jPADataDTODAO.getAll();
    }

    @GetMapping("/mock/FS/getAll")
        public List<DataDTO> getAllFS() throws ServiceException {
        return fSDataDTODAO.getAll();
    }

}
