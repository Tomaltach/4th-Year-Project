package beefmodule.gui.panels;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import javax.swing.*;

import net.sourceforge.jcalendarbutton.*;

public class Panel_Elements {
    // JButton
    public JButton btnApply;
    public JButton btnRefresh;

    // JCalendarButton
    public JCalendarButton btnCalendar; // medical_jpanel

    // JComboBox
    public JComboBox cmbMonth; // create_feed_expense
    public JComboBox cmbType; // create_feed_expense
    public JComboBox cmbYear; // create_feed_expense

    // JLabel
    public JLabel lblCowTag; // medical_jpanel
    public JLabel lblDate; // medical_jpanel
    public JLabel lblHerdId; // create_herd_jpanel
    public JLabel lblInfo; // medical_jpanel
    public JLabel lblMonth; // create_feed_expense
    public JLabel lblPrice; // medical_jpanel
    public JLabel lblType; // create_feed_expense
    public JLabel lblYear; // create_feed_expense

    // JPanel
    public JPanel createCowPanel; // create_cow_jpanel
    public JPanel createHerdPanel; // create_herd_jpanel
    public JPanel createFeedExpensePanel; // create_feed_expense_jpanel
    public JPanel manageHerdsPanel; // manage_herds_jpanel
    public JPanel medicalExpensePanel; // medical_jpanel
    public JPanel overviewPanel; // medical_overview_jpanel
    public JPanel panel; // medical_jpanel, vet_jpanel
    public JPanel sellCowPanel; // create_cow_jpanel
    public JPanel showHerdPanel; // show_herd_jpanel
    public JPanel vetVisitPanel; // vet_jpanel

    // JTextField
    public JTextField txtCowTag; // medical_jpanel
    public JTextField txtDate; // medical_jpanel
    public JTextField txtHerdId; // create_herd_jpanel
    public JTextField txtInfo; // medical_jpanel
    public JTextField txtPrice; // medical_jpanel
}