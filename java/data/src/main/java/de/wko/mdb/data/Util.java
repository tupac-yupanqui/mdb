package de.wko.mdb.data;

import java.text.SimpleDateFormat;

public class Util {

    public static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public static String mask(String s) {
        return s.replace("'", "''");
    }
}
