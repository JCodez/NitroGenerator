package nitro.generator;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.ui.FlatProgressBarUI;
import nitro.generator.Utils.GuiUtils;
import nitro.generator.Utils.Utils;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static nitro.generator.Utils.GuiUtils.*;
import static nitro.generator.Utils.Startup.theme;
import static nitro.generator.Utils.Utils.*;

public class Gui {
    public static JFrame gui;
    static String selectedTheme = theme();
    private static JLabel info;
    public static JMenuItem threadsItem;
    private static boolean isInstance = false;

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
        JMenuItem reloadItem = new JMenuItem("Reload");
        JMenuItem exitItem = new JMenuItem("Exit");
        reloadItem.addActionListener(e -> reload());
        exitItem.addActionListener(e -> exit());
        saveItem.addActionListener(e -> saving());
        editMenu.add(exitItem);
        editMenu.add(reloadItem);
        editMenu.add(saveItem);

        JMenuItem startItem = new JMenuItem("Start");
        startItem.addActionListener(e -> begin());
        threadsItem = new JMenuItem("Threads [" + threadsValue() + "]");
        threadsItem.addActionListener(e -> GuiUtils.threads());
        generatorMenu.add(startItem);
        generatorMenu.add(threadsItem);


        JMenuItem themeItem = new JMenuItem("Theme [" + theme() + "]");
        themeItem.addActionListener(e -> GuiUtils.theme(false));
        windowMenu.add(themeItem);

        tabs.add(editMenu);
        tabs.add(generatorMenu);
        tabs.add(windowMenu);

        return tabs;
    }

    private static void begin() {
        isInstance = true;
        info.setVisible(false);
        JProgressBar progressBar = new JProgressBar();
        progressBar.setUI(new FlatProgressBarUI());
        GuiUtils.startTimer(progressBar, 420);
        gui.add(progressBar);
    }

    private static void saving() {
        if (isInstance && !info.isVisible()) {
            Utils.createSaving();
            JFileChooser fileChooser = new JFileChooser(savingFolder);

            int returnValue = fileChooser.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                FileWriter writer = null;
                try {
                    writer = new FileWriter(selectedFile.getAbsolutePath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    writer.write(codes.getText());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e); // try catches r so annoying AAAA
                }
            }
        }
    }
}