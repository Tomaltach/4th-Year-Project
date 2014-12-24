package beefmodule.gui.panels;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import beefmodule.datahandler.*;
import beefmodule.sqlhandler.*;
import financemanager.*;
import java.util.ListIterator;
import net.java.dev.designgridlayout.*;
import spreadsheet.*;

public class Create_Feed_Expense_JPanel extends JPanel {
    // objects
    Panel_Elements gui = new Panel_Elements();
    SQL_Beef_Handler sql = new SQL_Beef_Handler();
    Strings string = new Strings(); // to use reused information strings
    Spreadsheet ss = new Spreadsheet();
    
    // variables
    final Double BLANK = 0.0;
    int width = 650;
    int height = 350;
    
    int herd_id;
    Double hay = 0.0;
    Double nuts = 0.0;
    Double silage = 0.0;
    Double expense = 0.0;
    int type;
    String typeOut;

    int herdIdIn;
    
    String[] feed_types = string.feed_type(); // holds types of feed
    int month; // the combo box index value
    String monthOut; // the selected month
    String[] str_months = string.months(); // holds string array for months to be placed in combo box
    int year; // the combo box index value
    String yearOut; // the selected year
    String[] str_years = string.str_years(); // holds string array for years to be placed in combo box
    String sql_stat; // used to create sql statement to be sent
    String monthIn; // holds the returned value for checking
    String yearIn; // holds the returned value for checking
    
    java.util.List<Feed_Data> feedData;
    java.util.List<Herd_Data> herdData;

