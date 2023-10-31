package tetofo.spring.tetofospringdashboard.Service.DTO.Enum;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TagDTO {
    DIRECTORY_PATH,
    FILENAME,
    MESSAGE,
    PERSISTENCE_FILE,
    STRING,
    RECORD;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
