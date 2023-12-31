package tetofo.spring.tetofospringdashboard.Service.DAO.Impl;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tetofo.spring.tetofospringdashboard.Service.DAO.IDAO;
import tetofo.spring.tetofospringdashboard.Service.DAO.Exception.DAOException;
import tetofo.spring.tetofospringdashboard.Service.DTO.Enum.TagDTO;
import tetofo.spring.tetofospringdashboard.Service.DTO.Impl.DataDTO;
import tetofo.spring.tetofospringdashboard.Service.Mapper.IJsonMapper;
import tetofo.spring.tetofospringdashboard.Service.Mapper.Exception.MapperException;

@Service("FSDataDTODAO")
public class FSDataDTODAO implements IDAO<DataDTO, DataDTO> {

    private static final Logger LOG = LoggerFactory.getLogger(FSDataDTODAO.class); 

    @Autowired
    private IJsonMapper jsonMapper;
    private String exchangeFolder = "C:\\exchange"; 

    public static final List<DataDTO> items = Arrays.asList(
        new DataDTO(Collections.singleton(TagDTO.STRING), "Hello SPRING dashboard.",null),
        new DataDTO(Collections.singleton(TagDTO.STRING), "It is me",null),
        new DataDTO(Collections.singleton(TagDTO.STRING), "Another Test content.", null)
    );

    @Override
    public List<DataDTO> getAll() throws DAOException {
        LOG.info("Log message.");
        DirectoryStream<Path> directoryStream = null;
        try {
            directoryStream = Files.newDirectoryStream(Path.of(exchangeFolder));
        } catch (IOException ioe) {
            throw new DAOException("Reading files failed.", ioe);
        }
        final List<DataDTO> tokens = new ArrayList<>();
        directoryStream.forEach(path -> {
            try {
                tokens.add(asDataDTO(jsonMapper, path));
            } catch (DAOException e) {
                LOG.error(String.format("Unable to deserialize file: %s.", path));
            }
        });
        return tokens;
    }

    @Override
    public DataDTO get(DataDTO s) throws DAOException {
        return items.get(0);
    }

    @Override
    public void update(DataDTO s) throws DAOException{
        throw new DAOException("Unimplemented method 'update'");
    }

    @Override
    public void delete(DataDTO s) throws DAOException {
        throw new DAOException("Unimplemented method 'delete'");
    }

    private static DataDTO asDataDTO(IJsonMapper jsonMapper, Path path) throws DAOException {
        String content = null;
        try {
            content = Files.readString(path);
        } catch (IOException e) {
            throw new DAOException("Unable to deserialize file.",e);
        }
        try {
            return jsonMapper.fromJson(content, DataDTO.class);
        } catch (MapperException e) {
            throw new DAOException("Unable to read content as DataDTO.",e);
        }
    }

    @Override
    public DataDTO save(DataDTO r) throws DAOException {
        throw new DAOException("Unimplemented method 'create'");
    }
    
}
