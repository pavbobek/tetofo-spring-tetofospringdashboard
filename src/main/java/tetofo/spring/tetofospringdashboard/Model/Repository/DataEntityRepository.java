package tetofo.spring.tetofospringdashboard.Model.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tetofo.spring.tetofospringdashboard.Model.Entity.Impl.DataEntity;

@Repository
public interface DataEntityRepository extends CrudRepository<DataEntity, Long> {
    public List<DataEntity> findByTagsId(Long id); 
}
