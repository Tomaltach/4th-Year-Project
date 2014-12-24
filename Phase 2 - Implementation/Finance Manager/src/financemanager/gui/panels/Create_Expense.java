package financemanager.gui.panels;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import financemanager.*;
import financemanager.datahandler.*;
import financemanager.sqlhandler.*;
import net.java.dev.designgridlayout.*;
import spreadsheet.*;

public class Create_Expense extends JPanel {
    // objects
    Panel_Elements gui = new Panel_Elements();
    SQL_Handler sql = new SQL_Handler();
    Strings string = new Strings(); // to use reused information strings
    Spreadsheet ss = new Spreadsheet();

    // variables
    int width = 650;
    int height = 350;
    int count = 10;

    String str_amount; // contain text field value
    Double amount; // contains value to be passed to sql handler
    int type; // the combo box index value
    String[] e_type = string.expense_types(); // holds string array for types of expense to be placed in combo box
    int month; // the combo box index value
    String monthOut; // the selected month
    String[] str_months = string.months(); // holds string array for months to be placed in combo box
    int year; // the combo box index value
    int yearOut; // the selected year
    int[] years = string.int_years(); // holds int array for years to be referenced and put into database
    String[] str_years = string.str_years(); // holds string array for years to be placed in combo box
    String sql_stat; // used to create sql statement to be sent
    String monthIn; // holds the returned value for checking
    int yearIn; // holds the returned value for checking

    String[] days = string.days(); // holds days to get full date
    int day;
    String dayOut;
    String worker; // worker name
    String str_hours;
    Double hours; // hours worker has worked

    String[] ma_types = string.ma_types();
    int ma_type;
    String ma_typeOut;

    String sqlStat = "";
    java.util.List<Basics_Data> bData;

