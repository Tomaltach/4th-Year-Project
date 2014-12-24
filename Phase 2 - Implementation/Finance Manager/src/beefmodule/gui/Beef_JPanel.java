package beefmodule.gui;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.awt.event.*;
import javax.swing.*;

import beefmodule.sqlhandler.*;
import spreadsheet.*;

public class Beef_JPanel extends JPanel {
    // objects
    Beef_GUI_Elements gui = new Beef_GUI_Elements();
    SQL_Beef_Handler sqlBeef = new SQL_Beef_Handler();
    Spreadsheet ss = new Spreadsheet();

    // variables
    String display = "";

    public Beef_JPanel() {
        gui.beefPanel = new JPanel();
        init();
    }
    public void init() {
        // Compact panel before sending to JFrame in financemanger.gui.GUI.java
        gui.panel = new JPanel();
        gui.beefPanel.add(gui.panel);
        spreadSheet();
    }
    public JPanel beefPanel() {
        return gui.beefPanel;
    }
    public void spreadSheet() {
        gui.panel.add(ss.getSpreadsheet());
        gui.beefPanel.add(gui.panel);
    }
    public void displayData() {

    }
    public void refresh() {
        displayData();
        //ss.updateHerd(ss, herdData);
        gui.panel.revalidate();
    }
}