package financemanager.gui;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import beefmodule.gui.*;
import beefmodule.gui.panels.*;
import financemanager.*;
import financemanager.gui.panels.*;
import financemanager.sqlhandler.*;
import report.gui.panels.*;
import report.pdfs.*;

public class GUI extends JFrame {
    // objects
    GUI_Elements gui = new GUI_Elements(); // to use gui elements
    GUI_Menu menuPanel = new GUI_Menu(); // to use the menu options
    Welcome_JPanel welcomePanel = new Welcome_JPanel(); // to show welcome panel at start up
    Basics_JPanel basicsPanel = new Basics_JPanel(); // to show basics panel
    Beef_JPanel beefPanel = new Beef_JPanel(); // to show beef panel
    SQL_Handler sql = new SQL_Handler(); // to use sql handler methods
    Strings string = new Strings(); // to use reused information strings
    Preferences_JPanel preferencesPanel = new Preferences_JPanel(); // to select prefered preferences from defined choices

    Create_Expense createExpensePanel = new Create_Expense(); // to create expense panel
    Show_Expense showExpensePanel = new Show_Expense(); // to show expense panel

    Create_Cow_JPanel createCowPanel = new Create_Cow_JPanel(); // to create and show create cow panel
    Create_Herd_JPanel createHerdPanel = new Create_Herd_JPanel(); // to create and show create herd panel
    Create_Feed_Expense_JPanel createFeedExpensePanel = new Create_Feed_Expense_JPanel(); // to create and show feed expenses
    Show_Herd_JPanel showHerdPanel = new Show_Herd_JPanel(); // to create and show herd panel
    Manage_Herds_JPanel manageHerdPanel = new Manage_Herds_JPanel(); // to create and show manage herd panel
    Medical_JPanel medicalExpensePanel = new Medical_JPanel(); // to create and show medical expenses
    Sell_Cow_JPanel sellCowPanel = new Sell_Cow_JPanel(); // to sell and show create cow panel
    Vet_JPanel vetVisitPanel = new Vet_JPanel(); // to create and show vet visits
    Medical_Overview_JPanel medOverviewPanel = new Medical_Overview_JPanel(); // to create an overview medical panel

    // Reports
    Pie_Chart_Report_JPanel pieChartReportPanel = new Pie_Chart_Report_JPanel();
    Bar_Chart_Report_JPanel barChartReportPanel = new Bar_Chart_Report_JPanel();
    PDF_Report_JPanel pdfReportPanel = new PDF_Report_JPanel();

    // local variables
    int viewPanel = 0; // for switch case to choose panel in init()

    // cards for the module panel
    private static final String BASICS_MODULE = "Basics"; // basic
    private static final String BEEF_MODULE = "Beef"; // beef
    private static final String CREATE_HERD = "Create_Herd"; // beef
    private static final String SHOW_HERD = "Show_Herd"; // beef
    private static final String MANAGE_HERD = "Manage_Herd"; // beef
    private static final String CREATE_COW = "Create_Cow"; // beef
    private static final String CREATE_EXPENSE = "Create_Expense"; // basic
    private static final String CREATE_FEED_EXPENSE = "Create_Feed_Expense"; // beef
    private static final String DAIRY_MODULE = "Dairy";
    private static final String MEDICAL_MODULE = "Medical"; // beef
    private static final String MEDICAL_OVERVIEW = "Medical_Overview"; // beef
    private static final String PREFERENCES_MODULE = "Preferences"; // basic
    private static final String SELL_COW = "Sell_Cow"; // beef
    private static final String SHOW_EXPENSE = "Show_Expense"; // basic
    private static final String TILAGE_MODULE = "Tilage";
    private static final String VET_VISIT_MODULE = "Vet_Visit"; // beef
    private static final String WELCOME_PAGE = "Welcome"; // basic
    private static final String PIE_CHART_REPORT = "Pie_Chart_Report"; // report
    private static final String BAR_CHART_REPORT = "Bar_Chart_Report"; // report
    private static final String PDF_REPORT = "PDF_Report"; // report

