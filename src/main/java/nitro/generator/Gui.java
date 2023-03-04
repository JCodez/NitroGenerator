package nitro.generator;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import nitro.generator.Utils.GuiUtils;

import javax.swing.*;

import java.awt.*;

import static nitro.generator.Utils.Startup.theme;
import static nitro.generator.Utils.Utils.exit;

public class Gui {
    String selectedTheme = theme();
    public static JFrame gui;
    private static JLabel info;
    public Gui() {
        gui = new JFrame("Nitro generator");
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

        info = new JLabel("To begin, generator > start", SwingConstants.CENTER);
        Font boldFont = new Font("Arial", Font.BOLD, 20);
        info.setFont(boldFont);

        gui.add(info, BorderLayout.CENTER);
        gui.setJMenuBar(mainMenuBar());
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(650, 450);
        gui.setVisible(true);
    }
    public static JMenuBar mainMenuBar() {
        JMenuBar tabs = new JMenuBar();

        JMenu editMenu = new JMenu("Edit");
        JMenu generatorMenu = new JMenu("Nitro");
        JMenu windowMenu = new JMenu("Window");

        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> exit());
        editMenu.add(exitItem);
        editMenu.add(saveItem);

        JMenuItem startItem = new JMenuItem("Start");
        startItem.addActionListener(e -> begin());
        JMenuItem threadsItem = new JMenuItem("Threads");
        threadsItem.addActionListener(e -> GuiUtils.threads());
        generatorMenu.add(startItem);
        generatorMenu.add(threadsItem);


        JMenuItem themeItem = new JMenuItem("Theme");
        themeItem.addActionListener(e -> GuiUtils.theme(false));
        windowMenu.add(themeItem);

        tabs.add(editMenu);
        tabs.add(generatorMenu);
        tabs.add(windowMenu);

        return tabs;
    }
    private static void begin(){
        info.setVisible(false);
        gui.add(GuiUtils.generatorGui());



        gui.revalidate();

    }
}