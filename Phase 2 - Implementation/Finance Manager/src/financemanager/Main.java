package financemanager;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.awt.*;
import java.net.*;
import java.util.logging.*;
import javax.swing.*;

import financemanager.gui.*;

public class Main {
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                login();
                checkUpdates();
                createAndShowGUI();
                gui();
            }
        });
    }
    public static void login() {
        Login log = new Login();
        for(int i=0; i<=4; i++) {
            boolean correct = log.getIDandPassword();
            if(correct == true) {
                break;
            }
            if(i > 3 && correct == false) {
                System.exit(0);
            }
        }
    }
    public static void checkUpdates() {
        // this method is to check a website for any updates.
        try {
            URL url = new URL("http://toms-cloud-dev.bugs3.com/Farm%20Finance%20Manager/updateChecker.html");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            int rc = conn.getResponseCode();
            if(rc != 404) {
                System.out.println(rc + " - URL exists");
                //UpdateChecker updater = new UpdateChecker();
            }
            else {
                System.out.println(rc + " - URL Does NOT exisit");
            }
            conn.disconnect();
        }
        catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void gui() {
        GUI gui = new GUI();
        gui.setSize(new Dimension(1000, 590));
        gui.setResizable(false);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true); //display frame
        //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //gui.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    private static void createAndShowGUI() {
        if(useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch(Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }
    }
}