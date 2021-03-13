package de.wko.mdb.cli.cmd;

import com.google.common.io.Files;
import de.wko.mdb.cli.CliContext;
import de.wko.mdb.cli.Commands;
import de.wko.mdb.cli.tables.FolderTable;
import de.wko.mdb.cli.tools.*;
import de.wko.mdb.format.AudioFormatException;
import de.wko.mdb.format.AudioTags;
import de.wko.mdb.format.Mp3Tagger;
import de.wko.mdb.fs.ArchiveFileSystem;
import de.wko.mdb.fs.FileSystemException;
import de.wko.mdb.fs.LocalFileSystem;
import de.wko.mdb.fs.sort.FileComparator;
import de.wko.mdb.rcl.*;
import de.wko.mdb.types.*;
import de.wko.mdb.types.enums.EFileType;
import de.wko.mdb.types.enums.EFolderType;
import de.wko.mdb.types.query.SearchArtistBlurQuery;
import de.wko.mdb.types.query.SearchTitelBlurQuery;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ShellComponent
@ShellCommandGroup(value="Folderkommandos")
public class FolderCommands {

    @Autowired
    ContentClient contentClient;

    @Autowired
    FolderClient folderClient;

    @Autowired
    FileClient fileClient;

    @Autowired
    ArtistClient artistClient;

    @Autowired
    AlbumClient albumClient;

    @Autowired
    TitelClient titelClient;

    @Autowired
    SearchClient searchClient;

    @Autowired
    CliContext context;

    private ConsoleReader reader = new ConsoleReader();
    private Folder parentFolder = null;
    private ArchiveFileSystem archiveFileSystem = null;
    private String archivePath;
    private String fullPath;
    private Long archiveId;
    private Long albumId;
    private Long subalbumId;
    private boolean autoCreate;

    @ShellMethod(value = "Liste der Inhalte", key = Commands.CMD_LIST_CONTENT)
    public void listFolders(@ShellOption(value = "-n", help = "nur neue Inhalte") boolean newOnly,
                            @ShellOption(value = "-x", help = "nur existierende Inhalte") boolean existOnly) {
        if (!context.getCurrentFileSystem().isFilesystemAvailable()
                || context.getCurrentFileSystem() instanceof LocalFileSystem) {
            System.out.println("kein Archiv geöffnet");
            return;
        }

        try {
            prepare();

            FolderContent content = contentClient.getContentByPath(archiveId, archiveFileSystem.getCurrentDir());
            if (content == null) {
                System.out.println("Parent folder doesn't exist and must be created before.");
                return;
            }

            FolderContent newContent = getNewContent(archiveFileSystem, content);
            if (!newOnly && existOnly) {
                new FolderTable(content).print();
            } else if (newOnly && !existOnly) {
                new FolderTable(newContent).print();
            } else {
                new FolderTable(ListHelper.mergeContent(content, newContent)).print();
            }
        } catch (MdbRestException e) {
            System.out.println("MdbRestException " + e.getResponse().getMessage());
            e.printStackTrace();
        }

    }

