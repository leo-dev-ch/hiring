package ar.com.leogaray.hiring.model.nobel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SortOrderEnum {
    _1("1"), _2("2"), _3("3");
    private final String value;

    SortOrderEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static SortOrderEnum fromValue(String text) {
        for (SortOrderEnum b : SortOrderEnum.values()) {
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

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}