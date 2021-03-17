package de.wko.mdb.cli.cmd;

import com.google.common.io.Files;
import de.wko.mdb.cli.CliContext;
import de.wko.mdb.cli.Commands;
import de.wko.mdb.cli.Settings;
import de.wko.mdb.cli.tables.FolderTable;
import de.wko.mdb.cli.tools.*;
import de.wko.mdb.format.AudioFormatException;
import de.wko.mdb.format.AudioTags;
import de.wko.mdb.format.Converter;
import de.wko.mdb.format.Mp3Tagger;
import de.wko.mdb.fs.ArchiveFileSystem;
import de.wko.mdb.fs.FileHelper;
import de.wko.mdb.fs.FileSystemException;
import de.wko.mdb.fs.LocalFileSystem;
import de.wko.mdb.fs.sort.FileComparator;
import de.wko.mdb.rcl.*;
import de.wko.mdb.types.*;
import de.wko.mdb.types.enums.EExtraType;
import de.wko.mdb.types.enums.EFileType;
import de.wko.mdb.types.enums.EFolderType;
import de.wko.mdb.types.query.SearchAlbumBlurQuery;
import de.wko.mdb.types.query.SearchArtistBlurQuery;
import de.wko.mdb.types.query.SearchTitelBlurQuery;
import de.wko.mdb.types.query.SearchTitelQuery;
import de.wko.mdb.types.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.text.ParseException;
import java.util.*;

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
    ExtraClient extraClient;

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

    @ShellMethod(value = "Remove content object", key = Commands.CMD_REMOVE_CONTENT)
    public void removeContent(@ShellOption(defaultValue = "") String objectToRemove) {
        if (StringUtils.isEmpty(objectToRemove)) {
            System.out.println("Missing parameter");
            return;
        }
        if (!context.getCurrentFileSystem().isFilesystemAvailable()
                || context.getCurrentFileSystem() instanceof LocalFileSystem) {
            System.out.println("kein Archiv geöffnet");
            return;
        }
        try {
            prepare();

            File file = new File(fullPath + objectToRemove);
            System.out.println(file.getAbsolutePath());
            System.out.println(file.isFile());
            System.out.println(file.isDirectory());
            System.out.println(file.exists());

            Folder folder = folderClient.getFolderByPath(archiveId, archivePath + file.getName());
            System.out.println(folder);



        } catch (MdbRestException e) {
            e.printStackTrace();
        }
    }

    @ShellMethod(value = "Liste der Inhalte", key = {
            Commands.CMD_LIST_CONTENT,
            Commands.CMD_LIST_CONTENT_SHORT})
    public void listFolders(@ShellOption(value = "-n", help = "nur neue Inhalte") boolean newOnly,
                            @ShellOption(value = "-r", help = "list recursive unknown contents") boolean recursive,
                            @ShellOption(value = "-x", help = "nur existierende Inhalte") boolean existOnly) {

        String resetToDir = null;

        if (!context.getCurrentFileSystem().isFilesystemAvailable()
                || context.getCurrentFileSystem() instanceof LocalFileSystem) {
            System.out.println("kein Archiv geöffnet");
            return;
        }

        try {
            prepare();

            if (recursive) {
                resetToDir = archiveFileSystem.getCurrentDir();
                listRecursive(archiveFileSystem.getCurrentDir(), false);
                return;
            }

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
        } catch (ReaderExitException e) {
            if (resetToDir!=null) {
                try {
                    archiveFileSystem.changeCurrentDir(resetToDir);
                } catch (FileSystemException fse) {
                    fse.printStackTrace();
                }
            }
        } catch (MdbRestException e) {
            System.out.println("MdbRestException " + e.getResponse().getMessage());
            e.printStackTrace();
        }

    }

    @ShellMethod(value = "Inhalt bearbeiten", key = {
                        Commands.CMD_EDIT_CONTENT,
                        Commands.CMD_EDIT_CONTENT_SHORT})
    public void editContent(
            @ShellOption(defaultValue = "") String objectToEdit,
            @ShellOption(value = "-n", help = "nur neue Inhalte") boolean newOnly,
            @ShellOption(value = "-r", help = "list recursive unknown contents") boolean recursive,
            @ShellOption(defaultValue = "-1") int id,
            @ShellOption(value = "-a") boolean auto) {

        String resetToDir = null;

        if (!context.getCurrentFileSystem().isFilesystemAvailable()
                || context.getCurrentFileSystem() instanceof LocalFileSystem) {
            System.out.println("kein Archiv geöffnet");
            return;
        }

        autoCreate = auto;

        try {
            prepare();

            if (recursive) {
                resetToDir = archiveFileSystem.getCurrentDir();
                listRecursive(archiveFileSystem.getCurrentDir(), true);
                return;
            }

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
                    FileObject fileObject = getFileObject(file.getName(), newOnly);
                    if (fileObject==null) continue;
                    editFile(fileObject, parentFolder);
                }
            }
        } catch (ReaderExitException e) {
            if (resetToDir!=null) {
                try {
                    archiveFileSystem.changeCurrentDir(resetToDir);
                } catch (FileSystemException fse) {
                    fse.printStackTrace();
                }
            }
        } catch (MdbRestException e) {
            System.out.println("MdbRestException " + e.getResponse().getMessage());
            e.printStackTrace();
        } catch (FileSystemException e) {
            System.out.println(e.getMessage());
        }

    }

    private FileObject getFileObject(String filename, boolean newOnly) throws MdbRestException, ReaderExitException {
        FileObject fileObject = fileClient.getFileByFolderIdAndName(parentFolder.getId(), filename);
        if (fileObject != null) {
            if (newOnly) return null;
            if (!reader.readBoolean(String.format("Edit File '%s'?", filename), false)) {
                return null;
            }
        } else {
            if (!autoCreate) {
                if (!reader.readBoolean(String.format("No file object exists for '%s'. Create it?", filename), true)) {
                    return null;
                }
            }
            fileObject = new FileObject();
            fileObject.setFilename(filename);
            fileObject.setFolderId(parentFolder.getId());
            fileObject.setObjectId(0L);
            fileObject.setType(EFileType.UNKNOWN);
        }
        return fileObject;
    }

    private void editFolder(Folder folder) {
        try {
            Folder parent = folderClient.getFolderById(folder.getParentId());
            if (parent == null) {
                System.out.println("Der übergeordnete Folder existiert nicht und muss vorher angelegt werden.");
            }

            List<String> typeList = EFolderType.getValueList(parent.getType());
            String type = reader.readFromList("Foldertyp", typeList, folder.getType().getDescr());
            folder.setType(EFolderType.fromString(type));
            switch (folder.getType()) {
                case ARTIST:
                    editArtistFolder(folder);
                    break;
                case ALBUM:
                    editAlbumFolder(folder);
                    break;
                case SUBALBUM:
                    editSubalbumFolder(folder);
                    break;
                case COLLECTION:
                    editCollectionFolder(folder);
                    break;
                case LIST:
                    editListFolder(folder);
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
            if (!file.getType().equals(EFileType.MP3) && !file.getType().equals(EFileType.WMA)) {
                System.out.println("skip file                "+file.getFilename());
                return;
            }
        }

        switch (file.getType()) {
            case MP3:
                editMp3File(file, parentFolder);
                break;
            case WMA:
                editWmaFile(file, parentFolder);
                break;
            case EXTRA:
                editExtraFile(file, parentFolder);
                break;
        }

    }

    private void editExtraFile(FileObject file, Folder parentFolder) throws ReaderExitException {
        try {
            Extra extra = new Extra();

            EExtraType type = EExtraType.OTHER;
            if (file.getFilename().toUpperCase().startsWith("COVER")) {
                type = EExtraType.COVER;
            }
            if (file.getFilename().toUpperCase().startsWith("SMALL")) {
                type = EExtraType.THUMB;
            }
            if (file.getFilename().toUpperCase().startsWith("BOOKLET")) {
                type = EExtraType.BOOKLET;
            }
            String selectedType = reader.readFromList("Type", EExtraType.getValueList(), type.getDescr());
            extra.setType(EExtraType.fromString(selectedType));
            extra.setDescription(reader.readString("Description", selectedType));
            extra.setAlbumId(parentFolder.getObjectId());

            extra = extraClient.saveExtra(extra);
            file.setObjectId(extra.getId());
            fileClient.saveFile(file);
        } catch (MdbRestException e) {
            e.printStackTrace();
        }
    }

    private void editWmaFile(FileObject file, Folder parentFolder) throws ReaderExitException {
        System.out.println("convert file to MP3      "+file.getFilename());
        String shadowArchive = Settings.getInstance().getProperty(Settings.KEY_SHADOW_ARCHIVE);

        boolean res = FileHelper.copyFile(
                            file.getFilename(),
                            archiveFileSystem.getArchive().getPath() + archivePath,
                            shadowArchive+archivePath );
        if (!res) {
            System.out.println(String.format("error while copying file '%s' to '%s", file.getFilename(), shadowArchive+archivePath));
            return;
        }

        String newFileName;
        try {
            newFileName = Converter.getInstance().convert(fullPath, file.getFilename());
        } catch (AudioFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (!FileHelper.removeFile(file.getFilename(), archiveFileSystem.getArchive().getPath() + archivePath)) {
            System.out.println(String.format("error while removing file '%s'", file.getFilename()));
            return;
        }

        file.setFilename(newFileName);
        file.setType(EFileType.MP3);
        editMp3File(file, parentFolder);
    }

    private void editMp3File(FileObject file, Folder parentFolder) throws ReaderExitException {
        String fullPathName = fullPath + file.getFilename();
        try {
            AudioTags tags = Mp3Tagger.getInstance().getMp3Tags(fullPathName);

            Titel titel;
            if (file.getId()==0L) {
                // File nicht in DB
                if (parentFolder.getType().equals(EFolderType.ALBUM) || parentFolder.getType().equals(EFolderType.SUBALBUM)) {
                    titel = selectTitelFromList(getScoredTitelList(tags));
                } else {
                    List<Titel> titelList = searchTitel(tags.getTitle(), tags.getArtist());
                    if (titelList.size() == 0) {
                        titel = new Titel();
                    } else if (titelList.size() == 1) {
                        titel = titelList.get(0);
                    } else {
                        System.out.println("Search result not unique");
                        for (Titel t :titelList) {
                            System.out.println("##### "+t.getAlbumId());
                        }
                        titel = titelList.get(0);
                    }
                }

                if (titel.getId()==0L) {
                    initTitel(titel, tags);
                    if (autoCreate) {
                        titel.setArtist(identifyArtist(tags.getArtist()));
                    } else {
                        System.out.println("create title for "+file.getFilename());
                        editTitel(titel, tags);
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
                editTitel(titel, tags);
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

    private void editTitel(Titel titel, AudioTags tags) throws ReaderExitException, MdbRestException {
        titel.setName(reader.readString("Title", titel.getName(), true));
        titel.setArtist(identifyArtist(tags.getArtist()));
        titel.setTracknr(reader.readInteger("TrackNo", titel.getTracknr(), true));
        titel.setComment(reader.readString("Comment", titel.getComment(), false));
        titel.setLength(reader.readInteger("Length", titel.getLength(), true));
        titel.setYear(reader.readString("Year", titel.getYear(), false));
        //titel.setGenre(reader.readString("Genre", tags.getGenre(), false));
    }

    private EFileType getFileTypeFromFileName(String fileName) {
        String extension = Files.getFileExtension(fileName);
        if (Strings.isEmpty(extension)) return EFileType.UNKNOWN;
        if (extension.equalsIgnoreCase("mp3")) {
            return EFileType.MP3;
        }
        if (extension.equalsIgnoreCase("wma")) {
            return EFileType.WMA;
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
                        //f.setType(EFolderType.UNKNOWN);
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
            String auswahl = reader.readString("Search for");
            List<Album> albums = albumClient.getAlbumByPattern(auswahl);
            auswahl = reader.readFromList("Search result", AlbumHelper.getList(albums, "New search"), "");
            album = AlbumHelper.getSelection(albums, auswahl);
        } while (album == null);
        return album;
    }

    private Album selectAlbum(Album album) throws ReaderExitException, MdbRestException {
        String auswahl = reader.readString("Album name");
        album = albumClient.getAlbumByName(auswahl);
        if (album == null) {
            album = new Album();
            album.setName(auswahl);
            album = albumClient.saveAlbum(album);
        }
        return album;
    }

    private void editListFolder(Folder folder)   throws ReaderExitException, MdbRestException {
        folderClient.saveFolder(folder);
    }

    private void editCollectionFolder(Folder folder)   throws ReaderExitException, MdbRestException {
        folderClient.saveFolder(folder);
    }

    private void editSubalbumFolder(Folder folder)   throws ReaderExitException, MdbRestException {
        Subalbum subalbum = null;
        String searchString = "";

        Album parentAlbum = albumClient.getAlbumById(parentFolder.getObjectId());

        List<String> albumSelectList = new ArrayList<>();
        int subalbumSelection = 0;
        int existingSubalbum = -1;
        int preselectedSubalbum = 0;

        int ix = -1;
        for (Subalbum sa : parentAlbum.getSubalbums()) {
            ix++;
            if (!sa.getName().equals("Titelliste")) {
                albumSelectList.add(sa.getName());
            }
            if (sa.getId()==folder.getObjectId()) {
                existingSubalbum = ix;
            }
            if (sa.getName().equalsIgnoreCase(folder.getName())) {
                preselectedSubalbum = ix;
            }
        }

        albumSelectList.add("-- create subalbum");

        subalbumSelection = (folder.getObjectId()==0) ? preselectedSubalbum : existingSubalbum;
        subalbumSelection = reader.readFromList("select a subalbum", albumSelectList, subalbumSelection);

        if (subalbumSelection == albumSelectList.size()-1) {
            searchString = reader.readString("subalbum name", folder.getName(), true);
            subalbum = new Subalbum();
            subalbum.setParentId(parentAlbum.getId());
            subalbum.setName(searchString);
            subalbum = albumClient.saveSubalbum(subalbum);
        } else {
            subalbum = parentAlbum.getSubalbums().get(subalbumSelection);
        }

        folder.setObjectId(subalbum.getId());
        folderClient.saveFolder(folder);
    }

    private void editAlbumFolder(Folder folder)   throws ReaderExitException, MdbRestException {
        Album album;
        Album targetAlbum = null;
        String searchString = "";

        List<String> albumSelectList = new ArrayList<>();
        List<ScoredAlbum> scoredAlbumList;
        int albumSelection = 0;

        if (folder.getObjectId()!=0L) {
            album = albumClient.getAlbumById(folder.getObjectId());
            searchString = album.getName();
            albumSelectList.add(String.format("retain (%s)", searchString));
            albumSelectList.add("-- search album");
            albumSelection = reader.readFromList("select an album", albumSelectList, 0);
            if (albumSelection==0) {
                targetAlbum = album;
            }
        } else {
            targetAlbum = searchAlbum(folder.getName());
        }

        while (targetAlbum==null) {
            searchString = reader.readString("search phrase", searchString, true);
            targetAlbum = searchAlbum(searchString);
        }

        folder.setObjectId(targetAlbum.getId());
        folderClient.saveFolder(folder);
    }

    private Album searchAlbum(String searchString) throws MdbRestException, ReaderExitException {
        List<String> albumSelectList = new ArrayList<>();
        List<ScoredAlbum> scoredAlbumList;
        int albumSelection = 0;

        scoredAlbumList = getScoredAlbumList(searchString);
        for (ScoredAlbum sa : scoredAlbumList) {
            albumSelectList.add(String.format("%s (%s)", sa.getAlbum().getName(), sa.getAlbum().getArtist().getName()));
        }
        albumSelectList.add("-- search album");
        albumSelectList.add("-- create new album");
        albumSelection = reader.readFromList("select an album", albumSelectList, albumSelection);
        if (albumSelection < albumSelectList.size()-2) {
            return scoredAlbumList.get(albumSelection).getAlbum();
        }
        if (albumSelection == albumSelectList.size()-1) {

            Album newAlbum = new Album();
            String name = reader.readString("album name", searchString, true);
            newAlbum.setName(name);
            Artist artist = getArtist(parentFolder.getType().equals(EFolderType.ARTIST) ? parentFolder.getName() : null);
            newAlbum.setArtist(artist);
            do {
                String release = reader.readString("Release","");
                try {
                    if (StringUtils.isEmpty(release)) break;
                    Date releaseDate = Util.sdf.parse(release);
                    newAlbum.setRelease(release);
                    break;
                } catch (ParseException e) {
                    System.out.println("incorrect date");
                }
            } while(true);
            newAlbum = albumClient.saveAlbum(newAlbum);
            return newAlbum;
        }
        return null;
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
                if (album.getSubalbums()==null || album.getSubalbums().size()==0) {
                    subalbumId = 0L;
                } else {
                    subalbumId = album.getSubalbums().get(0).getId();
                }
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
        if (parentFolder.getType().equals(EFolderType.COLLECTION)) {
            query.setScoreCount(1);
        }
        query.setAlbumId(albumId==null?0L:albumId);
        query.setScoreMax(10);

        List<ScoredTitel> result = searchClient.searchTitelBlur(query);
        return result;
    }

    private Artist getArtist(String searchString) throws ReaderExitException, MdbRestException {
        Artist artist = null;
        boolean artistFound = false;
        do {
            if (StringUtils.isEmpty(searchString)) {
                searchString = reader.readString("Artist", "", true);
            }
            List<ScoredArtist> scoredArtistList = getScoredArtistList(searchString);
            artistFound = isArtistFound(scoredArtistList);
            List<String> searchList = new ArrayList<>();
            for (ScoredArtist sa : scoredArtistList) {
                searchList.add(sa.getArtist().getName());
            }
            searchList.add("new search");
            if (!artistFound) {
                searchList.add(String.format("create new artist '%s'", searchString));
            }
            int searchResult = reader.readFromList("select an artist", searchList, 0);
            if ((artistFound && searchResult == searchList.size()-1) || (!artistFound && searchResult == searchList.size()-2)) continue;
            if (!artistFound && searchResult == searchList.size()-1) {
                artist = new Artist();
                artist.setName(searchString);
                artist = artistClient.saveArtist(artist);
            } else {
                artist = scoredArtistList.get(searchResult).getArtist();
            }
        } while (artist == null);
        return artist;
    }

    private boolean isArtistFound(List<ScoredArtist> list) {
        if (list==null) return false;
        if (list.size()==0) return false;
        if (list.get(0).getScore()==0) return true;
        return false;
    }

    private List<ScoredArtist> getScoredArtistList(String artistName) throws MdbRestException {
        SearchArtistBlurQuery artistQuery = new SearchArtistBlurQuery();

        artistQuery.setArtist(artistName);
        artistQuery.setScoreMax(5);
        artistQuery.setScoreCount(10);
        return searchClient.searchArtistBlur(artistQuery);
    }

    private List<ScoredAlbum> getScoredAlbumList(String albumName) throws MdbRestException {
        SearchAlbumBlurQuery albumQuery = new SearchAlbumBlurQuery();

        albumQuery.setAlbum(albumName);
        albumQuery.setScoreMax(5);
        albumQuery.setScoreCount(10);
        return searchClient.searchAlbumBlur(albumQuery);
    }

    private List<Titel> searchTitel(String name, String artist) throws MdbRestException {

        SearchTitelQuery query = new SearchTitelQuery();
        query.setTitel(name);
        query.setArtist(artist);
        List<Titel> result = searchClient.searchTitel(query);

        return result;
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
            nameTypeList.add(normalizeFilename(String.format("%02d - %s - %s", titel.getTracknr(), titel.getArtist().getName(), titel.getName())));
            nameTypeList.add(normalizeFilename(String.format("%s - %s", titel.getArtist().getName(), titel.getName())));
            nameTypeList.add(normalizeFilename(String.format("%02d - %s", titel.getTracknr(), titel.getName())));
            renameOption = reader.readFromList("Auswahl", nameTypeList, 0);
        }
        switch (renameOption) {
            case 0:
                return;
            case 1:
                newFilename = normalizeFilename(String.format("%02d - %s - %s.mp3", titel.getTracknr(), titel.getArtist().getName(), titel.getName()));
                break;
            case 2:
                newFilename = normalizeFilename(String.format("%s - %s.mp3", titel.getArtist().getName(), titel.getName()));
                break;
            case 3:
                newFilename = normalizeFilename(String.format("%02d - %s.mp3", titel.getTracknr(), titel.getName()));
                break;
        }
        int ix = fullPathName.lastIndexOf("/");
        String newPathName = fullPathName.substring(0, ix+1) + newFilename;
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

    private void listRecursive(String dir, boolean edit) throws ReaderExitException{
        System.out.println(String.format("check dir '%s'", dir));
        try {
            FolderContent content = contentClient.getContentByPath(archiveId, dir);
            FolderContent newContent = getNewContent(archiveFileSystem, content);
            //System.out.println(String.format("---> new: %d, reg: %d", newContent.getTrackList().size(), content.getTrackList().size()));
            for (Folder fo : newContent.getFolderList()) {
                System.out.println(String.format("  -- unknown folder '%s'", fo.getName()));
            }
            for (FileObject fo : newContent.getTrackList()) {
                if (edit) {
                    boolean createIt = reader.readBoolean(String.format("unknown content '%s': create?", fo.getFilename()), true);
                    if (createIt) {
                        FileObject fileObject = getFileObject(fo.getFilename(), true);
                        Folder parentFolderRec = folderClient.getFolderByPath(archiveFileSystem.getArchive().getId(), archiveFileSystem.getCurrentDir());
                        if (!parentFolderRec.getType().equals(EFolderType.ALBUM)) {
                            System.out.println("Extras are allowed in album folders only");
                        } else {
                            editFile(fileObject, parentFolderRec);
                        }
                    }

                } else {
                    System.out.println(String.format("  -- unknown content '%s'", fo.getFilename()));
                }
            }
            for (Folder folder  : content.getFolderList() ) {
                archiveFileSystem.changeCurrentDir(folder.getName());
                listRecursive(dir + (dir.endsWith("/")?"":"/") + folder.getName(), edit);
                archiveFileSystem.changeCurrentDir("..");
            }
        } catch (FileSystemException e) {
            e.printStackTrace();
        } catch (MdbRestException e) {
            e.printStackTrace();
        }

    }

    private String normalizeFilename(String fn) {
        return fn
                .replace(":","")
                .replace("*","+")
                .replace("?","_")
                .replace("\r"," ")
                .replace("\"","")
                .replace("/","_");
    }
}