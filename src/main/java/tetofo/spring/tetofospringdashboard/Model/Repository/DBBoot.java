package tetofo.spring.tetofospringdashboard.Model.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import tetofo.spring.tetofospringdashboard.Model.Entity.Impl.TagEntity;

@Service
public class DBBoot {

    @Autowired 
    private TagEntityRepository tagEntityRepository;

    @PostConstruct
    public void postConstruct() {
        tagEntityRepository.save(TagEntity.DIRECTORY_PATH);
        tagEntityRepository.save(TagEntity.FILENAME);
        tagEntityRepository.save(TagEntity.JWT);
        tagEntityRepository.save(TagEntity.MESSAGE);
        tagEntityRepository.save(TagEntity.PERSISTENCE_FILE);
        tagEntityRepository.save(TagEntity.RECORD);
        tagEntityRepository.save(TagEntity.STRING);
        tagEntityRepository.save(TagEntity.USER);
        tagEntityRepository.save(TagEntity.USERNAME);
    }
}
