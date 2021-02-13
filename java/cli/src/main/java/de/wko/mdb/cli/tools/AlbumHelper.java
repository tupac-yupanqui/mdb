package de.wko.mdb.cli.tools;

import de.wko.mdb.types.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumHelper {
    public static List<String> getList(List<Album> albums) {
        List<String> result = new ArrayList<>();
        for (Album a : albums) {
            result.add(a.getName());
        }
        return result;
    }

    public static List<String> getList(List<Album> albums, String addValue) {
        List<String> result = getList(albums);
        result.add(addValue);
        return result;
    }

    public static Album getSelection(List<Album> albums, String selection) {
        for (Album a : albums) {
            if (selection.equalsIgnoreCase(a.getName())) {
                return a;
            }
        }
        return null;
    }

}
