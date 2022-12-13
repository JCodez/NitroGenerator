package nitro.generator;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.Random;

public class Generator extends JFrame implements ActionListener {
    public static  JTextField field;
    private final JButton btn;
    private final  JButton copy;
    Color burple = new Color(88, 101, 242);
    Color background = new Color(61, 61, 72);
    Color bg = new Color(46, 46, 51);


    public Generator() {
        setLayout(null);
        setTitle("Nitro generator");

        field = new JTextField();
        field.setEditable(false);
        field.setBackground(background);
        field.setPreferredSize(new Dimension(330,50));
        field.setBorder(new LineBorder(burple,4));
        field.setForeground(Color.lightGray);
        add(field);

        copy = new JButton("Copy");
        add(copy);
        copy.addActionListener(e -> copyMethod());

        btn = new JButton("Generate");
        add(btn);
        btn.addActionListener(this);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 130);
        this.getContentPane().setBackground(bg);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);
    }
    public void copyMethod(){
        field.setBorder(new LineBorder(Color.pink,4));
        String theGift = field.getText();
        StringSelection selection = new StringSelection(theGift);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            field.setBorder(new LineBorder(burple,4));
            String uperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String lowerCase = "abcdefghijklmnopqrstuvwxyz";
            int num = 1234567890;
            String random = uperCase + lowerCase + num;
            StringBuilder result = new StringBuilder();
            Random rnd = new Random();
            while (result.length() < 16) {
                int index = (int) (rnd.nextFloat() * random.length());
                result.append(random.charAt(index));
            }
            String randomFinal = result.toString();
            String gift = "discord.gift/";
            field.setText(gift + randomFinal);
        }
    }
}