    @ShellMethod(value = "Inhalt bearbeiten", key = Commands.CMD_EDIT_CONTENT)
    public void editContent(
            @ShellOption(defaultValue = "") String objectToEdit,
            @ShellOption(value = "-n", help = "nur neue Inhalte") boolean newOnly,
            @ShellOption(defaultValue = "-1") int id,
            @ShellOption(value = "-a") boolean auto) {
        if (!context.getCurrentFileSystem().isFilesystemAvailable()
                || context.getCurrentFileSystem() instanceof LocalFileSystem) {
            System.out.println("kein Archiv geöffnet");
            return;
        }

        autoCreate = auto;

        try {
            prepare();

            if (parentFolder == null) {
                System.out.println("Der übergeordnete Folder existiert nicht und muss vorher angelegt werden.");
                return;
            }
            List<MdbFile> files = archiveFileSystem.listDir("");
            Collections.sort(files, FileComparator.getFileComparator(FileComparator.SORT_TYPE));
            for (MdbFile file : files) {
                if (objectToEdit.length() > 0 && !file.getName().equalsIgnoreCase(objectToEdit)) {
                    continue;
                }
                if (file.isDirectory()) {
                    Folder folder = folderClient.getFolderByPath(archiveId, archivePath + file.getName());
                    if (folder != null) {
                        if (newOnly) continue;
                        if (!reader.readBoolean(String.format("Edit Folder '%s'?", file.getName()), false)) {
                            continue;
                        }
                    } else {
                        if (!reader.readBoolean(String.format("Kein Folder für '%s' vorhanden. Anlegen?", file.getName()), true)) {
                            continue;
                        }
                        folder = new Folder();
                        folder.setName(file.getName());
                        folder.setParentId(parentFolder.getId());
                        folder.setObjectId(0L);
                        folder.setArchiveId(archiveId);
                        folder.setType(EFolderType.UNKNOWN);
                    }
                    editFolder(folder);
                } else {
                    FileObject fileObject = fileClient.getFileByFolderIdAndName(parentFolder.getId(), file.getName());
                    if (fileObject != null) {
                        if (newOnly) continue;
                        if (!reader.readBoolean(String.format("Edit File '%s'?", file.getName()), false)) {
                            continue;
                        }
                    } else {
                        if (!autoCreate) {
                            if (!reader.readBoolean(String.format("No file object exists for '%s'. Create it?", file.getName()), true)) {
                                continue;
                            }
                        }
                        fileObject = new FileObject();
                        fileObject.setFilename(file.getName());
                        fileObject.setFolderId(parentFolder.getId());
                        fileObject.setObjectId(0L);
                        fileObject.setType(EFileType.UNKNOWN);
                    }
                    editFile(fileObject, parentFolder);
                }
            }
        } catch (ReaderExitException e) {
        } catch (MdbRestException e) {
            System.out.println("MdbRestException " + e.getResponse().getMessage());
            e.printStackTrace();
        } catch (FileSystemException e) {
            System.out.println(e.getMessage());
        }

    }

    private void editFolder(Folder folder) {
        try {
            Folder parent = folderClient.getFolderById(folder.getParentId());
            if (parent == null) {
                System.out.println("Der übergeordnete Folder existiert nicht und muss vorher angelegt werden.");
            }

            String type = reader.readFromList("Foldertyp", EFolderType.getValueList(parent.getType()), folder.getType().getDescr());
            folder.setType(EFolderType.fromString(type));
            switch (folder.getType()) {
                case ARTIST:
                    editArtistFolder(folder);
                    break;
                case ALBUM:
                    editAlbumFolder(folder);
                    break;
            }
        } catch (ReaderExitException e) {
            // nothing to do
        } catch (MdbRestException e) {
            e.printStackTrace();
        }
    }

    private void editFile(FileObject file, Folder parentFolder) throws ReaderExitException {
        if (file.getType().equals(EFileType.UNKNOWN)) {
            file.setType(getFileTypeFromFileName(file.getFilename()));
        }
        if (!autoCreate) {
            String type = reader.readFromList("Filetyp", EFileType.getValueList(), file.getType().getDescr());
            file.setType(EFileType.fromString(type));
        } else {
            if (!file.getType().equals(EFileType.MP3)) {
                System.out.println("skip file                "+file.getFilename());
                return;
            }
        }

        switch (file.getType()) {
            case MP3:
                editMp3File(file, parentFolder);
                break;
        }

    }

