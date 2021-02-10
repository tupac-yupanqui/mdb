package de.wko.mdb.types.enums;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum EFolderType {
    ROOT("Root"),
    ARTIST("Artist"),
    ALBUM("Album"),
    SUBALBUM("Subalbum"),
    LIST("Liste"),
    UNKNOWN("Unbekannt");

    private final String stringRepresentation;

    private EFolderType(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public static EFolderType fromString(String stringRepresentation) {
        for (EFolderType b : EFolderType.values()) {
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
        return EnumSet.allOf(EFolderType.class).stream().filter(f -> f!=UNKNOWN).map(f -> f.getDescr()).collect(Collectors.toList());
    }

}
