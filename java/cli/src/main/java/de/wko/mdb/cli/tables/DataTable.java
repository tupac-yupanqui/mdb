package de.wko.mdb.cli.tables;

public class DataTable {
    protected static final char ALIGN_LEFT = 'l';
    protected static final char ALIGN_RIGHT = 'r';
    protected static final char ALIGN_CENTER = 'c';

    protected Column[] columns;

    int width = 0;

    public void printHeader() {
        width = columns.length-1;
        for (Column c : columns) {
            width += c.width;
        }
        System.out.println("-".repeat(width));
        for (Column c : columns) {
            System.out.print(String.format("%-"+(c.width+1)+"s",c.title));
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
            printColumn(columns[i], p, (++i == columns.length) );
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
}