    private void editMp3File(FileObject file, Folder parentFolder) throws ReaderExitException {
        String fullPathName = fullPath + file.getFilename();
        try {
            AudioTags tags = Mp3Tagger.getInstance().getMp3Tags(fullPathName);

            Titel titel;
            if (file.getId()==0L) {
                // File nicht in DB
                titel = selectTitelFromList(getScoredTitelList(tags));

                if (titel.getId()==0L) {
                    initTitel(titel, tags);
                    if (autoCreate) {
                        titel.setArtist(identifyArtist(tags.getArtist()));
                    } else {
                        System.out.println("create title for "+file.getFilename());
                        titel.setName(reader.readString("Title", titel.getName(), true));
                        titel.setArtist(identifyArtist(tags.getArtist()));
                        titel.setTracknr(reader.readInteger("TrackNo", titel.getTracknr(), true));
                        titel.setComment(reader.readString("Comment", titel.getComment(), false));
                        titel.setLength(reader.readInteger("Length", titel.getLength(), true));
                        titel.setYear(reader.readString("Year", titel.getYear(), false));
                        //titel.setGenre(reader.readString("Genre", tags.getGenre(), false));
                    }
                    titel = titelClient.saveTitel(titel);
                    if (autoCreate) {
                        System.out.println(String.format("create title             %s - %s (ID=%d)", titel.getArtist().getName(), titel.getName(), titel.getId()));
                    }
                }
                file.setObjectId(titel.getId());
            } else {
                // File in DB
                titel = titelClient.getTitelById(file.getObjectId());
            }
            if (!autoCreate) {
                boolean editTags = reader.readBoolean("write MP3 tags?", false);
                if (editTags) {
                    saveTags(titel, tags, fullPathName);
                }
                System.out.println("file: "+file.getFilename());
                boolean keepFilename = reader.readBoolean("keep file name?", true);
                if (!keepFilename) {
                    renameFile(file, titel);
                }
            } else {
                renameFile(file, titel);
            }
            file = fileClient.saveFile(file);
            if (autoCreate) {
                System.out.println(String.format("save file object         '%s' (ID=%d)", file.getFilename(), file.getId()));
            }
        } catch (MdbRestException e) {
            e.printStackTrace();
        } catch (AudioFormatException e) {
            e.printStackTrace();
        }
    }

    private EFileType getFileTypeFromFileName(String fileName) {
        String extension = Files.getFileExtension(fileName);
        if (Strings.isEmpty(extension)) return EFileType.UNKNOWN;
        if (extension.equalsIgnoreCase("mp3")) {
            return EFileType.MP3;
        }
        return EFileType.UNKNOWN;
    }

    private FolderContent getNewContent(ArchiveFileSystem fs, FolderContent existingContent) {
        List<Folder> newFolders = new ArrayList();
        List<FileObject> newTracks = new ArrayList<>();

        try {
            List<MdbFile> files = fs.listDir(fs.getCurrentDir());
            for (MdbFile file : files) {
                if (file.isDirectory()) {
                    if (!ListHelper.isFileInFolderList(file, existingContent.getFolderList())) {
                        Folder f = new Folder();
                        f.setName(file.getName());
                        f.setType(EFolderType.UNKNOWN);
                        newFolders.add(f);
                    }
                } else {
                    //System.out.println(file.getName());
                    if (!ListHelper.isFileInTrackList(file, existingContent.getTrackList())) {
                        FileObject t = new FileObject();
                        t.setFilename(file.getName());
                        newTracks.add(t);
                    }
                }
            }
        } catch (FileSystemException e) {
            e.printStackTrace();
        }

        FolderContent newContent = new FolderContent();
        newContent.setTrackList(newTracks);
        newContent.setFolderList(newFolders);
        return newContent;
    }

    private Artist searchArtist(Artist artist) throws ReaderExitException, MdbRestException {
        do {
            String auswahl = reader.readString("Suche nach");
            List<Artist> artists = artistClient.getArtistByPattern(auswahl);
            auswahl = reader.readFromList("Suchergebnis", ArtistHelper.getList(artists, "Neue Suche"), "");
            artist = ArtistHelper.getSelection(artists, auswahl);
        } while (artist == null);
        return artist;
    }

