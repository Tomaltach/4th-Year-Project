package beefmodule.gui.panels;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

import beefmodule.datahandler.*;
import beefmodule.sqlhandler.*;
import financemanager.*;
import net.java.dev.designgridlayout.*;
import spreadsheet.*;

public class Show_Herd_JPanel extends JPanel {
    // objects
    Panel_Elements gui = new Panel_Elements();
    SQL_Beef_Handler sql = new SQL_Beef_Handler();
    Strings string = new Strings(); // to use reused information strings
    Spreadsheet ss = new Spreadsheet();
    public static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);

    // variables
    int width = 700;
    int height = 400;

    int herdId;
    String herdDate;

    List<Herd_Data> herdData;

    public Show_Herd_JPanel() {
        gui.showHerdPanel = new JPanel();
        gui.panel = new JPanel();
        try {
            herdData = sql.select_herd_list_data();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        init();
    }
    public void init() {
        DesignGridLayout layout = new DesignGridLayout(gui.panel);
        showOptions(layout);
    }
    public JPanel showHerdPanel() {
        return gui.showHerdPanel;
    }
    public void showOptions(DesignGridLayout layout) {
        layout.row().center().add(ss.getHerdListSpreadsheet(width, 0, height, herdData));
        
        gui.showHerdPanel.add(gui.panel);
    }
    public void refresh() {
        displayData();
        ss.updateLHerd(ss, herdData);
        gui.panel.revalidate();
    }
    public void displayData() {
        try {
            herdData = sql.select_herd_list_data();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void getHData() {
        try {
            herdData = sql.select_herd_list_data();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}