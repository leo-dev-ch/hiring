package ar.com.leogaray.hiring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoriaNobel {
    CHE("che"), ECO("eco"), LIT("lit"), PEA("pea"), PHY("phy"), MED("med");
    private final String code;

    public static CategoriaNobel getCategoriaByCode(String key) {
        for (CategoriaNobel item : values()) {
            if (item.getCode().equals(key)) {
                return item;
            }
        }
        return null;
    }
}
