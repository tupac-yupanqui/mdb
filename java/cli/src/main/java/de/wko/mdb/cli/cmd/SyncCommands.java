package de.wko.mdb.cli.cmd;

import de.wko.mdb.cli.CliContext;
import de.wko.mdb.cli.tools.FileSystemManager;
import de.wko.mdb.fs.ArchiveFileSystem;
import de.wko.mdb.fs.FileSystemException;
import de.wko.mdb.fs.LocalFileSystem;
import de.wko.mdb.rcl.*;
import de.wko.mdb.types.FileObject;
import de.wko.mdb.types.Folder;
import de.wko.mdb.types.FolderContent;
import de.wko.mdb.types.MdbFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@ShellComponent
@ShellCommandGroup(value="Sync commands")
public class SyncCommands {

    @Autowired
    CliContext context;

    @Autowired
    FileSystemManager fsmanager;

    @Autowired
    ContentClient contentClient;

    @Autowired
    FolderClient folderClient;

    @Autowired
    FileClient fileClient;

    @Autowired
    ExtraClient extraClient;

    private class SyncContext {
        int countFiles = 0;
        int countDirs = 0;
        String sourceBaseDir;
        String targetBaseDir;
        Long sourceArchiveId;
        Long targetArchiveId;
    }
    private SyncContext syncContext = new SyncContext();

    @ShellMethod(value = "Clone archive from other archive", key = "clone archive")
    public void listArchives(@ShellOption(defaultValue="0") Long sourceArchiveId) {

        if (sourceArchiveId==0L) {
            System.out.println("Source archive not specified");
            return;
        }

        if (!context.getCurrentFileSystem().isFilesystemAvailable()
                || context.getCurrentFileSystem() instanceof LocalFileSystem) {
            System.out.println("no current archive");
            return;
        }

        ArchiveFileSystem targetArchiveFileSystem = (ArchiveFileSystem) context.getCurrentFileSystem();
        Long targetArchiveId = targetArchiveFileSystem.getArchive().getId();

        ArchiveFileSystem sourceArchiveFileSystem = (ArchiveFileSystem) fsmanager.createFileSystem(sourceArchiveId);
        if (sourceArchiveFileSystem==null || !(sourceArchiveFileSystem instanceof ArchiveFileSystem)) {
            System.out.println("Source file system not available");
            return;
        }

        if (sourceArchiveId==targetArchiveId) {
            System.out.println("Cannot clone archives with itself");
            return;
        }

        try {
            /*
            FolderContent content = contentClient.getContentByPath(targetArchiveId, targetArchiveFileSystem.getCurrentDir());
            if (content.getFolderList().size()>0 || content.getTrackList().size()>0) {
                System.out.println("There are content objects but target archive must be empty");
                return;
            }

             */
            /*
            List<MdbFile> files = targetArchiveFileSystem.listDir("");
            if (files.size()>0) {
                System.out.println("There are files but target archive must be empty");
                return;
            }

             */
            syncContext.sourceBaseDir = sourceArchiveFileSystem.getHost().getDrive()+sourceArchiveFileSystem.getArchive().getPath();
            syncContext.targetBaseDir = targetArchiveFileSystem.getHost().getDrive()+targetArchiveFileSystem.getArchive().getPath();
            File root = new File(syncContext.sourceBaseDir);
            syncContext.sourceArchiveId = sourceArchiveFileSystem.getArchive().getId();
            syncContext.targetArchiveId = targetArchiveFileSystem.getArchive().getId();

            Folder rootFolder = folderClient.getFolderByPath(syncContext.targetArchiveId, "");

            recursive(root, rootFolder.getId(),  (p, pid)->copyFile(p, pid));
            System.out.println(String.format("Clone result (Dir: %d, File: %d)", syncContext.countDirs, syncContext.countFiles));
  //      } catch (FileSystemException e) {
    //        e.printStackTrace();
        } catch (MdbRestException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    private Long copyFile(File f, Long pid) throws MdbRestException, IOException {
        String targetName = syncContext.targetBaseDir+f.getAbsolutePath().substring(syncContext.sourceBaseDir.length()).replace("\\","/");
        if (f.isDirectory()) {
            syncContext.countDirs++;
            Folder sourceFolder = folderClient.getFolderByPath(syncContext.sourceArchiveId, f.getAbsolutePath().substring(syncContext.sourceBaseDir.length()).replace("\\","/"));
            Folder targetFolder = folderClient.getFolderByPath(syncContext.targetArchiveId, f.getAbsolutePath().substring(syncContext.sourceBaseDir.length()).replace("\\","/"));
            if (targetFolder==null) {
                targetFolder = new Folder();
            }
            targetFolder.setName(sourceFolder.getName());
            targetFolder.setType(sourceFolder.getType());
            targetFolder.setObjectId(sourceFolder.getObjectId());
            targetFolder.setArchiveId(syncContext.targetArchiveId);
            targetFolder.setParentId(pid);
    //        targetFolder = folderClient.saveFolder(targetFolder);
//            new File(targetName).mkdirs();
 //           System.out.println("mkdir "+targetName);
            return targetFolder.getId();
        } else {
            syncContext.countFiles++;
            Folder sourceFolder = folderClient.getFolderByPath(syncContext.sourceArchiveId, f.getParent().substring(syncContext.sourceBaseDir.length()).replace("\\","/"));
            FileObject sourceFile = fileClient.getFileByFolderIdAndName(sourceFolder.getId(), f.getName());;
            if (sourceFile!=null) {
                FileObject targetFile = new FileObject();
                targetFile.setFilename(f.getName());
                targetFile.setType(sourceFile.getType());
                targetFile.setFolderId(pid);
                targetFile.setObjectId(sourceFile.getObjectId());
  //              fileClient.saveFile(targetFile);
            } else {
                System.out.println(f.getAbsolutePath());
            }
//            Files.copy(Path.of(f.getAbsolutePath()), Path.of(targetName), StandardCopyOption.REPLACE_EXISTING);
   //         System.out.println("copy to "+targetName);
        }
        return null;
}

    private void recursive(File root, Long parentId,  ProcessFile function) throws MdbRestException, IOException {
        for (final File fileEntry : root.listFiles()) {
            Long pid = function.process(fileEntry, parentId);
            if (fileEntry.isDirectory()) {
                if (pid==null) {
                    throw new MdbRestException("something went wrong\"");
                }
                recursive(fileEntry, pid, function);
            }
        }
    }

    @FunctionalInterface
    interface ProcessFile  {
        Long process(File file, Long parentId) throws MdbRestException, IOException;
    }

}
