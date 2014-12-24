package financemanager.gui.panels;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */


import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import financemanager.*;
import net.java.dev.designgridlayout.DesignGridLayout;

public class Welcome_JPanel extends JPanel {
    // objects
    Strings string = new Strings(); // to use reused information strings

    // local variables
    JPanel panel = new JPanel();
    JLabel lblWelcome;
    JLabel picLabel;
    String welcome = "";
    BufferedImage myPicture;

    public Welcome_JPanel(){
        init();
    }
    public final void init() {
        DesignGridLayout layout = new DesignGridLayout(panel);
        showOptions(layout);
    }
    public JPanel welcomePanel() {
        return panel;
    }
    public void showOptions(DesignGridLayout layout) {
        lblWelcome = new JLabel(string.welcome());
        try {
            myPicture = ImageIO.read(new File("resources/welcome-image.jpg"));
            picLabel = new JLabel(new ImageIcon(myPicture));

        }
        catch(IOException e) {
            e.printStackTrace();
        }

        layout.row().center().add(lblWelcome);
        layout.row().center().add(picLabel);
    }
}