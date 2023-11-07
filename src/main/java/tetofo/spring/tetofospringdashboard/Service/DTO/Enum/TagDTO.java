package tetofo.spring.tetofospringdashboard.Service.DTO.Enum;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TagDTO {
    DIRECTORY_PATH,
    FILENAME,
    MESSAGE,
    PERSISTENCE_FILE,
    RECORD,
    STRING,
    USER,
    USERNAME;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
