package nitro.generator;

import com.formdev.flatlaf.FlatDarkLaf;
import nitro.generator.Utils.Utils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.io.File.separator;
import static nitro.generator.Utils.Utils.configPath;
import static nitro.generator.Utils.Utils.isNew;

public class Startup {
    static void init() {
        if (!isNew()) {
            FlatDarkLaf.install();
            String[] themes = {"Discord", "Dark", "Light"};
            String theme = (String) JOptionPane.showInputDialog(null, "Select a theme", "Theme", JOptionPane.PLAIN_MESSAGE, null, themes, themes[0]);

            if (theme != null) {
                Utils.create();
                try {
                    Utils.write("theme.txt", theme);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
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