    private Artist selectArtist(Artist artist) throws ReaderExitException, MdbRestException {
        String auswahl = reader.readString("Name Artist");
        artist = artistClient.getArtistByName(auswahl);
        if (artist == null) {
            artist = new Artist();
            artist.setName(auswahl);
            artist = artistClient.saveArtist(artist);
        }
        return artist;
    }

    private Album searchAlbum(Album album) throws ReaderExitException, MdbRestException {
        do {
            String auswahl = reader.readString("Suche nach");
            List<Album> albums = albumClient.getAlbumByPattern(auswahl);
            auswahl = reader.readFromList("Suchergebnis", AlbumHelper.getList(albums, "Neue Suche"), "");
            album = AlbumHelper.getSelection(albums, auswahl);
        } while (album == null);
        return album;
    }

    private Album selectAlbum(Album album) throws ReaderExitException, MdbRestException {
        String auswahl = reader.readString("Name Album");
        album = albumClient.getAlbumByName(auswahl);
        if (album == null) {
            album = new Album();
            album.setName(auswahl);
            album = albumClient.saveAlbum(album);
        }
        return album;
    }

    private void editAlbumFolder(Folder folder)   throws ReaderExitException, MdbRestException {
        Album album = folder.getObjectId() == 0L ? albumClient.getAlbumByName(folder.getName()) : albumClient.getAlbumById(folder.getObjectId());
        if (album == null) {
            List<String> auswahlAktion = Arrays.asList("Anlegen", "Suchen", "Eingabe");
            String auswahl = reader.readFromList(String.format("Artist '%s' (neu)", folder.getName()), auswahlAktion, "Suchen");
            switch (auswahlAktion.indexOf(auswahl)) {
                case 0:
                    album = new Album();
                    album.setName(folder.getName());
                    album = albumClient.saveAlbum(album);
                    break;
                case 1:
                    album = searchAlbum(album);
                    break;
                case 2:
                    album = selectAlbum(album);
                    break;
            }
        } else {
            List<String> auswahlAktion = Arrays.asList("Auswählen", "Suchen", "Eingabe");
            String auswahl = reader.readFromList(String.format("Album '%s' (ID %d)", album.getName(), album.getId()), auswahlAktion, "Auswählen");
            switch (auswahlAktion.indexOf(auswahl)) {
                case 0:
                    break;
                case 1:
                    album = searchAlbum(album);
                    break;
                case 2:
                    album = selectAlbum(album);
                    break;
            }
        }
        folder.setObjectId(album.getId());
        folderClient.saveFolder(folder);
    }

    private void editArtistFolder(Folder folder) throws ReaderExitException, MdbRestException {
        Artist artist = folder.getObjectId() == 0L ? artistClient.getArtistByName(folder.getName()) : artistClient.getArtistById(folder.getObjectId());
        if (artist == null) {
            List<String> auswahlAktion = Arrays.asList("Anlegen", "Suchen", "Eingabe");
            String auswahl = reader.readFromList(String.format("Album '%s' (neu)", folder.getName()), auswahlAktion, "Anlegen");
            switch (auswahlAktion.indexOf(auswahl)) {
                case 0:
                    artist.setName(folder.getName());
                    artist = artistClient.saveArtist(artist);
                    break;
                case 1:
                    artist = searchArtist(artist);
                    break;
                case 2:
                    artist = selectArtist(artist);
                    break;
            }
        } else {
            List<String> auswahlAktion = Arrays.asList("Auswählen", "Suchen", "Eingabe");
            String auswahl = reader.readFromList(String.format("Artist '%s' (ID %d)", artist.getName(), artist.getId()), auswahlAktion, "Auswählen");
            switch (auswahlAktion.indexOf(auswahl)) {
                case 0:
                    break;
                case 1:
                    artist = searchArtist(artist);
                    break;
                case 2:
                    artist = selectArtist(artist);
                    break;
            }
        }
        folder.setObjectId(artist.getId());
        folderClient.saveFolder(folder);

    }

