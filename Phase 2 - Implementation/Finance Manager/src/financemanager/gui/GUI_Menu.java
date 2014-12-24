package financemanager.gui;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 *
 * ** THIS CLASS IS NOW DEAD **
 * * Code was put into GUI.java  *
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class GUI_Menu extends JPanel implements TreeSelectionListener {
    // objects
    private JTree tree;
    private static boolean DEBUG = false;

    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";

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

        menu = new DefaultMutableTreeNode(new MenuInfo("Create Expense", 110));
        category.add(menu);
        menu = new DefaultMutableTreeNode(new MenuInfo("Add to Expense", 120));
        category.add(menu);
        menu = new DefaultMutableTreeNode(new MenuInfo("Show Expenses", 130));
        category.add(menu);

        category = new DefaultMutableTreeNode(new MenuInfo("Beef Farming", 200));
        top.add(category);

        secondary = new DefaultMutableTreeNode(new MenuInfo("Herd Management", 210));
        category.add(secondary);
        menu = new DefaultMutableTreeNode(new MenuInfo("Create Herd", 211));
        secondary.add(menu);
        menu = new DefaultMutableTreeNode(new MenuInfo("Show Herd", 212));
        secondary.add(menu);
        menu = new DefaultMutableTreeNode(new MenuInfo("Add Cow", 213));
        secondary.add(menu);
        menu = new DefaultMutableTreeNode(new MenuInfo("Remove Cow", 214));
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
    }
    public void display(int choice) {
        switch(choice) {
            // Menu
            case 000: // menu
                System.out.println("000");
                break;

            // Basics
            case 100: // basics
                //function.showBasics();
                System.out.println("100");
                break;
            case 110: // create_expense
                System.out.println("110");
                break;
            case 120: // add_to_expense
                System.out.println("120");
                break;
            case 130: // show_expense
                System.out.println("130");
                break;

            // Beef
            case 200: // beef
                //function.showBeef();
                System.out.println("200");
                break;
            // Herd Management
            case 210: // herd_management
                System.out.println("210");
                break;
            case 211: // create_herd
                System.out.println("211");
                break;
            case 212: // show_herd
                System.out.println("212");
                break;
            case 213: // add_cow
                System.out.println("213");
                break;
            case 214: // remove_cow
                System.out.println("214");
                break;
            // Cow Management
            case 220: // cow_management
                System.out.println("220");
                break;
            case 221: // create_cow
                System.out.println("221");
                break;
            case 222: // sell_cow
                System.out.println("222");
                break;
            // Medical Management
            case 230: // medical_management
                System.out.println("230");
                break;
            case 231: // create_vet_visit
                System.out.println("231");
                break;
            case 232: // create_medical_expense
                System.out.println("232");
                break;
            default:
                System.out.println("ERROR!!!");
        }
    }
}