package de.wko.mdb.types.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {

    public static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public static String mask(String s) {
        return s.replace("'", "''");
    }

    public static Date normalizeDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int year = cal.get(Calendar.YEAR);
        if (year<100) {
            if (year<35) {
                year += 2000;
            } else {
                year += 1900;
            }
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
        }
        return cal.getTime();
    }
}
