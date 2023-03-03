package nitro.generator;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

import java.awt.*;

import static nitro.generator.Utils.Startup.theme;
import static nitro.generator.Utils.Utils.exit;

public class Gui {
    String selectedTheme = theme();

    public Gui() {
        JFrame gui = new JFrame("Nitro generator");
        switch (selectedTheme) {
            case "Darcula":
                FlatDarculaLaf.install();
                break;
            case "Dark":
                FlatDarkLaf.install();
                break;
            case "Light":
                FlatLightLaf.install();
                break;
            default:
                FlatDarkLaf.install();
                break;
        }
        SwingUtilities.updateComponentTreeUI(gui); // tysm StackOverflow <3


        gui.setJMenuBar(mainMenuBar());
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(600, 450);
        gui.setVisible(true);
    }
    public static JMenuBar mainMenuBar() {
        JMenuBar tabs = new JMenuBar();

        JMenu editMenu = new JMenu("Edit");
        JMenu generatorMenu = new JMenu("Generator");
        JMenu windowMenu = new JMenu("Window");

        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> exit());
        editMenu.add(exitItem);
        editMenu.add(saveItem);

        JMenuItem threadsItem = new JMenuItem("Threads"); //todood
        generatorMenu.add(threadsItem);

        JMenuItem themeItem = new JMenuItem("Theme"); //todod
        windowMenu.add(themeItem);

        tabs.add(editMenu);
        tabs.add(generatorMenu);
        tabs.add(windowMenu);

        return tabs;
    }
}