package beefmodule.gui.panels;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import autocomplete.*;
import beefmodule.datahandler.*;
import beefmodule.sqlhandler.*;
import financemanager.*;
import net.java.dev.designgridlayout.*;
import net.sourceforge.jcalendarbutton.*;
import spreadsheet.*;

public class Vet_JPanel extends JPanel {
    // objects
    NameService nameService = new NameService(); // private class
    Panel_Elements gui = new Panel_Elements();
    SQL_Beef_Handler sql = new SQL_Beef_Handler();
    Strings string = new Strings(); // to use reused information strings
    Spreadsheet ss = new Spreadsheet();
    public static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);

    // variables
    int width = 650;
    int height = 350;

    String cowTag;
    String vvInfo;
    String vvDate;
    Double vvPrice;

    java.util.List<CowTag_Data> ctData;
    java.util.List<Vet_Visit_Data> vvData;

    public Vet_JPanel() {
        gui.vetVisitPanel = new JPanel();
        gui.panel = new JPanel();
        init();
    }
    public void init() {
        DesignGridLayout layout = new DesignGridLayout(gui.panel);
        showOptions(layout);
    }
    public JPanel vetVisitPanel() {
        return gui.vetVisitPanel;
    }
    public void showOptions(DesignGridLayout layout) {
        gui.lblCowTag = new JLabel("Cow Tag: ");
        gui.txtCowTag = new JTextField();
        gui.lblPrice = new JLabel("Expense: ");
        gui.txtPrice = new JTextField();
        gui.lblInfo = new JLabel("Info: ");
        gui.txtInfo = new JTextField();
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

        Document autoCompleteDocument = new AutoCompleteDocument(nameService, gui.txtCowTag);
        gui.txtCowTag.setDocument(autoCompleteDocument);

        layout.row().grid(gui.lblCowTag).add(gui.txtCowTag).
                     grid(gui.lblInfo).add(gui.txtInfo);
        layout.row().grid(gui.lblPrice).add(gui.txtPrice).
                     grid(gui.lblDate).add(gui.txtDate).
                     grid().add(gui.btnCalendar).add(gui.btnApply);
        layout.row().center().add(ss.getVetSpreadsheet(width, 1, height));

        gui.vetVisitPanel.add(gui.panel);
    }
    public void refresh() {
        displayData();
        ss.updateVetVisits(ss, vvData);
        gui.panel.revalidate();
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
            vvInfo = gui.txtInfo.getText();
            vvDate = gui.txtDate.getText();
            vvPrice = Double.parseDouble(gui.txtPrice.getText());
            if(cowTag.equals("")) {
                JOptionPane.showMessageDialog(null, "Must enter Cows Tag!");
            }
            else if(vvInfo.equals("")) {
                JOptionPane.showMessageDialog(null, "Must enter Info about expense!");
            }
            else if(vvDate.equals("")) {
                JOptionPane.showMessageDialog(null, "Must enter a Date!");
            }
            else {
                tagChecker();
            }
        }
        displayData();
        nameService.getAutoCompleteData();
        nameService.setNameService();
        gui.txtCowTag.setText("");
        gui.txtInfo.setText("");
        gui.txtPrice.setText("");
        ss.updateVetVisits(ss, vvData);
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
        String[] result = new String[ctData.size()];
        boolean check = false;
        ListIterator<CowTag_Data> ctdata_list = ctData.listIterator();
        while(ctdata_list.hasNext()) {
            CowTag_Data ctd = ctdata_list.next();
            result[i] = ctd.getCowTag();
            System.out.println(i);
            if(cowTag.equals(result[i])) {
                check = true;
                break;
            }
            i++;
        }
        if(check == true) {
            createExpense();
        }
        else {
            JOptionPane.showMessageDialog(null, "ERROR! Animal does NOT exist!\nTry again or add animal first.", "Error Massage", JOptionPane.ERROR_MESSAGE);
            gui.txtCowTag.requestFocus();
        }
    }
    public void createExpense() {
        try {
            sql.create_vet_visits(cowTag, vvInfo, vvDate, vvPrice);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void displayData() {
        try {
            vvData = sql.select_vet_visit_data();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private class NameService implements CompletionService<String> {
        private java.util.List<String> data;
        SQL_Beef_Handler sql3 = new SQL_Beef_Handler();

        public NameService() {
            getAutoCompleteData();
            if(ctData != null){
                setNameService();
            }
        }
        public String toString() {
            StringBuilder b = new StringBuilder();
            for(String o : data) {
                b.append(o).append("\n");
            }
            return b.toString();
        }
        public String autoComplete(String startsWith) {
            String hit = null;
            for(String o : data) {
                if(o.startsWith(startsWith)) {
                    if(hit == null) {
                        hit = o;
                    }
                    else {
                        hit = null;
                        break;
                    }
                }
            }
            return hit;
        }
        public void setNameService() {
            int i = 0;
            String[] result = new String[ctData.size()];

            getAutoCompleteData();
            ListIterator<CowTag_Data> ctdata_list = ctData.listIterator();
            while(ctdata_list.hasNext()) {
                CowTag_Data ctd = ctdata_list.next();
                result[i] = ctd.getCowTag();
                i++;
            }
            data = Arrays.asList(result);
        }
        public void getAutoCompleteData() {
            try {
                ctData = sql3.select_cowtag_data();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}