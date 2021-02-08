package de.wko.mdb.cli;

import de.wko.mdb.fs.ArchiveFileSystem;
import de.wko.mdb.fs.AbstractFileSystem;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class MdbPromptProvider implements PromptProvider {
    @Autowired
    CliContext context;

    @Override
    public AttributedString getPrompt() {
        String prompt = "mdb> ";
        AbstractFileSystem fs = context.getCurrentFileSystem();
        if (fs!=null && fs instanceof ArchiveFileSystem) {
            String aname = ((ArchiveFileSystem) fs).getArchive().getName().replaceAll("\\s","");
            String hname = ((ArchiveFileSystem) fs).getHost().getName().replaceAll("\\s","");
            prompt = aname+"@"+hname+"> ";
        }
        //AttributedStyle as = AttributedStyle.DEFAULT.background(AttributedStyle.RED);
        AttributedStyle as = AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN);
        as.foreground(AttributedStyle.BLUE);
        return new AttributedString(prompt, as);
    }
}
