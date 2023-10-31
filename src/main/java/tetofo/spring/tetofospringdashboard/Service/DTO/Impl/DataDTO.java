package tetofo.spring.tetofospringdashboard.Service.DTO.Impl;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import tetofo.spring.tetofospringdashboard.Service.DTO.IDTO;
import tetofo.spring.tetofospringdashboard.Service.DTO.Enum.TagDTO;

@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class DataDTO implements IDTO {
    private Set<TagDTO> tags;
    private String payload;
    private Set<DataDTO> members;
    public DataDTO() {

    }
    public DataDTO(Set<TagDTO> tags, String payload, Set<DataDTO> members) {
        this.tags = tags;
        this.payload = payload;
        this.members = members;
    }
    public Set<TagDTO> getTags() {
        return tags;
    }
    public void setTags(Set<TagDTO> tags) {
        this.tags = tags;
    }
    public String getPayload() {
        return payload;
    }
    public void setPayload(String payload) {
        this.payload = payload;
    }
    public Set<DataDTO> getMembers() {
        return members;
    }
    public void setMembers(Set<DataDTO> members) {
        this.members = members;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
        result = prime * result + ((payload == null) ? 0 : payload.hashCode());
        result = prime * result + ((members == null) ? 0 : members.hashCode());
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
        DataDTO other = (DataDTO) obj;
        if (tags == null) {
            if (other.tags != null)
                return false;
        } else if (!tags.equals(other.tags))
            return false;
        if (payload == null) {
            if (other.payload != null)
                return false;
        } else if (!payload.equals(other.payload))
            return false;
        if (members == null) {
            if (other.members != null)
                return false;
        } else if (!members.equals(other.members))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Data [tags=" + tags + ", payload=" + payload + ", members=" + members + "]";
    }
}
