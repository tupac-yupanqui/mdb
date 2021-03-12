package de.wko.mdb.format;

import com.mpatric.mp3agic.*;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

public class Mp3Tagger {
    private static Mp3Tagger theInstance;

    public static Mp3Tagger getInstance() {
        if (theInstance == null) {
            theInstance = new Mp3Tagger();
        }
        return theInstance;
    }

    public AudioTags getMp3Tags(String filename) throws AudioFormatException {
        AudioTags tags = new AudioTags();
        try {
            Mp3File mp3File = new Mp3File(filename);
            if (mp3File.hasId3v1Tag()) {
                ID3v1 id3v1Tag = mp3File.getId3v1Tag();
                tags.setTitle(id3v1Tag.getTitle());
                tags.setArtist(id3v1Tag.getArtist());
                tags.setAlbum(id3v1Tag.getAlbum());
                tags.setComment(id3v1Tag.getComment());
                tags.setYear(id3v1Tag.getYear());
                tags.setNumber(getTrackNr(id3v1Tag.getTrack()));

            }
            if (mp3File.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3File.getId3v2Tag();
                tags.setTitle(id3v2Tag.getTitle());
                tags.setArtist(id3v2Tag.getArtist());
                tags.setAlbum(id3v2Tag.getAlbum());
                tags.setComment(id3v2Tag.getComment());
                tags.setYear(id3v2Tag.getYear());
                tags.setGenre(id3v2Tag.getGenreDescription());
                tags.setNumber(getTrackNr(id3v2Tag.getTrack()));
            }
            tags.setLength((int)mp3File.getLengthInSeconds());
        } catch (IOException e) {
            throw new AudioFormatException("MP3 File kann nicht geöffnet werden");
        } catch (UnsupportedTagException e) {
            throw new AudioFormatException("MP3 File: Unsupported Tag");
        } catch (InvalidDataException e) {
            throw new AudioFormatException("MP3 File: InvalidData Exception");
        }
        return tags;
    }

    public void setMp3Tags(String filename, AudioTags tags) throws AudioFormatException {
        try {
            Mp3File mp3File = new Mp3File(filename);
            if (mp3File.hasId3v1Tag()) {
                ID3v1 id3v1Tag = mp3File.getId3v1Tag();
                id3v1Tag.setAlbum(tags.getAlbum());
                id3v1Tag.setTitle(tags.getTitle());
                id3v1Tag.setArtist(tags.getArtist());
                id3v1Tag.setComment(tags.getComment());
                id3v1Tag.setYear(tags.getYear());
                id3v1Tag.setTrack(String.valueOf(tags.getNumber()));
            }
            if (mp3File.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3File.getId3v2Tag();
                id3v2Tag.setAlbum(tags.getAlbum());
                id3v2Tag.setTitle(tags.getTitle());
                id3v2Tag.setArtist(tags.getArtist());
                id3v2Tag.setComment(tags.getComment());
                id3v2Tag.setYear(tags.getYear());
                //id3v2Tag.setGenreDescription(tags.getGenre());
                id3v2Tag.setTrack(String.valueOf(tags.getNumber()));
            }
            mp3File.save("tmp.mp3");
            boolean result;
            File file = new File(filename);
            result = file.delete();
            file = new File("tmp.mp3");
            result = file.renameTo(new File(filename));
        } catch (IOException e) {
            throw new AudioFormatException("MP3 File kann nicht geöffnet werden");
        } catch (UnsupportedTagException e) {
            throw new AudioFormatException("MP3 File: Unsupported Tag");
        } catch (NotSupportedException e) {
            throw new AudioFormatException("MP3 File: Not supported");
        } catch (InvalidDataException e) {
            throw new AudioFormatException("MP3 File: InvalidData Exception");
        }

    }

    private int getTrackNr(String ns) {
        if (StringUtils.isBlank(ns)) return 0;
        int ix = ns.indexOf('/');
        try {
            if (ix == -1) {
                return Integer.parseInt(ns);
            }
            return Integer.parseInt(ns.substring(0, ix));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
