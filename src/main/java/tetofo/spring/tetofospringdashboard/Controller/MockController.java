package tetofo.spring.tetofospringdashboard.Controller;

//import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tetofo.spring.tetofospringdashboard.Model.Entity.Impl.TagEntity;
import tetofo.spring.tetofospringdashboard.Model.Repository.TagEntityRepository;
import tetofo.spring.tetofospringdashboard.Service.DAO.IDAO;
//import tetofo.spring.tetofospringdashboard.Service.DAO.Exception.DAOException;
//import tetofo.spring.tetofospringdashboard.Service.DTO.Enum.TagDTO;
import tetofo.spring.tetofospringdashboard.Service.DTO.Impl.DataDTO;
import tetofo.spring.tetofospringdashboard.Service.Exception.ServiceException;

@RestController
public class MockController {

    @Autowired
    @Qualifier("FSDataDTODAO")
    private IDAO<DataDTO, DataDTO> fSDataDTODAO;
    @Autowired
    @Qualifier("DataDTODAO")
    private IDAO<DataDTO, DataDTO> jPADataDTODAO;
    @Autowired
    private TagEntityRepository tagEntityRepository;

    //private boolean done;

    @GetMapping("/mock/JPA/getAll")
    public List<DataDTO> getAllJPA() throws ServiceException {
        /* for persisting information to contemt management
        if(!done) {
            tagEntityRepository.save(TagEntity.FILENAME);
            tagEntityRepository.save(TagEntity.DIRECTORY_PATH);
            done = true;
        }
        final DataDTO dataDTO = new DataDTO();
        dataDTO.setPayload("test");
        dataDTO.setTags(Collections.singleton(TagDTO.FILENAME));
        final DataDTO data2DTO = new DataDTO();
        data2DTO.setPayload("test2");
        data2DTO.setTags(Collections.singleton(TagDTO.DIRECTORY_PATH));
        final DataDTO data3DTO = new DataDTO();
        data3DTO.setPayload("test3");
        data3DTO.setTags(Collections.singleton(TagDTO.DIRECTORY_PATH));
        data2DTO.setMembers(Collections.singleton(data3DTO));
        dataDTO.setMembers(Collections.singleton(data2DTO));
        jPADataDTODAO.save(dataDTO);
        List<DataDTO> data = jPADataDTODAO.getAll();
        DataDTO toUpdate = data.get(0);
        toUpdate.setPayload("updated");
        List<DataDTO> toRemove = new ArrayList<>();
        toUpdate.getMembers().forEach(item -> {
            
            if (!item.getTags().contains(TagDTO.RECORD)) {
                toRemove.add(item);
            }
        });
        toUpdate.getMembers().removeAll(toRemove);
        jPADataDTODAO.update(toUpdate);
        jPADataDTODAO.delete(toUpdate);
         */
        return jPADataDTODAO.getAll();
    }

    @GetMapping("/mock/JPA/tags/getAll")
    public Iterable<TagEntity> getAllTagsJPA() throws ServiceException {
        return tagEntityRepository.findAll();
    }

    @GetMapping("/mock/FS/getAll")
    public List<DataDTO> getAllFS() throws ServiceException {
        return fSDataDTODAO.getAll();
    }

}
