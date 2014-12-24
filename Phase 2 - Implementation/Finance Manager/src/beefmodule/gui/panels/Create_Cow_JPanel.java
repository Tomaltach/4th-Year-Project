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

public class Create_Cow_JPanel extends JPanel {
    // objects
    Panel_Elements gui = new Panel_Elements();
    SQL_Beef_Handler sql = new SQL_Beef_Handler();
    Strings string = new Strings(); // to use reused information strings
    Spreadsheet ss = new Spreadsheet();
    public static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);

    // variables
    int width = 650;
    int height = 350;

    String cowTag;
    String beefDate;
    Double beefPrice;

    java.util.List<CowTag_Data> ctData;
    java.util.List<Cow_Data> beefData;

    public Create_Cow_JPanel() {
        gui.createCowPanel = new JPanel();
        gui.panel = new JPanel();
        try {
            beefData = sql.select_cow_data();
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
    public JPanel createCowPanel() {
        return gui.createCowPanel;
    }
    public void showOptions(DesignGridLayout layout) {
        gui.lblCowTag = new JLabel("Cow Tag: ");
        gui.txtCowTag = new JTextField();
        gui.lblPrice = new JLabel("Price: ");
        gui.txtPrice = new JTextField();
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

        layout.row().grid(gui.lblCowTag).add(gui.txtCowTag).
                     grid(gui.lblPrice).add(gui.txtPrice);
        layout.row().grid(gui.lblDate).add(gui.txtDate).
                     grid().add(gui.btnCalendar).add(gui.btnApply);
        layout.row().center().add(ss.getCowSpreadsheet(width, 0, height, beefData));

        gui.createCowPanel.add(gui.panel);
    }
    public void apply() {
        boolean c;
        try {
            c = true;
            Double.parseDouble(gui.txtPrice.getText());
        }
        catch(NumberFormatException e) {
            c = false;
            JOptionPane.showMessageDialog(null, "ERROR! Must use numbers only!", "Error Massage", JOptionPane.ERROR_MESSAGE);
            gui.txtPrice.setText("");
            gui.txtPrice.requestFocus();
        }
        if(c == true) {
            cowTag = gui.txtCowTag.getText();
            beefDate = gui.txtDate.getText();
            beefPrice = Double.parseDouble(gui.txtPrice.getText());
            if(cowTag.equals("")) {
                JOptionPane.showMessageDialog(null, "Must enter Cows Tag!");
            }
            else if(beefDate.equals("")) {
                JOptionPane.showMessageDialog(null, "Must enter a Date!");
            }
            else {
                tagChecker();
            }
        }
        displayData();
        gui.txtCowTag.setText("");
        gui.txtPrice.setText("");
        ss.updateCow(ss, beefData);
        gui.panel.revalidate();
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
    public void tagChecker() {
        int i = 0;
        getCTData();
        String[] result = new String[ctData.size()];
        boolean check = true;
        ListIterator<CowTag_Data> ctdata_list = ctData.listIterator();
        while(ctdata_list.hasNext()) {
            CowTag_Data ctd = ctdata_list.next();
            result[i] = ctd.getCowTag();
            System.out.println(i);
            if(cowTag.equals(result[i])) {
                check = false;
                break;
            }
            i++;
        }
        if(check == true) {
            createExpense();
        }
        else {
            JOptionPane.showMessageDialog(null, "ERROR! Animal already exists!\nTry again.", "Error Massage", JOptionPane.ERROR_MESSAGE);
            gui.txtCowTag.requestFocus();
        }
    }
    public void createExpense() {
        try {
            sql.create_cow(cowTag, beefPrice, beefDate);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void displayData() {
        try {
            beefData = sql.select_cow_data();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void getCTData() {
        try {
            ctData = sql.select_cowtag_data();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void refresh() {
        displayData();
        ss.updateCow(ss, beefData);
        gui.panel.revalidate();
    }
}