    // INNER CLASS VARIABLES
    private static boolean DEBUG = false;
    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    // END OF INNER CLASS VARIABLES

    public GUI() {
        super("Farm Finance Manager");
        init();
    }
    public void init() {
        menubar(); // create menubar
        optionPanel(); // create optionPanel
        statusPanel(0); // create statusPanel
        modulePanel(); // create modulePanel
        pack();
        setVisible(true);
    }
    public JPanel cardHolderPanel() {
        gui.modulePanel = new JPanel(new CardLayout());
        gui.modulePanel.setBorder(BorderFactory.createTitledBorder("Module Panel"));
        gui.modulePanel.add(welcomePanel.welcomePanel(), WELCOME_PAGE);
        gui.modulePanel.add(basicsPanel.basicsPanel(), BASICS_MODULE);
        gui.modulePanel.add(beefPanel.beefPanel(), BEEF_MODULE);
        gui.modulePanel.add(createHerdPanel.createHerdPanel(), CREATE_HERD);
        gui.modulePanel.add(showHerdPanel.showHerdPanel(), SHOW_HERD);
        gui.modulePanel.add(manageHerdPanel.manageHerdPanel(), MANAGE_HERD);
        gui.modulePanel.add(createCowPanel.createCowPanel(), CREATE_COW);
        gui.modulePanel.add(createExpensePanel.createExpensePanel(), CREATE_EXPENSE);
        gui.modulePanel.add(createFeedExpensePanel.createFeedExpensePanel(), CREATE_FEED_EXPENSE);
        //gui.modulePanel.add(dairyPanel.dairyPanel(), DAIRY_MODULE);
        gui.modulePanel.add(medicalExpensePanel.medicalExpensePanel(), MEDICAL_MODULE);
        gui.modulePanel.add(medOverviewPanel.overviewPanel(), MEDICAL_OVERVIEW);
        gui.modulePanel.add(sellCowPanel.sellCowPanel(), SELL_COW);
        gui.modulePanel.add(showExpensePanel.showExpensePanel(), SHOW_EXPENSE);
        //gui.modulePanel.add(tilagePanel.tilagePanel(), TILAGE_MODULE);
        gui.modulePanel.add(preferencesPanel.preferencesPanel(), PREFERENCES_MODULE);
        gui.modulePanel.add(vetVisitPanel.vetVisitPanel(), VET_VISIT_MODULE);
        gui.modulePanel.add(pieChartReportPanel.pieChartReportPanel(), PIE_CHART_REPORT);
        gui.modulePanel.add(barChartReportPanel.barChartReportPanel(), BAR_CHART_REPORT);
        gui.modulePanel.add(pdfReportPanel.pdfReportPanel(), PDF_REPORT);

        return gui.modulePanel;
    }
    public void about() {
        JOptionPane.showMessageDialog(this, string.about());
    }
    public void createHerd() {
        createHerdPanel.refresh();
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, CREATE_HERD);
        statusPanel(13);
    }
    public void showHerd() {
        showHerdPanel.refresh();
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, SHOW_HERD);
        statusPanel(14);
    }
    public void manageHerd() {
        manageHerdPanel.refresh();
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, MANAGE_HERD);
        statusPanel(15);
    }
    public void checkForUpdates() {
        try {
            UpdateChecker updater = new UpdateChecker();
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void checkRSSFeed() {
        try {
            RSSFeedChecker rssfeed = new RSSFeedChecker();
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void createCow() {
        createCowPanel.refresh();
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, CREATE_COW);
        statusPanel(11);
    }
    public void sellCow() {
        sellCowPanel.refresh();
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, SELL_COW);
        statusPanel(12);
    }
    public void createExpense() {
        createExpensePanel.refresh();
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, CREATE_EXPENSE);
        statusPanel(5);
    }
    public void createFeedExpense() {
        createFeedExpensePanel.refresh();
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, CREATE_FEED_EXPENSE);
        statusPanel(10);
    }
    public void createMedicalExpense() {
        medicalExpensePanel.refresh();
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, MEDICAL_MODULE);
        statusPanel(8);
    }
    public void createVetVisit() {
        vetVisitPanel.refresh();
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, VET_VISIT_MODULE);
        statusPanel(9);
    }
    public void showMedOverview() {
        medOverviewPanel.refresh();
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, MEDICAL_OVERVIEW);
        statusPanel(16);
    }
    public void barChart(boolean show) {
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, BAR_CHART_REPORT);
        statusPanel(18);
    }
    public void pieChart(boolean show) {
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, PIE_CHART_REPORT);
        statusPanel(17);
    }
    public void pdfReport() {
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, PDF_REPORT);
        statusPanel(19);
    }
    public void menubar() {
        // File attributes
        gui.barChart = new JMenuItem("Bar Chart Report");
        gui.barChart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                barChart(true);
            }
        });
        gui.close = new JMenuItem("Close");
        gui.close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        gui.close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        gui.open = new JMenuItem("Open");
        gui.open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        gui.open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                open();
            }
        });
        gui.pieChart = new JMenuItem("Pie Chart Report");
        gui.pieChart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                pieChart(true);
            }
        });gui.save = new JMenuItem("Save");
        gui.pdfReport = new JMenuItem("PDF Report");
        gui.pieChart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                pdfReport();
            }
        });gui.save = new JMenuItem("Save");
        gui.save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        gui.save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                save();
            }
        });
        gui.saveAndClose = new JMenuItem("Save & Close");
        gui.saveAndClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        gui.saveAndClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                save();
                System.exit(0);
            }
        });
        gui.newReport = new JMenu("New");
        gui.newReport.setMnemonic(KeyEvent.VK_N);
        gui.newReport.add(gui.barChart);
        gui.newReport.add(gui.pieChart);
        gui.newReport.add(gui.pdfReport);
        gui.file = new JMenu("File");
        gui.file.setMnemonic(KeyEvent.VK_F);
        gui.file.add(gui.newReport);
        gui.file.addSeparator();
        gui.file.add(gui.open);
        gui.file.add(gui.save);
        gui.file.addSeparator();
        gui.file.add(gui.saveAndClose);
        gui.file.add(gui.close);

        // Edit attributes
        gui.preferences = new JMenuItem("Preferences");
        gui.preferences.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        gui.preferences.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //preferences();
                System.out.println("COMMENTED OUT - Not fully working yet");
            }
        });
        gui.edit = new JMenu("Edit");
        gui.edit.setMnemonic(KeyEvent.VK_E);
        gui.edit.add(gui.preferences);

        // Help attributes
        gui.about = new JMenuItem("About");
        gui.about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        gui.about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                about();
            }
        });
        gui.checkForUpdates = new JMenuItem("Check for Updates");
        gui.checkForUpdates.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                checkForUpdates();
            }
        });
        gui.rssFeed = new JMenuItem("Check RSS Feed");
        gui.rssFeed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                checkRSSFeed();
            }
        });
        gui.welcome = new JMenuItem("Welcome Page");
        gui.welcome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                welcome();
            }
        });
        gui.help = new JMenu("Help");
        gui.help.setMnemonic(KeyEvent.VK_H);
        //gui.help.add(gui.rssFeed);
        //gui.help.addSeparator();
        gui.help.add(gui.welcome);
        gui.help.add(gui.checkForUpdates);
        gui.help.add(gui.about);

        // add menubar to JFrame
        gui.bar = new JMenuBar();
        gui.bar.add(gui.file); // add file menu to menubar
        gui.bar.add(gui.edit); // add edit menu to menubar
        gui.bar.add(gui.help); // add help menu to menubar
        setJMenuBar(gui.bar);
    }
    public void modulePanel() {
        add(cardHolderPanel(), BorderLayout.CENTER);
    }
    public void open() {
        System.out.println("open - NOT DONE YET");
    }
    public void optionPanel() {
        // add elements to optionPanel
        gui.optionPanel = new JPanel();
        gui.optionPanel.setPreferredSize(new Dimension(235, 480));
        //gui.optionPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); // Allows for a bevel border
        gui.optionPanel.setBorder(BorderFactory.createTitledBorder("Categories"));
        gui.optionPanel.setBackground(Color.WHITE);
        gui.optionPanel.add(new GUI_Menu());

        // add optionPanel to JFrame
        add(gui.optionPanel, BorderLayout.WEST);
    }
    public void preferences() {
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, PREFERENCES_MODULE);
        statusPanel(7);
    }
    public void save() {
        new Thread(new SaveThread()).start(); //Start the thread
    }
    public int saveChoice(int next) {
        int percent = 7; // 14 * 7 = 98
        switch(next) {
            case 0:
                Basics_PDF_Report basics_pdf = new Basics_PDF_Report();
                break;
            case 1:
                Fertilizer_PDF_Report fertilizer_pdf = new Fertilizer_PDF_Report();
                break;
            case 2:
            Gas_PDF_Report gas_pdf = new Gas_PDF_Report();
                break;
            case 3:
            Labour_PDF_Report labour_pdf = new Labour_PDF_Report();
                break;
            case 4:
            Lighting_PDF_Report lighting_pdf = new Lighting_PDF_Report();
                break;
            case 5:
            Machinery_PDF_Report machinery_pdf = new Machinery_PDF_Report();
                break;
            case 6:
            Mortgaged_PDF_Report mortgaged_pdf = new Mortgaged_PDF_Report();
                break;
            case 7:
            Rented_PDF_Report rented_pdf = new Rented_PDF_Report();
                break;
            case 8:
            Slurry_PDF_Report slurry_pdf = new Slurry_PDF_Report();
                break;
            case 9:
            Water_PDF_Report water_pdf = new Water_PDF_Report();
                break;
            case 10:
            Herd_PDF_Report herd_pdf = new Herd_PDF_Report();
                break;
            case 11:
            Feed_PDF_Report feed_pdf = new Feed_PDF_Report();
                break;
            case 12:
            Medical_PDF_Report med_pdf = new Medical_PDF_Report();
                break;
            case 13:
            Vet_Visit_PDF_Report vet_pdf = new Vet_Visit_PDF_Report();
                break;
            default:
                System.out.println("ERROR");
                break;
        }
        return percent;
    }
    public void showBasics() {
        basicsPanel.refresh();
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, BASICS_MODULE);
        statusPanel(1);
    }
    public void showBeef() {
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, BEEF_MODULE);
        statusPanel(2);
    }
    public void showDairy() {
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, DAIRY_MODULE);
        System.out.println("dairy - NOT DONE YET");
        statusPanel(3);
    }
    public void showExpense() {
        showExpensePanel.refresh();
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, SHOW_EXPENSE);
        statusPanel(5);
    }
    public void showTilage() {
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, TILAGE_MODULE);
        System.out.println("tilage - NOT DONE YET");
        statusPanel(4);
    }
    public JLabel status(int statusIn) {
        // to create the text status of what is happening
        gui.statusLabel = new JLabel();
        String[] stat = string.status();
        gui.statusLabel.setText(stat[statusIn]);
        gui.statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        return gui.statusLabel;
    }
    public void statusPanel(int statusIn) {
        // to create the status bar at the bottom of the GUI in its own panel
        gui.statusPanel = new JPanel();
        gui.statusPanel.setPreferredSize(new Dimension(this.getWidth(), 20));
        gui.statusPanel.setLayout(new BorderLayout());
        //gui.statusPanel.setLayout(new BoxLayout(gui.statusPanel, BoxLayout.X_AXIS));
        gui.statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

        // get a return from the status method
        gui.statusPanel.add(status(statusIn), BorderLayout.WEST);
        gui.statusPanel.add(progressBar(), BorderLayout.EAST);
        add(gui.statusPanel, BorderLayout.SOUTH);
    }
    public JProgressBar progressBar() {
        Dimension dim = new Dimension(100,20);
        gui.progbar = new JProgressBar(0, 98);
        gui.progbar.setPreferredSize(dim);
        gui.progbar.setMaximumSize(dim);
        gui.progbar.setVisible(false);
        return gui.progbar;
    }
    public void welcome() {
        CardLayout cardLayout = (CardLayout) (gui.modulePanel.getLayout());
        cardLayout.show(gui.modulePanel, WELCOME_PAGE);
        statusPanel(0);
    }

    class GUI_Menu extends JPanel implements TreeSelectionListener {
        // objects
        private JTree tree;
    
        // Struct structure class
        private class MenuInfo {
            public String menuName;
            public int choice;

            public MenuInfo(String menu, int choice) {
                menuName = menu;
                this.choice = choice;
            }
            public String toString() {
                return menuName;
            }
        }

        public GUI_Menu() {
            super(new GridLayout(1,0));
            //Create the nodes.
            DefaultMutableTreeNode top = new DefaultMutableTreeNode(new MenuInfo("Finance Manager", 000));
            createNodes(top);

            //Create a tree that allows one selection at a time.
            tree = new JTree(top);
            tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

            //Listen for when the selection changes.
            tree.addTreeSelectionListener(this);
        
            if(playWithLineStyle) {
                System.out.println("line style = " + lineStyle);
                tree.putClientProperty("JTree.lineStyle", lineStyle);
            }

            //Create the scroll pane and add the tree to it.
            JScrollPane treeView = new JScrollPane(tree);

            JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
            splitPane.setTopComponent(treeView);
            //splitPane.setBottomComponent(null);
            splitPane.setDividerLocation(100);
            splitPane.setPreferredSize(new Dimension(225, 500));
            add(splitPane);
        }
        /** Required by TreeSelectionListener interface. */
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if(node == null) {
                return;
            }
            Object nodeInfo = node.getUserObject();
            MenuInfo menu = (MenuInfo)nodeInfo;
            if(node.isLeaf()) {
                display(menu.choice);
                if(DEBUG) {
                    System.out.print("WORKS???");
                }
            }
            else {
                display(menu.choice);
            }
            if(DEBUG) {
                System.out.println(nodeInfo.toString());
            }
        }
        private void createNodes(DefaultMutableTreeNode top) {
            DefaultMutableTreeNode category = null;
            DefaultMutableTreeNode secondary = null;
            DefaultMutableTreeNode menu = null;

            category = new DefaultMutableTreeNode(new MenuInfo("Basic Utilities", 100));
            top.add(category);

            menu = new DefaultMutableTreeNode(new MenuInfo("Add Expense", 110));
            category.add(menu);
            menu = new DefaultMutableTreeNode(new MenuInfo("Show Expenses", 120));
            category.add(menu);

            category = new DefaultMutableTreeNode(new MenuInfo("Beef Farming", 200));
            top.add(category);

            secondary = new DefaultMutableTreeNode(new MenuInfo("Herd Management", 210));
            category.add(secondary);
            menu = new DefaultMutableTreeNode(new MenuInfo("Manage Herd", 211));
            secondary.add(menu);
            menu = new DefaultMutableTreeNode(new MenuInfo("Show Herd", 212));
            secondary.add(menu);
            menu = new DefaultMutableTreeNode(new MenuInfo("Manage Cows", 213));
            secondary.add(menu);

            secondary = new DefaultMutableTreeNode(new MenuInfo("Cow Management", 220));
            category.add(secondary);
            menu = new DefaultMutableTreeNode(new MenuInfo("Create Cow", 221));
            secondary.add(menu);
            menu = new DefaultMutableTreeNode(new MenuInfo("Sell Cow", 222));
            secondary.add(menu);

            secondary = new DefaultMutableTreeNode(new MenuInfo("Medical", 230));
            category.add(secondary);
            menu = new DefaultMutableTreeNode(new MenuInfo("Create Vet Visit", 231));
            secondary.add(menu);
            menu = new DefaultMutableTreeNode(new MenuInfo("Create Medical Expense", 232));
            secondary.add(menu);

            secondary = new DefaultMutableTreeNode(new MenuInfo("Feed Management", 240));
            category.add(secondary);

            category = new DefaultMutableTreeNode(new MenuInfo("Reports", 900));
            top.add(category);

            menu = new DefaultMutableTreeNode(new MenuInfo("Bar Chart", 910));
            category.add(menu);
            menu = new DefaultMutableTreeNode(new MenuInfo("Pie Chart", 920));
            category.add(menu);
            menu = new DefaultMutableTreeNode(new MenuInfo("PDF Report", 930));
            category.add(menu);
        }
        public void display(int choice) {
            switch(choice) {
                // Menu
                case 000: // menu
                    GUI.this.welcome();
                    System.out.println("000");
                    break;

                // Basics
                case 100: // basics
                    GUI.this.showBasics();
                    System.out.println("100");
                    break;
                case 110: // create_expense
                    GUI.this.createExpense();
                    System.out.println("110");
                    break;
                case 120: // show_expense
                    GUI.this.showExpense();
                    System.out.println("120");
                    break;

                // Beef
                case 200: // beef
                    //GUI.this.showBeef();
                    System.out.println("200");
                    break;
                // Herd Management
                case 210: // herd_management
                    System.out.println("210");
                    break;
                case 211: // manage_herd
                    System.out.println("211");
                    GUI.this.createHerd();
                    break;
                case 212: // show_herd
                    System.out.println("212");
                    GUI.this.showHerd();
                    break;
                case 213: // manage_cow
                    System.out.println("213");
                    GUI.this.manageHerd();
                    break;
                // Cow Management
                case 220: // cow_management
                    System.out.println("220");
                    break;
                case 221: // create_cow
                    System.out.println("221");
                    GUI.this.createCow();
                    break;
                case 222: // sell_cow
                    System.out.println("222");
                    GUI.this.sellCow();
                    break;
                // Medical Management
                case 230: // medical_management
                    System.out.println("230");
                    GUI.this.showMedOverview();
                    break;
                case 231: // create_vet_visit
                    System.out.println("231");
                    GUI.this.createVetVisit();
                    break;
                case 232: // create_medical_expense
                    System.out.println("232");
                    GUI.this.createMedicalExpense();
                    break;
                case 240: // feed_management
                    System.out.println("240");
                    GUI.this.createFeedExpense();
                    break;
                // Reports
                case 900: // reports
                    System.out.println("900");
                    break;
                case 910: // bar charts
                    System.out.println("910");
                    GUI.this.barChart(true);
                    break;
                case 920: // pie charts
                    System.out.println("920");
                    GUI.this.pieChart(true);
                    break;
                case 930: // pdf reports
                    System.out.println("930");
                    GUI.this.pieChart(false);
                    GUI.this.pdfReport();
                    break;
                default:
                    System.out.println("ERROR!!!");
            }
        }
    }

    class SaveThread implements Runnable {
        public void run() {
            int i = 0;
            int next = 0;
            gui.progbar.setVisible(true);
            while(i<=98) { //Progressively increment variable i
                gui.progbar.setValue(i); //Set value
                gui.progbar.repaint(); //Refresh graphics
                i += saveChoice(next);
                next++;
            }
            gui.progbar.setValue(0); //Set value
            gui.progbar.setVisible(false);
        }
    }
}