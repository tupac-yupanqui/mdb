package de.wko.mdb.fs.sort;

import de.wko.mdb.types.MdbFile;

import java.util.Comparator;

public class FileComparator {

    public static final int SORT_LEXICAL = 0;
    public static final int SORT_TYPE = 1;

    public static Comparator<MdbFile> getFileComparator(int sortType) {
        switch (sortType) {
            case SORT_LEXICAL:
                return new Comparator<MdbFile>() {
                    @Override
                    public int compare(MdbFile o1, MdbFile o2) {
                        return o1.getName().compareToIgnoreCase(o2.getName());
                    }
                };
            case SORT_TYPE:
                return new Comparator<MdbFile>() {
                    @Override
                    public int compare(MdbFile o1, MdbFile o2) {
                        if (o1.isDirectory() && !o2.isDirectory()) return -1;
                        if (!o1.isDirectory() && o2.isDirectory()) return 1;
                        return o1.getName().compareToIgnoreCase(o2.getName());
                    }
                };
            default:
                return null;
        }
    }
}
