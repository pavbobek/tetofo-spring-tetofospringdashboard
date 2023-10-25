package tetofo.spring.tetofospringdashboard.Model.Enum;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Tag {
    DIRECTORY_PATH,
    FILENAME,
    MESSAGE,
    PERSISTENCE_FILE,
    STRING;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
