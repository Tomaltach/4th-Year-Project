package financemanager.gui.panels;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.util.*;
import javax.swing.*;

import financemanager.datahandler.*;
import financemanager.sqlhandler.*;
import spreadsheet.*;

public class Basics_JPanel extends JPanel {
    // objects
    Panel_Elements gui = new Panel_Elements();
    SQL_Handler sql = new SQL_Handler();
    Spreadsheet ss = new Spreadsheet();

    // variables
    int width = 700;
    int height = 450;

    List<Fertilizer_Data> fData;
    List<Gas_Data> gData;
    List<Labour_Data> lData;
    List<Lighting_Data> liData;
    List<Machinery_Data> maData;
    List<Mortgaged_Data> mData;
    List<Rented_Data> rData;
    List<Slurry_Data> sData;
    List<Water_Data> wData;

    public Basics_JPanel(){
        gui.basicsPanel = new JPanel();
        gui.panel = new JPanel();
        init();
    }
    public void init() {
        selectAll();
        spreadSheet();
    }
    public JPanel basicsPanel() {
        return gui.basicsPanel;
    }
    public void spreadSheet() {
        gui.panel.add(ss.getSpreadsheet(width, height, fData, gData, lData, liData, maData, mData, rData, sData, wData));
        gui.basicsPanel.add(gui.panel);
    }
    public void refresh() {
        selectAll();
        ss.updateAllBasicData(ss, fData, gData, lData, liData, maData, mData, rData, sData, wData);
        gui.panel.revalidate();
    }
    public void selectAll() {
        try {
            fData = sql.select_fertilizer_expense();
            gData = sql.select_gas_expense();
            lData = sql.select_labour_expense();
            liData = sql.select_lighting_expense();
            maData = sql.select_machinery_expense();
            mData = sql.select_mortgaged_land();
            rData = sql.select_rented_land();
            sData = sql.select_slurry_expense();
            wData = sql.select_water_expense();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}