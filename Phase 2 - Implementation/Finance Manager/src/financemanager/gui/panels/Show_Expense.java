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
import net.java.dev.designgridlayout.DesignGridLayout;
import spreadsheet.*;

public class Show_Expense extends JPanel {
    // objects
    Panel_Elements gui = new Panel_Elements();
    Basics_JPanel basic = new Basics_JPanel();
    SQL_Handler sql = new SQL_Handler();
    Strings string = new Strings(); // to use reused information strings
    Spreadsheet ss = new Spreadsheet();

    // variables
    int width = 650;
    int height = 350;

    int count = 10;

    int type; // the combo box index value
    String[] e_type = string.expense_types(); // holds string array for types of expense to be placed in combo box
    int month; // the combo box index value
    String monthOut; // the selected month
    String[] str_months = string.showMonths(); // holds string array for months to be placed in combo box
    int year; // the combo box index value
    int yearOut; // the selected year
    int[] years = string.int_years(); // holds int array for years to be referenced and put into database
    String[] str_years = string.str_showYears(); // holds string array for years to be placed in combo box
    String sql_stat; // used to create sql statement to be sent
    String monthIn; // holds the returned value for checking
    int yearIn; // holds the returned value for checking

    String sqlStat = "";

    java.util.List<Basics_Data> bData;

