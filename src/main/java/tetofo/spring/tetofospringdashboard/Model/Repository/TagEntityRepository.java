package tetofo.spring.tetofospringdashboard.Model.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import tetofo.spring.tetofospringdashboard.Model.Entity.Impl.TagEntity;

@Repository
public interface TagEntityRepository extends CrudRepository<TagEntity, Long> {

    @PostConstruct
    public default void postConstruct() {
        save(TagEntity.DIRECTORY_PATH);
        save(TagEntity.FILENAME);
        save(TagEntity.MESSAGE);
        save(TagEntity.PERSISTENCE_FILE);
        save(TagEntity.RECORD);
        save(TagEntity.STRING);
        save(TagEntity.USER);
        save(TagEntity.USERNAME);
    }

}
