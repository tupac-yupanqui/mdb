package de.wko.mdb.cli.cmd;

import de.wko.mdb.cli.CliContext;
import de.wko.mdb.cli.tables.FolderTable;
import de.wko.mdb.cli.tools.*;
import de.wko.mdb.fs.ArchiveFileSystem;
import de.wko.mdb.fs.FileSystemException;
import de.wko.mdb.fs.LocalFileSystem;
import de.wko.mdb.fs.sort.FileComparator;
import de.wko.mdb.rcl.*;
import de.wko.mdb.types.*;
import de.wko.mdb.types.enums.EFolderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

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
    ArtistClient artistClient;

    @Autowired
    AlbumClient albumClient;

    @Autowired
    CliContext context;

    @ShellMethod(value = "Liste der Inhalte", key = "list content")
    public void listFolders(@ShellOption(value = "-n", help = "nur neue Inhalte") boolean newOnly,
                            @ShellOption(value = "-x", help = "nur existierende Inhalte") boolean existOnly) {
        if (!context.getCurrentFileSystem().isFilesystemAvailable()
                || context.getCurrentFileSystem() instanceof LocalFileSystem) {
            System.out.println("kein Archiv geöffnet");
            return;
        }
        ArchiveFileSystem fs = (ArchiveFileSystem) context.getCurrentFileSystem();
        try {
            Long aid = fs.getArchive().getId();

            FolderContent content = contentClient.getFoldersByArchiveId(aid, fs.getCurrentDir());
            if (content == null) {
                System.out.println("Der übergeordnete Folder existiert nicht und muss vorher angelegt werden.");
                return;
            }
            FolderContent newContent = getNewContent(fs, content);
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

    @ShellMethod(value = "Inhalt bearbeiten", key = "edit content")
    public void editContent(
            @ShellOption(defaultValue = "") String objectToEdit,
            @ShellOption(value = "-n", help = "nur neue Inhalte") boolean newOnly) {
        if (!context.getCurrentFileSystem().isFilesystemAvailable()
                || context.getCurrentFileSystem() instanceof LocalFileSystem) {
            System.out.println("kein Archiv geöffnet");
            return;
        }
        ConsoleReader reader = new ConsoleReader();
        ArchiveFileSystem fs = (ArchiveFileSystem) context.getCurrentFileSystem();
        Long archiveId = fs.getArchive().getId();
        String basepath = fs.getCurrentDir();
        if (!basepath.equals("/")) basepath += "/";
        try {
            if (fs.isFilesystemAvailable()) {
                Folder parentFolder = folderClient.getFolderByPath(archiveId, fs.getCurrentDir());
                if (parentFolder == null) {
                    System.out.println("Der übergeordnete Folder existiert nicht und muss vorher angelegt werden.");
                    return;
                }
                List<MdbFile> files = fs.listDir("");
                Collections.sort(files, FileComparator.getFileComparator(FileComparator.SORT_TYPE));
                for (MdbFile file : files) {
                    if (objectToEdit.length() > 0 && !file.getName().equalsIgnoreCase(objectToEdit)) {
                        continue;
                    }
                    if (file.isDirectory()) {
                        Folder folder = folderClient.getFolderByPath(archiveId, basepath + file.getName());
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
                        System.out.println("File "+file.getName());
                    }
                }
            } else {
                System.out.println("Kein Zugriff auf Verzeichnis");
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

            ConsoleReader reader = new ConsoleReader();
            String filename = reader.readString("Name", folder.getName(), true);
            if (!filename.equalsIgnoreCase(folder.getName())) {
                try {
                    context.getCurrentFileSystem().rename(folder.getName(), filename);
                    folder.setName(filename);
                } catch (FileSystemException e) {
                    System.out.println(e.getMessage());
                }
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

    private FolderContent getNewContent(ArchiveFileSystem fs, FolderContent existingContent) {
        List<Folder> newFolders = new ArrayList();
        List<Track> newTracks = new ArrayList<>();

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
                    System.out.println(file.getName());
                    if (!ListHelper.isFileInTrackList(file, existingContent.getTrackList())) {
                        Track t = new Track();
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
        ConsoleReader reader = new ConsoleReader();
        do {
            String auswahl = reader.readString("Suche nach");
            List<Artist> artists = artistClient.getArtistByPattern(auswahl);
            auswahl = reader.readFromList("Suchergebnis", ArtistHelper.getList(artists, "Neue Suche"), "");
            artist = ArtistHelper.getSelection(artists, auswahl);
        } while (artist == null);
        return artist;
    }

    private Artist selectArtist(Artist artist) throws ReaderExitException, MdbRestException {
        ConsoleReader reader = new ConsoleReader();
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
        ConsoleReader reader = new ConsoleReader();
        do {
            String auswahl = reader.readString("Suche nach");
            List<Album> albums = albumClient.getAlbumByPattern(auswahl);
            auswahl = reader.readFromList("Suchergebnis", AlbumHelper.getList(albums, "Neue Suche"), "");
            album = AlbumHelper.getSelection(albums, auswahl);
        } while (album == null);
        return album;
    }

    private Album selectAlbum(Album album) throws ReaderExitException, MdbRestException {
        ConsoleReader reader = new ConsoleReader();
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
        ConsoleReader reader = new ConsoleReader();
        Album album = folder.getObjectId() == 0L ? albumClient.getAlbumByName(folder.getName()) : albumClient.getAlbumById(folder.getObjectId());
        if (album == null) {
            List<String> auswahlAktion = Arrays.asList("Anlegen", "Suchen", "Eingabe");
            String auswahl = reader.readFromList(String.format("Artist '%s' (neu)", folder.getName()), auswahlAktion, "Anlegen");
            switch (auswahlAktion.indexOf(auswahl)) {
                case 0:
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
        ConsoleReader reader = new ConsoleReader();
        Artist artist = folder.getObjectId() == 0L ? artistClient.getArtistByName(folder.getName()) : artistClient.getArtistById(folder.getObjectId());
        if (artist == null) {
            List<String> auswahlAktion = Arrays.asList("Anlegen", "Suchen", "Eingabe");
            String auswahl = reader.readFromList(String.format("Album '%s' (neu)", folder.getName()), auswahlAktion, "Anlegen");
            switch (auswahlAktion.indexOf(auswahl)) {
                case 0:
                    artist = new Artist();
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

}