    public Show_Expense() {
        gui.showExpensePanel = new JPanel();
        gui.panel = new JPanel();
        init();
    }
    public void init() {
        DesignGridLayout layout = new DesignGridLayout(gui.panel);
        showOptions(layout);
    }
    public JPanel showExpensePanel() {
        return gui.showExpensePanel;
    }
    public void showOptions(DesignGridLayout layout) {
        gui.lblExpenseType = new JLabel("Expense Type: ");
        gui.cmbExpenseType = new JComboBox(e_type);
        gui.cmbExpenseType.setBackground(Color.WHITE);
        gui.cmbExpenseType.setSelectedIndex(0);
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

        layout.row().grid(gui.lblExpenseType).add(gui.cmbExpenseType).
                     grid(gui.lblMonths).add(gui.cmbMonths).
                     grid(gui.lblYears).add(gui.cmbYears).
                     add(gui.btnApply);
        layout.emptyRow();
        layout.row().center().add(ss.getSpreadsheet(width, height, 10));

        gui.showExpensePanel.add(gui.panel);
    }
    public void apply() {
        type = gui.cmbExpenseType.getSelectedIndex();
        month = gui.cmbMonths.getSelectedIndex();
        year = gui.cmbYears.getSelectedIndex();
        monthOut = str_months[month];
        if(year > 0) {
            yearOut = years[year-1];
        }
        else if(year == 0) {
            yearOut = years[year];
        }
        showExpense();

        type = 0;
        ss.update(ss, type, bData);
        gui.panel.revalidate();
    }
    public void showExpense() {
        String sqlStat1 = "", sqlStat2 = "", sqlStat3 = "", sqlStat4 = "", sqlStat5 = "", sqlStat6 = "", sqlStat7 = "", sqlStat8 = "", sqlStat9 = "";
        if(type > 0 && month > 0 && year == 0) {
            sqlStat = chooseType();
            sqlStat += chooseMonth();
        }
        else if(type > 0 && month == 0 && year > 0) {
            sqlStat = chooseType();
            sqlStat += chooseYear();
        }
        else if(type > 0 && month > 0 && year > 0) {
            sqlStat = chooseType();
            sqlStat += chooseMonthYear();
        }
        else if(type > 0 && month == 0 && year == 0) {
            sqlStat = chooseType();
        }
        else if(type == 0 && month > 0 && year > 0) {
            for(int i=1; i<count; i++) {
                type = i;
                sqlStat = chooseType();
                sqlStat += chooseMonthYear();
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
        }
        else if(type == 0 && month > 0 && year == 0) {
            for(int i=1; i<count; i++) {
                type = i;
                sqlStat = chooseType();
                sqlStat += chooseMonth();
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
        }
        else if(type == 0 && month == 0 && year > 0) {
            for(int i=1; i<count; i++) {
                type = i;
                sqlStat = chooseType();
                sqlStat += chooseYear();
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
        }
        else if(type == 0 && month == 0 && year == 0) {
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
        }
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
    public String chooseMonth() {
        String sqlPiece = "";
        switch(type) {
            case 1:
                sqlPiece = "WHERE f_month = '" + monthOut + "'";
                break;
            case 2:
                sqlPiece = "WHERE g_month = '" + monthOut + "'";
                break;
            case 3:
                sqlPiece = "WHERE l_month = '" + monthOut + "'";
                break;
            case 4:
                sqlPiece = "WHERE li_month = '" + monthOut + "'";
                break;
            case 5:
                sqlPiece = "WHERE ma_month = '" + monthOut + "'";
                break;
            case 6:
                sqlPiece = "WHERE m_month = '" + monthOut + "'";
                break;
            case 7:
                sqlPiece = "WHERE r_month = '" + monthOut + "'";
                break;
            case 8:
                sqlPiece = "WHERE s_month = '" + monthOut + "'";
                break;
            case 9:
                sqlPiece = "WHERE w_month = '" + monthOut + "'";
                break;
            default:
                System.out.println("ERROR! chooseMonth");
                break;
        }
        return sqlPiece;
    }
    public String chooseYear() {
        String sqlPiece = "";
        switch(type) {
            case 1:
                sqlPiece = "WHERE f_year = " + yearOut;
                break;
            case 2:
                sqlPiece = "WHERE g_year = " + yearOut;
                break;
            case 3:
                sqlPiece = "WHERE l_year = " + yearOut;
                break;
            case 4:
                sqlPiece = "WHERE li_year = " + yearOut;
                break;
            case 5:
                sqlPiece = "WHERE ma_year = " + yearOut;
                break;
            case 6:
                sqlPiece = "WHERE m_year = " + yearOut;
                break;
            case 7:
                sqlPiece = "WHERE r_year = " + yearOut;
                break;
            case 8:
                sqlPiece = "WHERE s_year = " + yearOut;
                break;
            case 9:
                sqlPiece = "WHERE w_year = " + yearOut;
                break;
            default:
                System.out.println("ERROR! chooseYear");
                break;
        }
        return sqlPiece;
    }
    public String chooseMonthYear() {
        String sqlPiece = "";
        switch(type) {
            case 1:
                sqlPiece = "WHERE f_month = '" + monthOut + "' AND f_year = " + yearOut;
                break;
            case 2:
                sqlPiece = "WHERE g_month = '" + monthOut + "' AND g_year = " + yearOut;
                break;
            case 3:
                sqlPiece = "WHERE l_month = '" + monthOut + "' AND l_year = " + yearOut;
                break;
            case 4:
                sqlPiece = "WHERE li_month = '" + monthOut + "' AND li_year = " + yearOut;
                break;
            case 5:
                sqlPiece = "WHERE ma_month = '" + monthOut + "' AND ma_year = " + yearOut;
                break;
            case 6:
                sqlPiece = "WHERE m_month = '" + monthOut + "' AND m_year = " + yearOut;
                break;
            case 7:
                sqlPiece = "WHERE r_month = '" + monthOut + "' AND r_year = " + yearOut;
                break;
            case 8:
                sqlPiece = "WHERE s_month = '" + monthOut + "' AND s_year = " + yearOut;
                break;
            case 9:
                sqlPiece = "WHERE w_month = '" + monthOut + "' AND w_year = " + yearOut;
                break;
            default:
                System.out.println("ERROR! chooseMonthYear");
                break;
        }
        return sqlPiece;
    }
    public void refresh() {
        apply();
    }
}