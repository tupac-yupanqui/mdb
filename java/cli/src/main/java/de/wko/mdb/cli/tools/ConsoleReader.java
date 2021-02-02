package de.wko.mdb.cli.tools;

import org.apache.logging.log4j.util.Strings;

import java.util.Scanner;

public class ConsoleReader {
    private Scanner scanner = new Scanner(System.in);

    public ConsoleReader() {

    }

    public String readStringRequired(String label, String def) {
        return readString(label, def, true);
    }
    public String readString(String label, String def) {
        return readString(label, def, false);
    }
    public String readString(String label, String def, boolean required) {
        if (def==null) def="";
        do {
            System.out.print(String.format("%s%s [%s]: ", label, required?"*":"", def));
            String inp = scanner.nextLine();
            if (Strings.isNotEmpty(inp)) {
                if (inp.equals("##")) {
                    if (required) {
                        System.out.println("Eingabe erforderlich");
                        def="";
                        continue;
                    }
                    return "";
                }
                return inp;
            } else {
                if (required && Strings.isEmpty(inp)) {
                    System.out.println("Eingabe erforderlich");
                    def="";
                    continue;
                }
                return def;
            }
        } while (true);
    }

    public Boolean readBoolean(String label, boolean def) {
        System.out.print(String.format(label+" [%s]: ", def?"J/n":"j/N"));
        String inp = scanner.nextLine();
        if (Strings.isNotEmpty(inp)) {
            return inp.equalsIgnoreCase("J");
        } else {
            return def;
        }

    }
}
