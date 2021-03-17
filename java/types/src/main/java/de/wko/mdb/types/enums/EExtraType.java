package de.wko.mdb.types.enums;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum EExtraType {
    COVER("Cover"),
    THUMB("Cover small"),
    BOOKLET("Booklet"),
    OTHER("Other");

    private final String stringRepresentation;

    private EExtraType(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public static EExtraType fromString(String stringRepresentation) {
        for (EExtraType b : EExtraType.values()) {
            if (b.stringRepresentation.equalsIgnoreCase(stringRepresentation)) {
                return b;
            }
        }
        return OTHER;
    }
    public String getDescr() {
        return stringRepresentation;
    }

    public static List<String> getValueList() {
        return EnumSet.allOf(EExtraType.class).stream().map(f -> f.getDescr()).collect(Collectors.toList());
    }

}
