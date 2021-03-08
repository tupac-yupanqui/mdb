package de.wko.mdb.cli.tools;

import de.wko.mdb.cli.Commands;
import de.wko.mdb.cli.cmd.ArchiveCommands;
import de.wko.mdb.cli.cmd.FilesystemCommands;
import de.wko.mdb.cli.cmd.StatusCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ScriptRunner {

    @Autowired
    ArchiveCommands archiveCommands;
    @Autowired
    StatusCommands statusCommands;
    @Autowired
    FilesystemCommands filesystemCommands;

    public void run() {
        statusCommands.openArchive(4L);
        filesystemCommands.cd("Aeternitas");
        filesystemCommands.cd("House of Usher");

    }

    public void run(String script) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(script));
            String line;
            int lineNo = 0;
            while ((line = br.readLine()) != null) {
                lineNo++;
                line = line.trim();
                if (line.length()==0) {
                    continue;
                }
                if (line.startsWith("//")) {
                    continue;
                }
                try {
                    processLine(line);
                } catch (SyntaxException e) {
                    System.out.println("Syntaxfehler in Zeile "+lineNo+":");
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(String.format("Script '%s' kann nicht geöffnet werden",script));
            e.printStackTrace();
        }
    }

    public void start(List<String> startList) {
        if (startList!=null && startList.size()>0) {
            for (String script : startList) {
                run(script);
            }
        }
    }

    public void processLine(String line) throws SyntaxException {
        List<String> tokens = tokenize(line);
        if (tokens.size()==0) return;
        String token1 = tokens.get(0);
        if (token1.equalsIgnoreCase(Commands.C1_OPEN)) {
            if (tokens.size()<2) {
                throw new SyntaxException("'open' ist kein gueltiges Kommando");
            }
            String token2 = tokens.get(1);
            if (token2.equalsIgnoreCase(Commands.C2_ARCHIVE)) {
                if (tokens.size()<2) {
                    throw new SyntaxException(String.format("'%s' ohne Parameter ist nicht gültig", Commands.CMD_OPEN_ARCHIVE));
                }
                try {
                    Long archiveId = Long.parseLong(tokens.get(2));
                    statusCommands.openArchive(archiveId);
                } catch (NumberFormatException e) {
                    throw new SyntaxException(String.format("'%s %s': Parameter muss numerisch sein", Commands.CMD_OPEN_ARCHIVE, tokens.get(2)));
                }
            }
            return;
        }
        if (token1.equalsIgnoreCase(Commands.CMD_CHANGE_DIR)) {
            if (tokens.size()==1) {
                filesystemCommands.ls();
            } else if (tokens.size()!=2) {
                throw new SyntaxException(String.format("'%s' ist kein gueltiges Kommando", line));
            } else {
                filesystemCommands.cd(tokens.get(1));
                return;
            }
        }
        throw new SyntaxException(String.format("Unbekanntes Kommando '%s'", line));
    }

    private List<String> tokenize(String line) {
        List<String> result = new ArrayList<>();
        String regex = "\"([^\"]*)\"|(\\S+)";

        Matcher m = Pattern.compile(regex).matcher(line);
        while (m.find()) {
            if (m.group(1) != null) {
                result.add(m.group(1));
                //System.out.println("Quoted [" + m.group(1) + "]");
            } else {
                result.add(m.group(2));
                //System.out.println("Plain [" + m.group(2) + "]");
            }
        }
        return result;
    }

    private static class SyntaxException extends Exception {
        public SyntaxException(String s) {
            super(s);
        }
    }
}
