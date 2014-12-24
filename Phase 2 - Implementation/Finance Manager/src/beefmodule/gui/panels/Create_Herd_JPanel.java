package beefmodule.gui.panels;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.awt.event.*;
import java.beans.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

import beefmodule.datahandler.*;
import beefmodule.sqlhandler.*;
import financemanager.*;
import net.java.dev.designgridlayout.*;
import net.sourceforge.jcalendarbutton.*;
import spreadsheet.*;

public class Create_Herd_JPanel extends JPanel {
    // objects
    Panel_Elements gui = new Panel_Elements();
    SQL_Beef_Handler sql = new SQL_Beef_Handler();
    Strings string = new Strings(); // to use reused information strings
    Spreadsheet ss = new Spreadsheet();
    public static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);

    // variables
    int width = 650;
    int height = 350;

    int herdId;
    String herdDate;

    List<Herd_Data> herdData;

    public Create_Herd_JPanel() {
        gui.createHerdPanel = new JPanel();
        gui.panel = new JPanel();
        try {
            herdData = sql.select_herd_data();
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
    public JPanel createHerdPanel() {
        return gui.createHerdPanel;
    }
    public void showOptions(DesignGridLayout layout) {
        gui.lblHerdId = new JLabel("Herd Id: ");
        gui.txtHerdId = new JTextField();
        gui.lblDate = new JLabel("Date: ");
        gui.txtDate = new JTextField();
        gui.txtDate.setEditable(false);
        //gui.txtDate.setBackground(Color.WHITE);
        gui.txtDate.addFocusListener(new FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateFocusLost(evt);
            }
        });
        gui.btnCalendar = new JCalendarButton();
        gui.btnCalendar.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateOnlyPopupChanged(evt);
            }
        });
        gui.btnApply = new JButton("Apply");
        gui.btnApply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                apply();
            }
        });

        layout.row().grid(gui.lblHerdId).add(gui.txtHerdId).
                     grid(gui.lblDate).add(gui.txtDate).
                     grid().add(gui.btnCalendar).add(gui.btnApply);
        layout.row().center().add(ss.getHerdSpreadsheet(width, 0, height, herdData));

        gui.createHerdPanel.add(gui.panel);
    }
    public void apply() {
        boolean c;
        try {
            c = true;
            Integer.parseInt(gui.txtHerdId.getText());
        }
        catch(NumberFormatException e) {
            c = false;
            JOptionPane.showMessageDialog(null, "ERROR! Must use numbers only!", "Error Massage", JOptionPane.ERROR_MESSAGE);
            gui.txtHerdId.setText("");
            gui.txtHerdId.requestFocus();
        }
        if(c == true) {
            herdId = Integer.parseInt(gui.txtHerdId.getText());
            if(herdId == 0) {
                JOptionPane.showMessageDialog(null, "ERROR! Cannot edit default herd!", "Error Massage", JOptionPane.ERROR_MESSAGE);
            }
            else {
                herdDate = gui.txtDate.getText();
                if(herdDate == null || herdDate.equals("")) {
                    JOptionPane.showMessageDialog(null, "ERROR! Must enter a date!", "Error Massage", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    herdChecker();
                }
            }
            displayData();
            gui.txtHerdId.setText("");
            ss.updateHerd(ss, herdData);
            gui.panel.revalidate();
        }
    }
    private void dateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateFocusLost
        String date = gui.txtDate.getText();
        setDate(date);
    }//GEN-LAST:event_dateFocusLost
    private void dateOnlyPopupChanged(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateOnlyPopupChanged
        if (evt.getNewValue() instanceof Date)
            setDate((Date)evt.getNewValue());
    }//GEN-LAST:event_dateOnlyPopupChanged
    public void setDate(String dateString) {
        Date date = null;
        try {
            if((dateString != null) && (dateString.length() > 0)) {
                date = dateFormat.parse(dateString);
            }
        }
        catch(Exception e) {
            date = null;
        }
        this.setDate(date);
    }
    public void setDate(Date date){
        String dateString = "";
        if(date != null) {
            dateString = dateFormat.format(date);
        }
        gui.txtDate.setText(dateString);
        gui.btnCalendar.setTargetDate(date);
    }
    public void displayData() {
        try {
            herdData = sql.select_herd_data();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void createHerd() {
        try {
            sql.create_herd(herdId, herdDate);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void endHerd() {
        try {
            sql.edit_herd(herdId, herdDate);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void herdChecker() {
        int i = 0;
        getHData();
        int[] result = new int[herdData.size()];
        boolean check = true;
        boolean check2 = true;
        ListIterator<Herd_Data> hdata_list = herdData.listIterator();
        while(hdata_list.hasNext()) {
            Herd_Data hd = hdata_list.next();
            result[i] = hd.getHerd_id();
            if(herdId == result[i]) {
                if(hd.getHerd_termination_date() == null) {
                    check = false;
                    check2 = true;
                    break;
                }
                else {
                    check = false;
                    check2 = false;
                    break;
                }
            }
            i++;
        }
        if(check == true) {
            createHerd();
        }
        else {
            if(check2 == true) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to 'TERMINATE' herd '" + herdId + "'?");
                if(reply == JOptionPane.YES_OPTION) {
                    endHerd();
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "ERROR! Herd has already been terminated!", "Error Massage", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void getHData() {
        try {
            herdData = sql.select_herd_data();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void refresh() {
        displayData();
        ss.updateHerd(ss, herdData);
        gui.panel.revalidate();
    }
}