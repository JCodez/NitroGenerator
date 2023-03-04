package nitro.generator.Utils;

import com.formdev.flatlaf.FlatDarkLaf;
import nitro.generator.Gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Random;

import static java.io.File.separator;
import static nitro.generator.Gui.gui;
import static nitro.generator.Utils.Utils.configPath;

public class GuiUtils {
    public static void theme(boolean logic) {
        FlatDarkLaf.install();
        String[] themes = {"Darcula", "Dark", "Light"};
        String theme = (String) JOptionPane.showInputDialog(null, "Select a theme", "Theme", JOptionPane.PLAIN_MESSAGE, null, themes, themes[0]);
        if (theme != null) {
            Utils.create();
            try {
                Utils.write("theme.txt", theme);
                if (logic) {
                    Utils.write("threads.txt","10");
                    new Gui();
                } else if (!logic) {
                    gui.dispose();
                    new Gui();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static int threadsValue(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(configPath + separator + "threads.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Integer.parseInt(line);
    }

    public static void threads() {
        FlatDarkLaf.install();
        Integer[] values = new Integer[501];
        for (int i = 0; i <= 500; i++) {
            values[i] = i;
        }
        JComboBox<Integer> comboBox = new JComboBox<>(values);
        comboBox.setSelectedItem(10);

        int result = JOptionPane.showOptionDialog(null, comboBox, "Select amount of codes", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (result == JOptionPane.OK_OPTION) {
            int amount = (int) comboBox.getSelectedItem();
            try {
                Utils.write("threads.txt", String.valueOf(amount));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static JScrollPane generatorGui() {
        JTextArea txt = new JTextArea();
        txt.setFont(new Font("Arial", Font.PLAIN, 15));
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txt);
        scrollPane.setPreferredSize(new Dimension(600, 450));

        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //skidding my old code ??
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "1234567890";
        String characters = upperCase + lowerCase + numbers;
        Random random = new Random();
        for (int i = 0; i < threadsValue(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 16; j++) {
                int index = random.nextInt(characters.length());
                char c = characters.charAt(index);
                sb.append(c);
            }
            txt.append("discord.gift/" + sb.toString() + "\n");
        }

        return scrollPane;
    }
}