package tetofo.spring.tetofospringdashboard.Model.Enum;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Tag {
    DIRECTORY_PATH,
    FILENAME,
    MESSAGE,
    STRING;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
