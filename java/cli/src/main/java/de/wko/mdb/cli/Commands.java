package de.wko.mdb.cli;

public class Commands {

    private Commands() {}
    public static final String C1_OPEN  = "open";
    public static final String C1_LIST  = "list";
    public static final String C1_EDIT  = "edit";
    public static final String C2_ARCHIVE  = "archive";
    public static final String C2_CONTENT  = "content";
    public static final String CMD_LIST_CONTENT  = C1_LIST + " " + C2_CONTENT;
    public static final String CMD_LIST_CONTENT_SHORT  = "l c";
    public static final String CMD_EDIT_CONTENT  = C1_EDIT + " " + C2_CONTENT;
    public static final String CMD_EDIT_CONTENT_SHORT  = "e c";
    public static final String CMD_OPEN_ARCHIVE  = C1_OPEN + " " + C2_ARCHIVE;
    public static final String CMD_CHANGE_DIR = "cd";
}
