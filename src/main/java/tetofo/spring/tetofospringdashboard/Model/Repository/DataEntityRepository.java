package tetofo.spring.tetofospringdashboard.Model.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tetofo.spring.tetofospringdashboard.Model.Entity.Impl.DataEntity;
import tetofo.spring.tetofospringdashboard.Model.Entity.Impl.TagEntity;

@Repository
public interface DataEntityRepository extends CrudRepository<DataEntity, Long> {
    public List<DataEntity> findByTag(TagEntity tagEntity);
    public List<DataEntity> findByTagsId(Long tagId);
    public Optional<DataEntity> findByTagsIdAndPayloadContains(Long id, String string); 
}
