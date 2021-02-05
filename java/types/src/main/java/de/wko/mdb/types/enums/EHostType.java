package de.wko.mdb.types.enums;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum EHostType {
    HARDDRIVE("Rechner"),
    MOBILE("Wechseldatenträger"),
    FTP("FTP Server"),
    UNKNOWN("Unbekannt");

    private final String stringRepresentation;

    private EHostType(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public static EHostType fromString(String stringRepresentation) {
        for (EHostType b : EHostType.values()) {
            if (b.stringRepresentation.equalsIgnoreCase(stringRepresentation)) {
                return b;
            }
        }
        return UNKNOWN;
    }
    public String getDescr() {
        return stringRepresentation;
    }

    public static List<String> getValueList() {
        return EnumSet.allOf(EHostType.class).stream().filter(f -> f!=UNKNOWN).map(f -> f.getDescr()).collect(Collectors.toList());
    }

}