    public Create_Feed_Expense_JPanel() {
        gui.createFeedExpensePanel = new JPanel();
        gui.panel = new JPanel();

        displayData();

        init();
    }
    public void init() {
        DesignGridLayout layout = new DesignGridLayout(gui.panel);
        showOptions(layout);
    }
    public JPanel createFeedExpensePanel() {
        return gui.createFeedExpensePanel;
    }
    public void showOptions(DesignGridLayout layout) {
        gui.lblHerdId = new JLabel("Herd Id: ");
        gui.txtHerdId = new JTextField();
        gui.lblPrice = new JLabel("Price: ");
        gui.txtPrice = new JTextField();
        gui.lblType = new JLabel("Feed Type: ");
        gui.cmbType = new JComboBox(feed_types);
        gui.cmbType.setBackground(Color.WHITE);
        gui.cmbType.setSelectedIndex(0);
        gui.lblMonth = new JLabel("Month: ");
        gui.cmbMonth = new JComboBox(str_months);
        gui.cmbMonth.setBackground(Color.WHITE);
        gui.cmbMonth.setSelectedIndex(0);
        gui.lblYear = new JLabel("Year: ");
        gui.cmbYear = new JComboBox(str_years);
        gui.cmbYear.setBackground(Color.WHITE);
        gui.cmbYear.setSelectedIndex(0);
        gui.btnApply = new JButton("Apply");
        gui.btnApply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                apply();
            }
        });

        layout.row().grid(gui.lblHerdId).add(gui.txtHerdId).
                     grid(gui.lblPrice).add(gui.txtPrice).
                     grid(gui.lblType).add(gui.cmbType);
        layout.row().grid(gui.lblMonth).add(gui.cmbMonth).
                     grid(gui.lblYear).add(gui.cmbYear).
                     grid().add(gui.btnApply);
        layout.row().center().add(ss.getFeedSpreadsheet(width, 1, height, feedData));

        gui.createFeedExpensePanel.add(gui.panel);
    }
    public void apply() {
        boolean c;
        try {
            c = true;
            Double.parseDouble(gui.txtPrice.getText());
            Integer.parseInt(gui.txtHerdId.getText());
        }
        catch(NumberFormatException e) {
            c = false;
            JOptionPane.showMessageDialog(null, "ERROR! Must use numbers only!", "Error Massage", JOptionPane.ERROR_MESSAGE);
            gui.txtPrice.setText("");
            gui.txtPrice.requestFocus();
        }

        if(c == true) {
            herd_id = Integer.parseInt(gui.txtHerdId.getText());
            expense = Double.parseDouble(gui.txtPrice.getText());
            type = gui.cmbType.getSelectedIndex();
            month = gui.cmbMonth.getSelectedIndex();
            year = gui.cmbYear.getSelectedIndex();
            typeOut = feed_types[type];
            monthOut = str_months[month];
            yearOut = str_years[year];

            if(type == 0) {
                JOptionPane.showMessageDialog(null, "Must choose an Feed Type!");
            }
            else if(gui.txtPrice.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Must enter the Expense Amount!");
            }
            else {
                boolean check = checkHerdId();
                if(check == true) {
                    checkMonthYear();
                    if(monthOut.equals(monthIn) && yearOut.equals(yearIn) && herdIdIn == herd_id) {
                        findRow();
                        editExpense();
                    }
                    else {
                        createExpense();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Herd does not exist or has been terminated!");
                }
            }
        }
        displayData();
        gui.txtHerdId.setText("");
        gui.txtPrice.setText("");
        ss.updateFeedExpenses(ss, feedData);
        gui.panel.revalidate();
    }
    public boolean checkHerdId() {
        boolean check = false;
        try {
            herdData = sql.select_herd_data_active();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        ListIterator<Herd_Data> herddata_list = herdData.listIterator();
        while(herddata_list.hasNext()) {
            Herd_Data herd = herddata_list.next();
            if(herd.getHerd_id() == herd_id) {
                check = true;
                return check;
            }
        }
        return check;
    }
    public void checkMonthYear() {
        try {
            sql_stat = "SELECT * FROM app.feed_expenses "
                     + "WHERE feed_month = '" + monthOut + "' "
                     + "AND feed_year = '" + yearOut + "' "
                     + "AND herd_id = " + herd_id;
            sql.checkMonthYear(sql_stat);
            yearIn = sql.getYear();
            monthIn = sql.getMonth();
            herdIdIn = sql.getHerdId();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void displayData() {
        try {
            feedData = sql.select_feed_data();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void createExpense() {
        String script;
        switch(type) {
            case 1: // hay
                try {
                    script = "INSERT INTO app.feed_expenses(herd_id, feed_hay_expense, feed_nuts_expense, feed_silage_expense, feed_month, feed_year) "
                           + "VALUES(" + herd_id + ", " + expense + ", " + BLANK + ", " + BLANK + ", '" + monthOut + "', '" + yearOut + "')";
                    sql.create_feed_expenses(script);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2: // nuts
                try {
                    script = "INSERT INTO app.feed_expenses(herd_id, feed_hay_expense, feed_nuts_expense, feed_silage_expense, feed_month, feed_year) "
                           + "VALUES(" + herd_id + ", " + BLANK + ", " + expense + ", " + BLANK + ", '" + monthOut + "', '" + yearOut + "')";
                    sql.create_feed_expenses(script);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3: // silage
                try {
                    script = "INSERT INTO app.feed_expenses(herd_id, feed_hay_expense, feed_nuts_expense, feed_silage_expense, feed_month, feed_year) "
                           + "VALUES(" + herd_id + ", " + BLANK + ", " + BLANK + ", " + expense + ", '" + monthOut + "', '" + yearOut + "')";
                    sql.create_feed_expenses(script);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("ERROR! Create_Feed_Expense.editExpense() - Choices not valid!");
                break;
        }
    }
    public void editExpense() {
        String script;
        switch(type) {
            case 1: // hay
                try {
                    script = "UPDATE app.feed_expenses "
                           + "SET feed_hay_expense = feed_hay_expense + " + expense + " "
                           + "WHERE feed_month = '" + monthOut + "' "
                           + "AND feed_year = '" + yearOut + "' "
                           + "AND herd_id = " + herd_id;
                    sql.edit_feed_expenses(script);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2: // nuts
                try {
                    script = "UPDATE app.feed_expenses "
                           + "SET feed_nuts_expense = feed_nuts_expense + " + expense + " "
                           + "WHERE feed_month = '" + monthOut + "' "
                           + "AND feed_year = '" + yearOut + "' "
                           + "AND herd_id = " + herd_id;
                    sql.edit_feed_expenses(script);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3: // silage
                try {
                    script = "UPDATE app.feed_expenses "
                           + "SET feed_silage_expense = feed_silage_expense + " + expense + " "
                           + "WHERE feed_month = '" + monthOut + "' "
                           + "AND feed_year = '" + yearOut + "' "
                           + "AND herd_id = " + herd_id;
                    sql.edit_feed_expenses(script);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("ERROR! Create_Feed_Expense.editExpense() - Choices not valid!");
                break;
        }
    }
    public void findRow() {
        ListIterator<Feed_Data> feeddata_list = feedData.listIterator();
        
        while(feeddata_list.hasNext()) {
            Feed_Data feed = feeddata_list.next();
            if(monthOut.equals(monthIn) && yearOut.equals(yearIn) && herdIdIn == herd_id) {
                hay = feed.getF_hay_expense();
                nuts = feed.getF_nuts_expense();
                silage = feed.getF_silage_expense();
            }
	}
    }
    public void refresh() {
        displayData();
        ss.updateFeedExpenses(ss, feedData);
        gui.panel.revalidate();
    }
}