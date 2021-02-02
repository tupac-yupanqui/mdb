package de.wko.mdb.types.enums;

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
}
