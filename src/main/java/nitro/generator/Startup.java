package nitro.generator;

import com.formdev.flatlaf.FlatDarkLaf;
import nitro.generator.Utils.Utils;

import javax.swing.*;
import java.io.IOException;

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
}
