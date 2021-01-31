package de.wko.mdb.cli.tables;

public class Column {
    int width;
    String title;
    char align;

    Column(int w, String t, char a) {
        width = w; title = t; align = a;
    }
}
