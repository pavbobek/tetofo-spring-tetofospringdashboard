package tetofo.spring.tetofospringdashboard.Model.Entity.Impl;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import tetofo.spring.tetofospringdashboard.Model.Entity.IEntity;
import tetofo.spring.tetofospringdashboard.Model.Entity.Enum.TagEntity;

@Entity
public class DataEntity implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;
    private Set<TagEntity> tags;
    private String payload;
    @OneToMany(mappedBy="parent")
    private Set<DataEntity> members;
    @ManyToOne(fetch = FetchType.LAZY)
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
}
