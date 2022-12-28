package ar.com.leogaray.hiring.model.nobel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PortionEnum {
    _1("1"), _1_2("1/2"), _1_3("1/3"), _1_4("1/4");

    private final String value;

    PortionEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static PortionEnum fromValue(String text) {
        for (PortionEnum b : PortionEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}