package financemanager.gui.panels;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import javax.swing.*;
import net.java.dev.designgridlayout.*;

public class Panel_Elements {
     // JButtons
    public JButton btnApply; // preferences
    public JButton btnTemp; // basics

    // JCheckBox
    public JCheckBox cbTemp; // basics

    // JComboBox
    public JComboBox cmbDays; // create_expense
    public JComboBox cmbDays1; // create_expense
    public JComboBox cmbEvens; // preferences
    public JComboBox cmbExpenseType; // create_expense
    public JComboBox cmbHighlighted; // preferences
    public JComboBox cmbMonths; // create_expense
    public JComboBox cmbOdds; // preferences
    public JComboBox cmbType; // create_expense
    public JComboBox cmbYears; // create_expense

    // JLabel
    public JLabel lblDays; // create_expense
    public JLabel lblDays1; // create_expense
    public JLabel lblEvens; // preferences
    public JLabel lblExpenseAmount; // create_expense
    public JLabel lblExpenseType; // create_expense
    public JLabel lblHighlighted; // preferences
    public JLabel lblHours; // create_expense
    public JLabel lblLabourExpenses; // basics
    public JLabel lblLightingExpenses; // basics
    public JLabel lblMachineryExpenses; // basics
    public JLabel lblMonths; // create_expense
    public JLabel lblMortgagedLand; // basics
    public JLabel lblOdds; // preferences
    public JLabel lblRentedLand; // basics
    public JLabel lblTableLines; // preferences
    public JLabel lblType; // create_expense
    public JLabel lblWorker; // create_expense
    public JLabel lblYears; // create_expense

    // JPanel
    public JPanel basicsPanel; // basics
    public JPanel createExpensePanel; // create_expense
    public JPanel panel; // basics, preferences
    public JPanel preferencesPanel; // preferences
    public JPanel showExpensePanel; // show_expense

    // JTextField
    public JTextField txtExpenseAmount; // create_expense
    public JTextField txtHours; // create_expense
    public JTextField txtLabourExpenses; // basics
    public JTextField txtLightingExpenses; // basics
    public JTextField txtMachineryExpenses; // basics
    public JTextField txtMortgagedLand; // basics
    public JTextField txtRentedLand; // basics
    public JTextField txtWorker; // create_expense

    // RowGroup
    public RowGroup grpLabour; // create_expense
    public RowGroup grpMachinery; // create_expense
}