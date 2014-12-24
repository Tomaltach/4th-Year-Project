package beefmodule.gui.panels;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.util.*;
import javax.swing.*;

import beefmodule.datahandler.*;
import beefmodule.sqlhandler.*;
import spreadsheet.*;

public class Medical_Overview_JPanel extends JPanel {
    // objects
    Panel_Elements gui = new Panel_Elements();
    SQL_Beef_Handler sql = new SQL_Beef_Handler();
    Spreadsheet ss = new Spreadsheet();

    // variables
    int width = 700;
    int height = 450;

    List<Medical_Data> medData;
    List<Vet_Visit_Data> vvData;

    public Medical_Overview_JPanel(){
        gui.overviewPanel = new JPanel();
        gui.panel = new JPanel();
        init();
    }
    public void init() {
        selectAll();
        spreadSheet();
    }
    public JPanel overviewPanel() {
        return gui.overviewPanel;
    }
    public void spreadSheet() {
        gui.panel.add(ss.getMedOverviewSpreadsheet(width, height, medData, vvData));
        gui.overviewPanel.add(gui.panel);
    }
    public void refresh() {
        selectAll();
        ss.updateMedOverview(ss, medData, vvData);
        gui.panel.revalidate();
    }
    public void selectAll() {
        try {
            medData = sql.select_medical_data();
            vvData = sql.select_vet_visit_data();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}