    private void prepare() throws MdbRestException {
        archiveFileSystem = (ArchiveFileSystem) context.getCurrentFileSystem();

        parentFolder = folderClient.getFolderByPath(archiveFileSystem.getArchive().getId(), archiveFileSystem.getCurrentDir());

        archiveId = archiveFileSystem.getArchive().getId();

        archivePath = archiveFileSystem.getCurrentDir();
        if (!archivePath.equals("/")) archivePath += "/";

        fullPath = archiveFileSystem.getArchive().getPath() + archivePath;

        albumId = null;
        subalbumId = null;

        if (parentFolder!=null) {
            if (parentFolder.getType().equals(EFolderType.ALBUM)) {
                Album album = albumClient.getAlbumById(parentFolder.getObjectId());
                albumId = album.getId();
                subalbumId = album.getSubalbums().get(0).getId();
            } else if (parentFolder.getType().equals(EFolderType.SUBALBUM)) {
                Subalbum subalbum = albumClient.getSubalbumById(parentFolder.getObjectId());
                albumId = subalbum.getParentId();
                subalbumId = subalbum.getId();
            }
        }
    }

    private List<ScoredTitel> getScoredTitelList(AudioTags tags) throws MdbRestException {
        SearchTitelBlurQuery query = new SearchTitelBlurQuery();
        query.setTitel(tags.getTitle());
        query.setArtist(tags.getArtist());

        query.setScoreCount(5);
        if (parentFolder.getType().equals(EFolderType.ALBUM) || parentFolder.getType().equals(EFolderType.SUBALBUM)) {
            query.setScoreCount(1);
        }
        query.setAlbumId(albumId==null?0L:albumId);
        query.setScoreMax(10);

        return searchClient.searchTitelBlur(query);
    }

    private List<ScoredArtist> getScoredArtistList(String artistName) throws MdbRestException {
        SearchArtistBlurQuery artistQuery = new SearchArtistBlurQuery();

        artistQuery.setArtist(artistName);
        artistQuery.setScoreMax(5);
        artistQuery.setScoreCount(10);
        return searchClient.searchArtistBlur(artistQuery);
    }

    private Titel selectTitelFromList(List<ScoredTitel> scoredTitelList) throws MdbRestException, ReaderExitException {
        int titelSelection = 0;
        List<String> titelSelectList = new ArrayList<>();
        if (scoredTitelList.size()>0) {
            titelSelectList.add("create");
            for (ScoredTitel st : scoredTitelList) {
                if (titelSelection == 0 && st.getScore() == 0) {
                    titelSelection = titelSelectList.size();
                }
                titelSelectList.add(st.getTitel().getArtist().getName() + " - " + st.getTitel().getName());
            }

            if (!autoCreate) titelSelection = reader.readFromList("Select title", titelSelectList, titelSelection);
        }
        if (titelSelection == 0) return new Titel();

        return scoredTitelList.get(titelSelection-1).getTitel();
    }

    private void initTitel(Titel titel, AudioTags tags) {
        titel.setName(tags.getTitle());
        titel.setComment(tags.getComment());
        titel.setYear(tags.getYear());
        titel.setLength(tags.getLength());
        titel.setTracknr(tags.getNumber());
        titel.setAlbumId(albumId);
        titel.setSubalbumId(subalbumId);
        titel.setGenre(tags.getGenre());
    }