    public Create_Expense() {
        gui.createExpensePanel = new JPanel();
        gui.panel = new JPanel();
        init();
    }
    public void init() {
        DesignGridLayout layout = new DesignGridLayout(gui.panel);
        showOptions(layout);
    }
    public JPanel createExpensePanel() {
        return gui.createExpensePanel;
    }
    public void showOptions(DesignGridLayout layout) {
        // labour extras
        gui.grpLabour = new RowGroup();
        gui.lblDays = new JLabel("Days: ");
        gui.lblHours = new JLabel("Hours: ");
        gui.lblWorker = new JLabel("Worker: ");
        gui.cmbDays = new JComboBox(days);
        gui.cmbDays.setBackground(Color.WHITE);
        gui.cmbDays.setSelectedIndex(0);
        gui.txtHours = new JTextField();
        gui.txtWorker = new JTextField();
        // machinery extras
        gui.grpMachinery = new RowGroup();
        gui.lblDays1 = new JLabel("Days: ");
        gui.lblType = new JLabel("Type: ");
        gui.cmbType = new JComboBox(ma_types);
        gui.cmbType.setBackground(Color.WHITE);
        gui.cmbType.setSelectedIndex(0);
        gui.cmbDays1 = new JComboBox(days);
        gui.cmbDays1.setBackground(Color.WHITE);
        gui.cmbDays1.setSelectedIndex(0);

        gui.lblExpenseAmount = new JLabel("Expense Amount: ");
        gui.txtExpenseAmount = new JTextField("", 10);
        gui.lblExpenseType = new JLabel("Expense Type: ");
        gui.cmbExpenseType = new JComboBox(e_type);
        gui.cmbExpenseType.setBackground(Color.WHITE);
        gui.cmbExpenseType.setSelectedIndex(0);
        gui.cmbExpenseType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(gui.cmbExpenseType.getSelectedIndex() == 3) {
                    gui.grpMachinery.hide();
                    gui.grpLabour.show();
                }
                else if(gui.cmbExpenseType.getSelectedIndex() == 5) {
                    gui.grpLabour.hide();
                    gui.grpMachinery.show();
                }
                else {
                    gui.grpLabour.hide();
                    gui.grpMachinery.hide();
                }
            }
        });
        gui.lblMonths = new JLabel("Month: ");
        gui.cmbMonths = new JComboBox(str_months);
        gui.cmbMonths.setBackground(Color.WHITE);
        gui.cmbMonths.setSelectedIndex(0);
        gui.lblYears = new JLabel("Year: ");
        gui.cmbYears = new JComboBox(str_years);
        gui.cmbYears.setBackground(Color.WHITE);
        gui.cmbYears.setSelectedIndex(0);
        gui.btnApply = new JButton("Apply");
        gui.btnApply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                apply();
            }
        });

        layout.row().grid(gui.lblExpenseAmount).add(gui.txtExpenseAmount);
        layout.row().grid(gui.lblExpenseType).add(gui.cmbExpenseType).
                     grid(gui.lblMonths).add(gui.cmbMonths).
                     grid(gui.lblYears).add(gui.cmbYears).
                     add(gui.btnApply);
        //layout.row().group(gui.grpLabour).grid(gui.lblWorker).add(gui.txtWorker).
        //                                  grid(gui.lblHours).add(gui.txtHours).
        //                                  grid(gui.lblDays).add(gui.cmbDays);
        //layout.row().group(gui.grpMachinery).grid().empty().
        //                                     grid(gui.lblType).add(gui.cmbType).
        //                                     grid(gui.lblDays1).add(gui.cmbDays1);
        layout.row().center().add(ss.getSpreadsheet(width, height, 10));

        gui.createExpensePanel.add(gui.panel);
    }
    public void apply() {
        boolean c;
        try {
            c = true;
            Integer.parseInt(gui.txtExpenseAmount.getText());
        }
        catch(NumberFormatException e) {
            c = false;
            JOptionPane.showMessageDialog(null, "ERROR! Must use numbers only!", "Error Massage", JOptionPane.ERROR_MESSAGE);
            gui.txtExpenseAmount.setText("");
        }
        if(c == true) {
            type = gui.cmbExpenseType.getSelectedIndex();
            month = gui.cmbMonths.getSelectedIndex();
            year = gui.cmbYears.getSelectedIndex();
            monthOut = str_months[month];
            yearOut = years[year];
            str_amount = gui.txtExpenseAmount.getText();
        
            if(type == 0) {
                JOptionPane.showMessageDialog(null, "Must choose an Expense Type!");
            }
            else if(str_amount.equals("")) {
                JOptionPane.showMessageDialog(null, "Must enter the Expense Amount!");
            }
/*            else if(gui.cmbExpenseType.getSelectedIndex() == 3) {
                worker = gui.txtWorker.getText();
                str_hours = gui.txtHours.getText();
                if(worker.equals("") || str_hours.equals("")) {
                    JOptionPane.showMessageDialog(null, "Must enter the required data!");
                }
                else {
                    hours = Double.parseDouble(str_hours);
                    day = gui.cmbDays.getSelectedIndex();
                    dayOut = days[day];
                }
            }
            else if(gui.cmbExpenseType.getSelectedIndex() == 5) {
                ma_type = gui.cmbDays1.getSelectedIndex();
                if(ma_type == 0) {
                    JOptionPane.showMessageDialog(null, "Must enter a date!");
                }
                else {
                    day = gui.cmbDays1.getSelectedIndex();
                    dayOut = days[day];
                }
            }
*/            else {
                // call to database to make get months and years
                amount = Double.parseDouble(str_amount);
                checkMonthYear();
                if(monthOut.equals(monthIn) && yearOut == yearIn) {
                    editExpense();
                }
                else {
                    createExpense();
                }
            }
            displayData();
            type = 0;
            ss.update(ss, type, bData);
            gui.panel.revalidate();
        }
    }
    public void checkMonthYear() {
        switch(type) {
            case 1: // fertilizer
                try {
                    sql_stat = "SELECT * FROM app.fertilizer_expenses "
                             + "WHERE f_month = '" + monthOut + "' AND f_year = " + yearOut;
                    sql.checkMonthYear(sql_stat, type);
                    yearIn = sql.getYear();
                    monthIn = sql.getMonth();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2: // gas
                try {
                    sql_stat = "SELECT * FROM app.gas_expenses "
                             + "WHERE g_month = '" + monthOut + "' AND g_year = " + yearOut;
                    sql.checkMonthYear(sql_stat, type);
                    yearIn = sql.getYear();
                    monthIn = sql.getMonth();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3: // labour
                try {
                    sql_stat = "SELECT * FROM app.labour_expenses "
                             + "WHERE l_month = '" + monthOut + "' AND l_year = " + yearOut;
                    sql.checkMonthYear(sql_stat, type);
                    yearIn = sql.getYear();
                    monthIn = sql.getMonth();                 
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 4: // lighting
                try {
                    sql_stat = "SELECT * FROM app.lighting_expenses "
                             + "WHERE li_month = '" + monthOut + "' AND li_year = " + yearOut;
                    sql.checkMonthYear(sql_stat, type);
                    yearIn = sql.getYear();
                    monthIn = sql.getMonth();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 5: // machinery
                try {
                    sql_stat = "SELECT * FROM app.machinery_expenses "
                             + "WHERE ma_month = '" + monthOut + "' AND ma_year = " + yearOut;
                    sql.checkMonthYear(sql_stat, type);
                    yearIn = sql.getYear();
                    monthIn = sql.getMonth();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 6: // mortgaged
                try {
                    sql_stat = "SELECT * FROM app.mortgaged_land "
                             + "WHERE m_month = '" + monthOut + "' AND m_year = " + yearOut;
                    sql.checkMonthYear(sql_stat, type);
                    yearIn = sql.getYear();
                    monthIn = sql.getMonth();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 7: // rented
                try {
                    sql_stat = "SELECT * FROM app.rented_land "
                             + "WHERE r_month = '" + monthOut + "' AND r_year = " + yearOut;
                    sql.checkMonthYear(sql_stat, type);
                    yearIn = sql.getYear();
                    monthIn = sql.getMonth();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 8: // slurry
                try {
                    sql_stat = "SELECT * FROM app.slurry_expenses "
                             + "WHERE s_month = '" + monthOut + "' AND s_year = " + yearOut;
                    sql.checkMonthYear(sql_stat, type);
                    yearIn = sql.getYear();
                    monthIn = sql.getMonth();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 9: // water
                try {
                    sql_stat = "SELECT * FROM app.water_expenses "
                             + "WHERE w_month = '" + monthOut + "' AND w_year = " + yearOut;
                    sql.checkMonthYear(sql_stat, type);
                    yearIn = sql.getYear();
                    monthIn = sql.getMonth();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("ERROR! Create_Expense.checkMonthYear() - Choices not valid!");
                break;
        }
    }
    public void editExpense() {
        switch(type) {
            case 1: // fertilizer
                try {
                    sql.edit_fertilizer_expense(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2: // gas
                try {
                    sql.edit_gas_expense(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3: // labour
                try {
                    sql.edit_labour_expense(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 4: // lighting
                try {
                    sql.edit_lighting_expense(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 5: // machinery
                try {
                    sql.edit_machinery_expense(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 6: // mortgaged
                try {
                    sql.edit_mortgaged_land(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 7: // rented
                try {
                    sql.edit_rented_land(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 8: // slurry
                try {
                    sql.edit_slurry_expense(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 9: // water
                try {
                    sql.edit_water_expense(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("ERROR! Create_Expense.editExpense() - Choices not valid!");
                break;
        }
    }
    public void createExpense() {
        switch(type) {
            case 1: // fertilizer
                try {
                    sql.create_fertilizer_expense(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2: // gas
                try {
                    sql.create_gas_expense(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3: // labour
                try {
                    sql.create_labour_expense(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 4: // lighting
                try {
                    sql.create_lighting_expense(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 5: // machinery
                try {
                    sql.create_machinery_expense(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 6: // mortgaged
                try {
                    sql.create_mortgaged_land(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 7: // rented
                try {
                    sql.create_rented_land(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 8: // slurry
                try {
                    sql.create_slurry_expense(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 9: // water
                try {
                    sql.create_water_expense(amount, yearOut, monthOut);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("ERROR! Create_Expense.createExpense() - Choices not valid!");
                break;
        }
    }
    public void displayData() {
        String sqlStat1 = "", sqlStat2 = "", sqlStat3 = "", sqlStat4 = "", sqlStat5 = "", sqlStat6 = "", sqlStat7 = "", sqlStat8 = "", sqlStat9 = "";
        for(int i=1; i<count; i++) {
            type = i;
            sqlStat = chooseType();
            switch(type){
                case 1:
                    sqlStat1 = sqlStat;
                    break;
                case 2:
                    sqlStat2 = sqlStat;
                    break;
                case 3:
                    sqlStat3 = sqlStat;
                    break;
                case 4:
                    sqlStat4 = sqlStat;
                    break;
                case 5:
                    sqlStat5 = sqlStat;
                    break;
                case 6:
                    sqlStat6 = sqlStat;
                    break;
                case 7:
                    sqlStat7 = sqlStat;
                    break;
                case 8:
                    sqlStat8 = sqlStat;
                    break;
                case 9:
                    sqlStat9 = sqlStat;
                    break;
            }
        }
        type = 0;
        try {
            if(type > 0) {
                bData = sql.select_basics(sqlStat, type);
            }
            else if(type == 0) {
                bData = sql.select_basics(sqlStat1, sqlStat2, sqlStat3, sqlStat4, sqlStat5, sqlStat6, sqlStat7, sqlStat8, sqlStat9);
            }
        }
        catch(Exception e) {
            e.printStackTrace();        
        }
    }
    public String chooseType() {
        String sqlPiece = "";
        switch(type) {
            case 1:
                sqlPiece = "SELECT * FROM app.fertilizer_expenses ";
                break;
            case 2:
                sqlPiece = "SELECT * FROM app.gas_expenses ";
                break;
            case 3:
                sqlPiece = "SELECT * FROM app.labour_expenses ";
                break;
            case 4:
                sqlPiece = "SELECT * FROM app.lighting_expenses ";
                break;
            case 5:
                sqlPiece = "SELECT * FROM app.machinery_expenses ";
                break;
            case 6:
                sqlPiece = "SELECT * FROM app.mortgaged_land ";
                break;
            case 7:
                sqlPiece = "SELECT * FROM app.rented_land ";
                break;
            case 8:
                sqlPiece = "SELECT * FROM app.slurry_expenses ";
                break;
            case 9:
                sqlPiece = "SELECT * FROM app.water_expenses ";
                break;
            default:
                System.out.println("ERROR! chooseType");
                break;
        }
        return sqlPiece;
    }    
    public void refresh() {
        displayData();
        ss.update(ss, type, bData);
        gui.panel.revalidate();
    }
}