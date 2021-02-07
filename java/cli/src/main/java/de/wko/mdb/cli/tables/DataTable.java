package de.wko.mdb.cli.tables;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DataTable {
    protected static final char ALIGN_LEFT = 'l';
    protected static final char ALIGN_RIGHT = 'r';
    protected static final char ALIGN_CENTER = 'c';

    protected List<Column> columns = new ArrayList();

    int width = 0;

    public void printHeader() {
        width = columns.size()-1;
        for (Column c : columns) {
            width += c.width;
        }
        System.out.println("-".repeat(width));
        for (Column c : columns) {
            if (c.align==ALIGN_RIGHT) {
                System.out.print(StringUtils.leftPad(c.title, c.width, ' ')+" ");
            } else {
                System.out.print(StringUtils.rightPad(c.title, c.width+1, ' '));
            }
        }
        System.out.println();
        System.out.println("-".repeat(width));
    }
    public void printFooter() {
        System.out.println("-".repeat(width));
    }

    public void printRow(String... params) {
        int i=0;
        for (String p : params) {
            printColumn(columns.get(i), p, (++i == columns.size()) );
        }
    }

    public void printColumn(Column c, String s, boolean isLast) {
        switch (c.align) {
            case 'c':
                System.out.print(centerString(c.width, s, isLast));
                break;
            case 'l':
                System.out.print(leftString(c.width, s, isLast));
                break;
            case 'r':
                System.out.print(rightString(c.width, s, isLast));
                break;
        }
    }

    public static String leftString(int width, String s, boolean isLast) {
        return String.format("%-"+width+"s", s)+(isLast?'\n':' ');
    }
    public static String rightString(int width, String s, boolean isLast) {
        return String.format("%"+width+"s", s)+(isLast?'\n':' ');
    }
    public static String centerString (int width, String s, boolean isLast) {
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s))+(isLast?'\n':' ');
    }

    protected static String truncate(String s, int l) {
        if (s.length()>l-1) {
            return s.substring(0, l-4)+"...";
        }
        return s;
    }
}