    private Artist identifyArtist(String artistName) throws MdbRestException, ReaderExitException{
        List<ScoredArtist> scoredArtistList = getScoredArtistList(artistName);
        int countExact = 0;
        Artist artist = null;
        for (ScoredArtist sa : scoredArtistList) {
            if (sa.getScore()==0) countExact++;
        }
        if (countExact==1) {
            artist = scoredArtistList.get(0).getArtist();
            if (!autoCreate) System.out.println("Artist found: "+artist.getName());
        } else if (countExact>1) {
            System.out.println("Artist not unique: "+artistName);
            List<String> artistSelectList = new ArrayList<>();
            artistSelectList.add("Create");
            int artistSelection = 0;
            for (ScoredArtist sa : scoredArtistList) {
                if (artistSelection==0 && sa.getScore()==0) {
                    artistSelection = artistSelectList.size();
                }
                artistSelectList.add(String.format("%s (%d)", sa.getArtist().getName(), sa.getArtist().getId()));
            }
            artistSelection = reader.readFromList("Artists", artistSelectList, artistSelection);
            if (artistSelection>0) {
                artist = scoredArtistList.get(artistSelection-1).getArtist();
            }
        } else {
            System.out.println("Artist not found: "+artistName);
            List<String> artistSelectList = new ArrayList<>();
            artistSelectList.add("Create");
            int artistSelection = 0;
            for (ScoredArtist sa : scoredArtistList) {
                if (artistSelection==0 && sa.getScore()==0) {
                    artistSelection = artistSelectList.size();
                }
                artistSelectList.add(String.format("%s (%d)", sa.getArtist().getName(), sa.getArtist().getId()));
            }
            artistSelection = reader.readFromList("Artists", artistSelectList, artistSelection);
            if (artistSelection>0) {
                artist = scoredArtistList.get(artistSelection-1).getArtist();
            }
        }
        if (artist==null) {
            artist = new Artist();
            artist.setName(artistName);
            if (!autoCreate) {
                artist.setName(reader.readString("Artist", artistName));
            } else {
                System.out.println("create artist "+artistName);
            }
            artist = artistClient.saveArtist(artist);
        }
        return artist;
    }

    private void saveTags(Titel titel, AudioTags tags, String fullPathName) throws AudioFormatException {
        tags.setNumber(titel.getTracknr());
        tags.setTitle(titel.getName());
        tags.setArtist(titel.getArtist().getName());
        tags.setComment(titel.getComment());
        tags.setYear(titel.getYear());
        //tags.setGenre(titel.getGenre());
        Mp3Tagger.getInstance().setMp3Tags(fullPathName, tags);
    }

    private void renameFile(FileObject file, Titel titel) throws ReaderExitException{
        String fullPathName = fullPath + file.getFilename();
        String newFilename = "";
        int renameOption;
        if (autoCreate) {
            if (albumId>0L) {
                renameOption = 1;
            } else {
                renameOption = 0;
            }
        } else {
            List<String> nameTypeList = new ArrayList<>();
            nameTypeList.add("Namen behalten");
            nameTypeList.add("## - <artist> - <titel>");
            nameTypeList.add("<artist> - <titel>");
            nameTypeList.add("## - <titel>");
            renameOption = reader.readFromList("Auswahl", nameTypeList, 0);
        }
        switch (renameOption) {
            case 0:
                return;
            case 1:
                newFilename = String.format("%02d - %s - %s.mp3", titel.getTracknr(), titel.getArtist().getName(), titel.getName());
                break;
            case 2:
                newFilename = String.format("%s - %s.mp3", titel.getArtist().getName(), titel.getName());
                break;
            case 3:
                newFilename = String.format("%02d - %s.mp3", titel.getTracknr(), titel.getName());
                break;
        }
        int ix = fullPathName.lastIndexOf("/");
        String newPathName = fullPathName.substring(0, ix+1) + normalizeFilename(newFilename);
        File f = new File(fullPathName);
        try {
            if (f.renameTo(new File(newPathName))) {
                file.setFilename(newFilename);
            } else {
                System.out.println("cant rename file to "+newPathName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String normalizeFilename(String fn) {
        return fn.replace(":","").replace("*","+");
    }
}