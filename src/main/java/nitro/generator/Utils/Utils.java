package nitro.generator.Utils;

import nitro.generator.Gui;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.io.File.separator;
import static nitro.generator.Gui.gui;

public class Utils {

    public static String localPath = System.getProperty("user.home");
    public static final String configPath = localPath + separator + "nitro-generator";
    static File configFolder = new File(configPath);
    public static File savingFolder = new File(configFolder + separator + "Saves");

    public static void create() {
        if (!configFolder.exists()) {
            configFolder.mkdir();
        }
    }

    public static boolean isNew() {
        if (configFolder.exists()) return false;
        else return true;
    }

    public static void write(String subpath, String text) throws IOException {
        FileWriter writer = new FileWriter(configPath + separator + subpath);
        writer.write(text);
        writer.close();
    }
    public static void exit() {
        System.exit(0);
    }
    public static void reload(){
        SwingUtilities.invokeLater(() -> {
            gui.dispose();
            Gui newGui = new Gui();
        });
    }
    public static void createSaving() {
        if (!savingFolder.exists()) {
            savingFolder.mkdir();
        }
    }
}