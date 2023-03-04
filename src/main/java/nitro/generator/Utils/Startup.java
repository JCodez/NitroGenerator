package nitro.generator.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.io.File.separator;
import static nitro.generator.Utils.Utils.configPath;
import static nitro.generator.Utils.Utils.isNew;

public class Startup {
    public static void init() {
        if (isNew()) {
           GuiUtils.theme(true);
        }
    }
    public static String theme() {
        FileReader reader = null;
        try {
            reader = new FileReader(configPath + separator + "theme.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader bufferedReader = new BufferedReader(reader);

        String selectedTheme = null;
        try {
            selectedTheme = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return selectedTheme; // screw try / catches
    }
}