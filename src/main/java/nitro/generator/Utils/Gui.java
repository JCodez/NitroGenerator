package nitro.generator.Utils;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

import static nitro.generator.Startup.theme;
import static nitro.generator.Utils.Utils.isNew;

public class Gui extends JFrame {
    String selectedTheme = theme();

    public Gui() {

        switch (selectedTheme) {
            case "Discord":
                 //SOON trademarks
                break;
            case "Dark":
                FlatDarkLaf.install();
                break;
            case "Light":
                FlatLightLaf.install();
                break;
            default:
                dispose();
                break;
        }

    }
}

