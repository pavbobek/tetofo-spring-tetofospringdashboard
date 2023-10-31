package tetofo.spring.tetofospringdashboard.Model.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tetofo.spring.tetofospringdashboard.Model.Entity.Impl.DataEntity;

@Repository
public interface DataRepository extends CrudRepository<DataEntity, Long> {}
