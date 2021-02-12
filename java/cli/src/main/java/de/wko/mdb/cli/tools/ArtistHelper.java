package de.wko.mdb.cli.tools;

import de.wko.mdb.types.Artist;

import java.util.ArrayList;
import java.util.List;

public class ArtistHelper {

    public static List<String> getList(List<Artist> artists) {
        List<String> result = new ArrayList<>();
        for (Artist a : artists) {
            result.add(a.getName());
        }
        return result;
    }

    public static List<String> getList(List<Artist> artists, String addValue) {
        List<String> result = getList(artists);
        result.add(addValue);
        return result;
    }

    public static Artist getSelection(List<Artist> artists, String selection) {
        for (Artist a : artists) {
            if (selection.equalsIgnoreCase(a.getName())) {
                return a;
            }
        }
        return null;
    }
}
