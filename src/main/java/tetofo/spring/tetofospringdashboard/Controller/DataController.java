package tetofo.spring.tetofospringdashboard.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tetofo.spring.tetofospringdashboard.Model.DTO.DataDTO;
import tetofo.spring.tetofospringdashboard.Service.DAO.IDAO;
import tetofo.spring.tetofospringdashboard.Service.Exception.ServiceException;

@RestController
public class DataController {
    
    @Autowired
    private IDAO<DataDTO, DataDTO> fSDataDTODAO;

    @GetMapping("/tokens")
    public List<DataDTO> getTokens() throws ServiceException {
        return fSDataDTODAO.getAll();
    }

}
