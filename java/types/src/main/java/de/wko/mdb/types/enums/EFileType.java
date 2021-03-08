package de.wko.mdb.types.enums;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum EFileType {
    HIDDEN("Verborgen"),
    MP3("MP3-File"),
    WMA("WMA-File"),
    COVER("Cover"),
    THUMB("Cover klein"),
    PLAYLIST ("Playlist"),
    UNKNOWN("Unbekannt");

    private final String stringRepresentation;

    private EFileType(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public static EFileType fromString(String stringRepresentation) {
        for (EFileType b : EFileType.values()) {
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
        return EnumSet.allOf(EFileType.class).stream().filter(f -> f!=UNKNOWN).map(f -> f.getDescr()).collect(Collectors.toList());
    }
}
