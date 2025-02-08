package br.com.gravitech.condonews.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContactTypeEnum {

    SYNDIC("Síndico(a)"),
    JANITOR("Zelador(a)"),
    MAINTENANCE("Manutenção"),
    OTHER("Outro");

    private final String description;

    public static ContactTypeEnum getByDescription(String description) {
        for (ContactTypeEnum contactType : ContactTypeEnum.values()) {
            if (contactType.getDescription().equals(description)) {
                return contactType;
            }
        }
        return null;
    }
}
