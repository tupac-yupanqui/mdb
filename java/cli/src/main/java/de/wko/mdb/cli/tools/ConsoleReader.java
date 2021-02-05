package de.wko.mdb.cli.tools;

import org.apache.logging.log4j.util.Strings;

import java.util.List;
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
                if (required && Strings.isEmpty(inp) && Strings.isEmpty(def)) {
                    System.out.println("Eingabe erforderlich");
                    def="";
                    continue;
                }
                return def;
            }
        } while (true);
    }

    public String readEnum(String label, List<String> values, String def) {

        do {
            System.out.print(label +" ");
            for (int i=0; i<values.size(); i++) {
                if (i==0) {
                    System.out.println(String.format("(%d) %s", (i+1), values.get(i)));
                } else {
                    System.out.println(String.format("%s(%d) %s", " ".repeat(label.length()+1), (i+1), values.get(i)));
                }
            }

            System.out.print(String.format("Auswahl [%d]: ", values.indexOf(def)+1));
            String inp = scanner.nextLine().trim();
            if (Strings.isNotEmpty(inp)) {
                int r;
                try {
                    r = Integer.valueOf(inp);
                    if (r<1 || r>values.size()) {
                        System.out.println(String.format("Ungültige Eingabe (muss zwischen 1 und %d liegen)", values.size()));
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Eingabe (nicht numerisch)");
                    continue;
                }
                return values.get(r-1);
            } else {
                if (Strings.isEmpty(def)) {
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
