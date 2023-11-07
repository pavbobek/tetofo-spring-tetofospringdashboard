package tetofo.spring.tetofospringdashboard.Model.Entity.Impl;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import tetofo.spring.tetofospringdashboard.Model.Entity.IEntity;

@Entity
public class TagEntity implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private Set<DataEntity> data;

    public static final TagEntity DIRECTORY_PATH = new TagEntity(0l);
    public static final TagEntity FILENAME = new TagEntity(1l);
    public static final TagEntity MESSAGE = new TagEntity(2l);
    public static final TagEntity PERSISTENCE_FILE = new TagEntity(3l);
    public static final TagEntity RECORD = new TagEntity(4l);
    public static final TagEntity STRING = new TagEntity(5l);
    public static final TagEntity USER = new TagEntity(6l);
    public static final TagEntity USERNAME = new TagEntity(7l);

    public TagEntity() {}

    public TagEntity(Long id) {
        this();
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<DataEntity> getData() {
        return data;
    }

    public void setData(Set<DataEntity> data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TagEntity other = (TagEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TagEntity [id=" + getId() + "]";
    }
}
