package tetofo.spring.tetofospringdashboard.Model.Entity.Impl;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import tetofo.spring.tetofospringdashboard.Model.Entity.IEntity;

@Entity
public class DataEntity implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
        CascadeType.MERGE,
        CascadeType.DETACH,
    })
    private Set<TagEntity> tags;
    private String payload;
    @OneToMany(mappedBy="parent", cascade = CascadeType.ALL )
    private Set<DataEntity> members;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="parent")
    private DataEntity parent;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPayload() {
        return payload;
    }
    public void setPayload(String payload) {
        this.payload = payload;
    }
    public Set<DataEntity> getMembers() {
        return members;
    }
    public void setMembers(Set<DataEntity> members) {
        this.members = members;
    }
    public DataEntity getParent() {
        return parent;
    }
    public void setParent(DataEntity parent) {
        this.parent = parent;
    }
    public Set<TagEntity> getTags() {
        return tags;
    }
    public void setTags(Set<TagEntity> tags) {
        this.tags = tags;
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
        DataEntity other = (DataEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "DataEntity [id=" + getId() + ", tags=" + tags + ", payload=" + payload + ", members=" + members + "]";
    }
}
