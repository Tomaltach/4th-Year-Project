package spreadsheet;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;

import beefmodule.datahandler.*;
import beefmodule.gui.panels.*;
import beefmodule.sqlhandler.*;
import financemanager.*;

public class Manage_Herds_Spreadsheet extends JPanel {
    // objects
    SQL_Beef_Handler sql = new SQL_Beef_Handler();
    Manage_Herds_JPanel mhp;
    JTable table;
    private boolean DEBUG = false;
    //Create and set up the window.
    public JPanel panel = new JPanel();

    int rowSize = 0;
    int colSize = 2;

    Object[][] data;

    DefaultTableModel bModel = new DefaultTableModel() {
        //setting the jtable read only
        public boolean isCellEditable(int row, int column) {
            switch (column) {
                case 0:
                    return true;
                default:
                    return false;
            }
        }
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);

            boolean c;
            try {
                c = true;
                String herd_Id_In = (String) data[row][col];
                int newHerdIdIn = Integer.parseInt(herd_Id_In);
            }
            catch(NumberFormatException e) {
                c = false;
                JOptionPane.showMessageDialog(null, "ERROR! Must use numbers only!", "Error Massage", JOptionPane.ERROR_MESSAGE);
            }
            if(c == true) {
                String herd_Id_In = (String) data[row][col];
                int newHerdIdIn = Integer.parseInt(herd_Id_In);
                String cowtagIn = (String) data[row][col+1];
                int oldHerdIdIn = (Integer) bModel.getValueAt(row, col);

                try {
                    boolean check = checkHerdActive(newHerdIdIn);
                    if(check == true) {
                        sql.add_cow_to_herd(newHerdIdIn, cowtagIn);
                        mhp.refresh();
                    }
                    else {
                        if(newHerdIdIn == 0) {
                            JOptionPane.showMessageDialog(null, "ERROR! Cow must belong to herd!\nHerd '0' is default before Cow is assigned!", "Error Massage", JOptionPane.ERROR_MESSAGE);
                        }
                        else if(newHerdIdIn == -1) {
                            JOptionPane.showMessageDialog(null, "ERROR! Cow must belong to herd!\nHerd '-1' is default for sold Cows!", "Error Massage", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "ERROR! Herd '" + newHerdIdIn + "' does not exists!", "Error Massage", JOptionPane.ERROR_MESSAGE);
                            sql.add_cow_to_herd(oldHerdIdIn, cowtagIn);
                            mhp.refresh();
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    };

    // used for basics
    Object[] tableColumnNames = new Object[2];
    Object[] objects = new Object[2];

    java.util.List<Herd_Data> herdData;
    String bD;
    int width;
    int height;

    public Manage_Herds_Spreadsheet() {

    }
    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createPanel() {
        //Create and set up the content pane.
        Manage_Herds_Spreadsheet newContentPane = new Manage_Herds_Spreadsheet();
        newContentPane.setOpaque(true); //content panes must be opaque
        panel.add(newContentPane);
    }
    public void init() {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createPanel();
            }
        });
    }
    // Herd
    public JPanel getManageHerdsSpreadsheet(int width, int type, int height, java.util.List<Herd_Data> herdData, Manage_Herds_JPanel mhp) {
        this.width = width;
        this.height = height;
        this.mhp = mhp;
        this.bD = "Let Slide";

        table = new JTable(bModel);
        
        this.herdData = herdData;
        displayManageHerdData(type);
        init();

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        //panel.add(button);
        return panel;
    }
    public void fillManageHerdTable(int i) {
        int numRows = 24;
        if(i <= numRows) {
            while(i <= numRows) {
                objects[0] = "";
                objects[1] = "";
                bModel.addRow(objects);
                i++;
            }
        }
    }
    public void blankManageHerdData() {
        //populating the tablemodel
        objects[0] = "";
        objects[1] = "";
        bModel.addRow(objects);
        int i = 0;
        while(i<20) {
            objects[0] = "";
            objects[1] = "";
            bModel.addRow(objects);
            i++;
	}
    }
    public void updateManageHerd(Manage_Herds_Spreadsheet ss, java.util.List<Herd_Data> herdData) {
        this.herdData = herdData;

        ss.bModel.getDataVector().removeAllElements();
        displayManageHerdData(0);
        ss.bModel.fireTableDataChanged();
    }
    public void displayManageHerdData(int type) {
        //setting the column name
        tableColumnNames[0] = "Herd ID";
        tableColumnNames[1] = "Cow Tag";
	bModel.setColumnIdentifiers(tableColumnNames);
	if(bD == null) {
            this.table.setModel(bModel);
            return;
	}
        switch(type) {
            case 0:
                manageHerdData();
                break;
            case 1:
                blankManageHerdData();
                break;
            default:
                System.out.println("ERROR! displayHerdData");
                break;
        }
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while(en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new MyTableCellRenderer2());
        }
        table.getColumnModel().getColumn(1).setPreferredWidth(100);

        table.setPreferredScrollableViewportSize(new Dimension(width, height));
        table.setFillsViewportHeight(true);

        if(DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }
    }
    public void manageHerdData() {
        int i = 0;
        rowSize = herdData.size();
        data = new Object[rowSize][colSize];
        ListIterator<Herd_Data> herddata_list = herdData.listIterator();
        while(herddata_list.hasNext()) {
            Herd_Data herd = herddata_list.next();
            objects[0] = herd.getHerd_id();
            objects[1] = herd.getCowtag();
            bModel.addRow(objects);
            for(int col=0; col<2; col++){
                data[i][col] = objects[col];
            }
            i++;
	}
        fillManageHerdTable(i);
    }
    public Object GetData(JTable table, int row_index, int col_index){
        return table.getModel().getValueAt(row_index, col_index);
    }
    public boolean checkHerdActive(int newHerdIdIn) {
        boolean check = false;
        try {
            herdData = sql.select_herd_data_active();
            ListIterator<Herd_Data> herddata_list = herdData.listIterator();
            while(herddata_list.hasNext()) {
                Herd_Data herd = herddata_list.next();
                int herd_ID = herd.getHerd_id();
                System.out.println(herd.getHerd_id());
                if(herd_ID == newHerdIdIn) {
                    check = true;
                    return check;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check;
    }
}

class MyTableCellRenderer2 extends DefaultTableCellRenderer {
    Colors colors = new Colors();
    Color[] color = colors.colors();
    int odd = 3;
    int even = 8;
    int highlighted = 9;
    int title = 5;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // component will actually be this.
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        component.setBackground(row%2==0 ? color[odd] : color[even]);

        if(isSelected) { // cell selected
            component.setBackground(color[highlighted]);
        }
        checkTitles(component, value, row);

        return component;
    }
    public void checkTitles(Component component, Object value, int row) {
        // basic titles
        if(value == "Fertilizer Info" || value == "Gas Info" || value == "Labour Info" || value == "Lighting Info" || value == "Machinery Info" || value == "Mortgage Info" || value == "Rent Info" || value == "Slurry Info" || value == "Water Info") { // title cell
            component.setBackground(color[title]);
        }
        // beef titles
        if(value == "Herd Info" || value == "Cow Info" || value == "Medical Info" || value == "Vet Visit Info") {
            component.setBackground(color[title]);
        }

        if(value == null) {
            //component.setBackground(Color.BLACK);
            component.setBackground(color[title]);
        }
    }
    public void setOdd(int odd) {
        this.odd = odd;
    }
    public void setEven(int even) {
        this.even = even;
    }
    public void setHighlighted(int highlighted) {
        this.highlighted = highlighted;
    }
}