package de.wko.mdb.types.enums;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum EArchiveType {
    COMPLETE("Archiv Komplett"),
    METAL("Archiv Metal"),
    UNKNOWN("Unbekannt");

    private final String stringRepresentation;

    private EArchiveType(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public static EArchiveType fromString(String stringRepresentation) {
        for (EArchiveType b : EArchiveType.values()) {
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
        return EnumSet.allOf(EArchiveType.class).stream().filter(f -> f!=UNKNOWN).map(f -> f.getDescr()).collect(Collectors.toList());
    }

}
