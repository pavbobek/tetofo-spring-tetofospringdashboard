package tetofo.spring.tetofospringdashboard.Model.DTO;

import java.util.List;

import tetofo.spring.tetofospringdashboard.Model.Enum.Tag;

public class DataDTO {
    private List<Tag> tags;
    private String payload;
    private List<DataDTO> members;
    public DataDTO() {

    }
    public DataDTO(List<Tag> tags, String payload, List<DataDTO> members) {
        this.tags = tags;
        this.payload = payload;
        this.members = members;
    }
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    public String getPayload() {
        return payload;
    }
    public void setPayload(String payload) {
        this.payload = payload;
    }
    public List<DataDTO> getMembers() {
        return members;
    }
    public void setMembers(List<DataDTO> members) {
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
