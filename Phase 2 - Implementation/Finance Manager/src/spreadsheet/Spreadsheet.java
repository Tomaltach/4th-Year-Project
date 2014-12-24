
package spreadsheet;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import beefmodule.datahandler.*;
import financemanager.*;
import financemanager.datahandler.*;

public class Spreadsheet extends JPanel {
    JTable table;
    private boolean DEBUG = false;
    //Create and set up the window.
    public JPanel panel = new JPanel();

    DefaultTableModel aModel = new DefaultTableModel() {
        //setting the jtable read only
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    // used for basics
    Object[] tableColumnNames = new Object[4];
    Object[] objects = new Object[4];
    // used for beef
    Object[] herdColumnNames = new Object[4];
    Object[] herdObjects = new Object[4];
    Object[] herdLColumnNames = new Object[3];
    Object[] herdLObjects = new Object[3];
    Object[] beefColumnNames = new Object[6];
    Object[] beefObjects = new Object[6];
    Object[] medColumnNames = new Object[5];
    Object[] medObjects = new Object[5];
    Object[] vvColumnNames = new Object[5];
    Object[] vvObjects = new Object[5];
    // feed expenses
    Object[] feedColumnNames = new Object[7];
    Object[] feedObjects = new Object[7];

    int width;
    int height;

    // BASIC_EXPENSE Data Lists
    java.util.List<Basics_Data> bData;
    java.util.List<Fertilizer_Data> fData;
    java.util.List<Gas_Data> gData;
    java.util.List<Labour_Data> lData;
    java.util.List<Lighting_Data> liData;
    java.util.List<Machinery_Data> maData;
    java.util.List<Mortgaged_Data> mData;
    java.util.List<Rented_Data> rData;
    java.util.List<Slurry_Data> sData;
    java.util.List<Water_Data> wData;
    String bD;
    // BEEF_EXPENSES Data Lists
    java.util.List<Herd_Data> herdData;
    java.util.List<Cow_Data> beefData;
    java.util.List<Medical_Data> meData;
    java.util.List<Vet_Visit_Data> vvData;
    java.util.List<Feed_Data> feedData;

    public Spreadsheet() {

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
        Spreadsheet newContentPane = new Spreadsheet();
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

    // BASIC DATA INFOMATION
    public JPanel getSpreadsheet() {
        init();
        return panel;
    }
    public JPanel getSpreadsheet(int width, int height, int type) {
        this.width = width;
        this.height = height;
        this.bD = "Let Slide";

        table = new JTable(aModel);

        displayBasicData(type);
        init();

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        return panel;
    }
    public JPanel getSpreadsheet(int width, int height, java.util.List<Fertilizer_Data> fData,
                                 java.util.List<Gas_Data> gData, java.util.List<Labour_Data> lData,
                                 java.util.List<Lighting_Data> liData, java.util.List<Machinery_Data> maData,
                                 java.util.List<Mortgaged_Data> mData, java.util.List<Rented_Data> rData,
                                 java.util.List<Slurry_Data> sData, java.util.List<Water_Data> wData) {
        this.width = width;
        this.height = height;

        this.fData = fData;
        this.gData = gData;
        this.lData = lData;
        this.liData = liData;
        this.maData = maData;
        this.mData = mData;
        this.rData = rData;
        this.sData = sData;
        this.wData = wData;

        table = new JTable(aModel);

        displayAllBasicData();
        init();

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        return panel;
    }
    public void updateAllBasicData(Spreadsheet ss, java.util.List<Fertilizer_Data> fData,
                                   java.util.List<Gas_Data> gData, java.util.List<Labour_Data> lData,
                                   java.util.List<Lighting_Data> liData, java.util.List<Machinery_Data> maData,
                                   java.util.List<Mortgaged_Data> mData, java.util.List<Rented_Data> rData,
                                   java.util.List<Slurry_Data> sData, java.util.List<Water_Data> wData) {
        this.fData = fData;
        this.gData = gData;
        this.lData = lData;
        this.liData = liData;
        this.maData = maData;
        this.mData = mData;
        this.rData = rData;
        this.sData = sData;
        this.wData = wData;

        ss.aModel.getDataVector().removeAllElements();
        displayAllBasicUpdatedData();
        ss.aModel.fireTableDataChanged();
    }
    public void fillBasicTables(int i, int j) {
        int numRows = 21;
        if(j == 0) {
            if(i <= numRows) {
                while(i <= numRows) {
                    objects[0] = "";
                    objects[1] = "";
                    objects[2] = "";
                    objects[3] = "";
                    aModel.addRow(objects);
                    i++;
                }
            }
        }
    }
    private void displayBasicData(int type) {
        int j = 0;
	//setting the column name
        tableColumnNames[0] = "Type";
	tableColumnNames[1] = "Expense";
	tableColumnNames[2] = "Month";
	tableColumnNames[3] = "Year";
	aModel.setColumnIdentifiers(tableColumnNames);
	if(fData == null && gData == null && lData == null && liData == null && maData == null && mData == null && rData == null && sData == null && wData == null && bD == null && bData == null) {
            this.table.setModel(aModel);
            return;
	}
        switch(type) {
            case 0:
                basicData();
                break;
            case 1:
                fertilizerData(j);
                break;
            case 2:
                gasData(j);
                break;
            case 3:
                labourData(j);
                break;
            case 4:
                lightingData(j);
                break;
            case 5:
                machineryData(j);
                break;
            case 6:
                mortgagedData(j);
                break;
            case 7:
                rentedData(j);
                break;
            case 8:
                slurryData(j);
                break;
            case 9:
                waterData(j);
                break;
            case 10:
                blankData();
                break;
            default:
                System.out.println("ERROR! displayBasicData");
                break;
        }

        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while(en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new MyTableCellRenderer());
        }
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
    private void displayAllBasicData() {
        int j = 1;
	//setting the column name
        tableColumnNames[0] = "Type";
	tableColumnNames[1] = "Expense";
	tableColumnNames[2] = "Month";
	tableColumnNames[3] = "Year";
	aModel.setColumnIdentifiers(tableColumnNames);
	if(fData == null && gData == null && lData == null && liData == null && maData == null && mData == null && rData == null && sData == null && wData == null) {
            this.table.setModel(aModel);
            return;
	}

        fertilizerData(j);
        gasData(j);
        labourData(j);
	lightingData(j);
        machineryData(j);
        mortgagedData(j);
        rentedData(j);
        slurryData(j);
        waterData(j);

        table = new JTable(aModel);
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while(en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new MyTableCellRenderer());
        }
        table.setPreferredScrollableViewportSize(new Dimension(width, height));
        table.setFillsViewportHeight(true);

        if(DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
    }
    public void displayAllBasicUpdatedData() {
        int j = 1;
	//setting the column name
        tableColumnNames[0] = "Type";
	tableColumnNames[1] = "Expense";
	tableColumnNames[2] = "Month";
	tableColumnNames[3] = "Year";
	aModel.setColumnIdentifiers(tableColumnNames);
	if(fData == null && gData == null && lData == null && liData == null && maData == null && mData == null && rData == null && sData == null && wData == null) {
            this.table.setModel(aModel);
            return;
	}

        fertilizerData(j);
        gasData(j);
        labourData(j);
	lightingData(j);
        machineryData(j);
        mortgagedData(j);
        rentedData(j);
        slurryData(j);
        waterData(j);

        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while(en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new MyTableCellRenderer());
        }
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
    public void blankData() {
        //populating the tablemodel
        objects[0] = "";
        objects[1] = "";
        objects[2] = "";
        objects[3] = "";
        aModel.addRow(objects);
        int i = 0;
        while(i<20) {
            objects[0] = "";
            objects[1] = "";
            objects[2] = "";
            objects[3] = "";
            aModel.addRow(objects);
            i++;
	}
    }
    public void basicData() {
        int i = 0;
        int j = 0;
        ListIterator<Basics_Data> bdata_list = bData.listIterator();
        //populating the tablemodel
        while(bdata_list.hasNext()) {
            Basics_Data bd = bdata_list.next();
            objects[0] = bd.getTitle();
            objects[1] = bd.getExpense();
            objects[2] = bd.getMonth();
            if(bd.getYear() == 0){
                objects[3] = null;
            }
            else {
                objects[3] = bd.getYear();
            }
            aModel.addRow(objects);
            i++;
	}
        fillBasicTables(i, j);
    }
    public void fertilizerData(int j) {
        int i = 0;
        ListIterator<Fertilizer_Data> fdata_list = fData.listIterator();
        //populating the tablemodel
        objects[0] = "Fertilizer Info";
        objects[1] = null;
        objects[2] = null;
        objects[3] = null;
        aModel.addRow(objects);
        i++;
        while(fdata_list.hasNext()) {
            Fertilizer_Data fd = fdata_list.next();
            objects[0] = "";
            objects[1] = fd.getExpense();
            objects[2] = fd.getMonth();
            objects[3] = fd.getYear();
            aModel.addRow(objects);
            i++;
	}
        fillBasicTables(i, j);
    }
    public void gasData(int j) {
        int i = 0;
        ListIterator<Gas_Data> gdata_list = gData.listIterator();
        //populating the tablemodel
        objects[0] = "Gas Info";
        objects[1] = null;
        objects[2] = null;
        objects[3] = null;
        aModel.addRow(objects);
        i++;
        while(gdata_list.hasNext()) {
            Gas_Data gd = gdata_list.next();
            objects[0] = "";
            objects[1] = gd.getExpense();
            objects[2] = gd.getMonth();
            objects[3] = gd.getYear();
            aModel.addRow(objects);
            i++;
	}
        fillBasicTables(i, j);
    }
    public void labourData(int j) {
        int i = 0;
        ListIterator<Labour_Data> ldata_list = lData.listIterator();
        //populating the tablemodel
        objects[0] = "Labour Info";
        objects[1] = null;
        objects[2] = null;
        objects[3] = null;
        aModel.addRow(objects);
        i++;
        while(ldata_list.hasNext()) {
            Labour_Data ld = ldata_list.next();
            objects[0] = "";
            objects[1] = ld.getExpense();
            objects[2] = ld.getMonth();
            objects[3] = ld.getYear();
            aModel.addRow(objects);
            i++;
	}
        fillBasicTables(i, j);
    }
    public void lightingData(int j) {
        int i = 0;
        ListIterator<Lighting_Data> lidata_list = liData.listIterator();
        //populating the tablemodel
        objects[0] = "Lighting Info";
        objects[1] = null;
        objects[2] = null;
        objects[3] = null;
        aModel.addRow(objects);
        i++;
        while(lidata_list.hasNext()) {
            Lighting_Data lid = lidata_list.next();
            objects[0] = "";
            objects[1] = lid.getExpense();
            objects[2] = lid.getMonth();
            objects[3] = lid.getYear();
            aModel.addRow(objects);
            i++;
	}
        fillBasicTables(i, j);
    }
    public void machineryData(int j) {
        int i = 0;
        ListIterator<Machinery_Data> madata_list = maData.listIterator();
        //populating the tablemodel
        objects[0] = "Machinery Info";
        objects[1] = null;
        objects[2] = null;
        objects[3] = null;
        aModel.addRow(objects);
        i++;
        while(madata_list.hasNext()) {
            Machinery_Data mad = madata_list.next();
            objects[0] = "";
            objects[1] = mad.getExpense();
            objects[2] = mad.getMonth();
            objects[3] = mad.getYear();
            aModel.addRow(objects);
            i++;
	}
        fillBasicTables(i, j);
    }
    public void mortgagedData(int j) {
        int i = 0;
        ListIterator<Mortgaged_Data> mdata_list = mData.listIterator();
        //populating the tablemodel
        objects[0] = "Mortgage Info";
        objects[1] = null;
        objects[2] = null;
        objects[3] = null;
        aModel.addRow(objects);
        i++;
        while(mdata_list.hasNext()) {
            Mortgaged_Data md = mdata_list.next();
            objects[0] = "";
            objects[1] = md.getExpense();
            objects[2] = md.getMonth();
            objects[3] = md.getYear();
            aModel.addRow(objects);
            i++;
	}
        fillBasicTables(i, j);
    }
    public void rentedData(int j) {
        int i = 0;
        ListIterator<Rented_Data> rdata_list = rData.listIterator();
        //populating the tablemodel
        objects[0] = "Rent Info";
        objects[1] = null;
        objects[2] = null;
        objects[3] = null;
        aModel.addRow(objects);
        i++;
        while(rdata_list.hasNext()) {
            Rented_Data rd = rdata_list.next();
            objects[0] = "";
            objects[1] = rd.getExpense();
            objects[2] = rd.getMonth();
            objects[3] = rd.getYear();
            aModel.addRow(objects);
            i++;
	}
        fillBasicTables(i, j);
    }
    public void slurryData(int j) {
        int i = 0;
        ListIterator<Slurry_Data> sdata_list = sData.listIterator();
        //populating the tablemodel
        objects[0] = "Slurry Info";
        objects[1] = null;
        objects[2] = null;
        objects[3] = null;
        aModel.addRow(objects);
        i++;
        while(sdata_list.hasNext()) {
            Slurry_Data sd = sdata_list.next();
            objects[0] = "";
            objects[1] = sd.getExpense();
            objects[2] = sd.getMonth();
            objects[3] = sd.getYear();
            aModel.addRow(objects);
            i++;
	}
        fillBasicTables(i, j);
    }
    public void waterData(int j) {
        int i = 0;
        ListIterator<Water_Data> wdata_list = wData.listIterator();
        //populating the tablemodel
        objects[0] = "Water Info";
        objects[1] = null;
        objects[2] = null;
        objects[3] = null;
        aModel.addRow(objects);
        i++;
        while(wdata_list.hasNext()) {
            Water_Data wd = wdata_list.next();
            objects[0] = "";
            objects[1] = wd.getExpense();
            objects[2] = wd.getMonth();
            objects[3] = wd.getYear();
            aModel.addRow(objects);
            i++;
	}
        fillBasicTables(i, j);
    }
    public void update(Spreadsheet ss, int type, java.util.List<Basics_Data> bData) {
        this.bData = bData;

        ss.aModel.getDataVector().removeAllElements();
        displayBasicData(type);
        ss.aModel.fireTableDataChanged();
    }
    // BEEF DATA INFORMATION
    public void fillHerdTable(int i) {
        int numRows = 21;
        if(i <= numRows) {
            while(i <= numRows) {
                herdObjects[0] = "";
                herdObjects[1] = "";
                herdObjects[2] = "";
                herdObjects[3] = "";
                aModel.addRow(herdObjects);
                i++;
            }
        }
    }
    public void fillHerdLTable(int i) {
        int numRows = 24;
        if(i <= numRows) {
            while(i <= numRows) {
                herdLObjects[0] = "";
                herdLObjects[1] = "";
                herdLObjects[2] = "";
                aModel.addRow(herdLObjects);
                i++;
            }
        }
    }
    public void fillCowTable(int i) {
        int numRows = 21;
        if(i <= numRows) {
            while(i <= numRows) {
                beefObjects[0] = "";
                beefObjects[1] = "";
                beefObjects[2] = "";
                beefObjects[3] = "";
                beefObjects[4] = "";
                beefObjects[5] = "";
                aModel.addRow(beefObjects);
                i++;
            }
        }
    }
    public void fillMedVetTable(int i, int j) {
        int numRows = 21;
        if(j == 0) {
            if(i <= numRows) {
                while(i <= numRows) {
                    medObjects[0] = "";
                    medObjects[1] = "";
                    medObjects[2] = "";
                    medObjects[3] = "";
                    medObjects[4] = "";
                    aModel.addRow(medObjects);
                    i++;
                }
            }
        }
    }
    public void fillFeedTable(int i) {
        int numRows = 21;
        if(i <= numRows) {
            while(i <= numRows) {
                feedObjects[0] = "";
                feedObjects[1] = "";
                feedObjects[2] = "";
                feedObjects[3] = "";
                feedObjects[4] = "";
                feedObjects[5] = "";
                feedObjects[6] = "";
                aModel.addRow(feedObjects);
                i++;
            }
        }
    }
    // Overviews
    public JPanel getMedOverviewSpreadsheet(int width, int height, java.util.List<Medical_Data> meData,
                                            java.util.List<Vet_Visit_Data> vvData) {
        this.width = width;
        this.height = height;
        this.bD = "Let Slide";

        table = new JTable(aModel);

        this.meData = meData;
        this.vvData = vvData;
        displayMedOverviewData();
        init();

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        return panel;
    }

    public void displayMedOverviewData() {
        int j = 1;
	//setting the column name
        medColumnNames[0] = "Title";
        medColumnNames[1] = "Cow Tag";
	medColumnNames[2] = "Info";
	medColumnNames[3] = "Date";
	medColumnNames[4] = "Price";
	aModel.setColumnIdentifiers(medColumnNames);
	if(bD == null) {
            this.table.setModel(aModel);
            return;
	}

        medicalData(j);
        vetVisitData(j);

        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while(en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new MyTableCellRenderer());
        }
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

    public void updateMedOverview(Spreadsheet ss, java.util.List<Medical_Data> meData,
                                  java.util.List<Vet_Visit_Data> vvData) {
        this.meData = meData;
        this.vvData = vvData;

        ss.aModel.getDataVector().removeAllElements();
        displayMedOverviewData();
        ss.aModel.fireTableDataChanged();
    }

    // Herd
    public JPanel getHerdSpreadsheet(int width, int type, int height, java.util.List<Herd_Data> herdData) {
        this.width = width;
        this.height = height;
        this.bD = "Let Slide";

        table = new JTable(aModel);

        this.herdData = herdData;
        displayHerdData(type);
        init();

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        return panel;
    }
    public JPanel getHerdListSpreadsheet(int width, int type, int height, java.util.List<Herd_Data> herdData) {
        this.width = width;
        this.height = height;
        this.bD = "Let Slide";

        table = new JTable(aModel);

        this.herdData = herdData;
        displayHerdListData(type);
        init();

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        return panel;
    }
    public void blankHerdData() {
        //populating the tablemodel
        herdObjects[0] = "";
        herdObjects[1] = "";
        herdObjects[2] = "";
        herdObjects[3] = "";
        aModel.addRow(herdObjects);
        int i = 0;
        while(i<20) {
            herdObjects[0] = "";
            herdObjects[1] = "";
            herdObjects[2] = "";
            herdObjects[3] = "";
            aModel.addRow(herdObjects);
            i++;
	}
    }
    public void blankHerdLData() {
        //populating the tablemodel
        herdLObjects[0] = "";
        herdLObjects[1] = "";
        herdLObjects[2] = "";
        aModel.addRow(herdLObjects);
        int i = 0;
        while(i<20) {
            herdLObjects[0] = "";
            herdLObjects[1] = "";
            herdLObjects[2] = "";
            aModel.addRow(herdLObjects);
            i++;
	}
    }
    public void updateHerd(Spreadsheet ss, java.util.List<Herd_Data> herdData) {
        this.herdData = herdData;

        ss.aModel.getDataVector().removeAllElements();
        displayHerdData(0);
        ss.aModel.fireTableDataChanged();
    }
    public void updateLHerd(Spreadsheet ss, java.util.List<Herd_Data> herdData) {
        this.herdData = herdData;

        ss.aModel.getDataVector().removeAllElements();
        displayHerdListData(0);
        ss.aModel.fireTableDataChanged();
    }
    public void displayHerdData(int type) {
        //setting the column name
        herdColumnNames[0] = "Title";
        herdColumnNames[1] = "Herd ID";
	herdColumnNames[2] = "Creation Date";
	herdColumnNames[3] = "Termination Date";
	aModel.setColumnIdentifiers(herdColumnNames);
	if(bD == null) {
            this.table.setModel(aModel);
            return;
	}
        switch(type) {
            case 0:
                herdData();
                break;
            case 1:
                blankHerdData();
                break;
            default:
                System.out.println("ERROR! displayHerdData");
                break;
        }
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while(en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new MyTableCellRenderer());
        }

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
    public void displayHerdListData(int type) {
        //setting the column name
        herdLColumnNames[0] = "Title";
        herdLColumnNames[1] = "Herd ID";
	herdLColumnNames[2] = "Cow Tag";
	aModel.setColumnIdentifiers(herdLColumnNames);
	if(bD == null) {
            this.table.setModel(aModel);
            return;
	}
        switch(type) {
            case 0:
                herdLData();
                break;
            case 1:
                blankHerdLData();
                break;
            default:
                System.out.println("ERROR! displayHerdListData");
                break;
        }
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while(en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new MyTableCellRenderer());
        }

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
    public void herdData() {
        int i = 0;
        ListIterator<Herd_Data> herddata_list = herdData.listIterator();
        //populating the tablemodel
        herdObjects[0] = "Herd Info";
        herdObjects[1] = null;
        herdObjects[2] = null;
        herdObjects[3] = null;
        aModel.addRow(herdObjects);
        i++;
        while(herddata_list.hasNext()) {
            Herd_Data herd = herddata_list.next();
            herdObjects[0] = "";
            herdObjects[1] = herd.getHerd_id();
            herdObjects[2] = herd.getHerd_creation_date();
            if(herd.getHerd_termination_date() != null) {
                herdObjects[3] = herd.getHerd_termination_date();
            }
            else {
                herdObjects[3] = "";
            }
            aModel.addRow(herdObjects);
            i++;
	}
        fillHerdTable(i);
    }
    public void herdLData() {
        int i = 0;
        ListIterator<Herd_Data> herddata_list = herdData.listIterator();
        //populating the tablemodel
        herdLObjects[0] = "Herd Info";
        herdLObjects[1] = null;
        herdLObjects[2] = null;
        aModel.addRow(herdLObjects);
        i++;
        while(herddata_list.hasNext()) {
            Herd_Data herd = herddata_list.next();
            herdLObjects[0] = "";
            herdLObjects[1] = herd.getHerd_id();
            herdLObjects[2] = herd.getCowtag();
            aModel.addRow(herdLObjects);
            i++;
	}
        fillHerdLTable(i);
    }
    // Cow
    public JPanel getCowSpreadsheet(int width, int type, int height, java.util.List<Cow_Data> beefData) {
        this.width = width;
        this.height = height;
        this.bD = "Let Slide";

        table = new JTable(aModel);

        this.beefData = beefData;
        displayCowData(type);
        init();

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        return panel;
    }
    public void blankCowData() {
        //populating the tablemodel
        beefObjects[0] = "";
        beefObjects[1] = "";
        beefObjects[2] = "";
        beefObjects[3] = "";
        beefObjects[4] = "";
        beefObjects[5] = "";
        aModel.addRow(beefObjects);
        int i = 0;
        while(i<20) {
            beefObjects[0] = "";
            beefObjects[1] = "";
            beefObjects[2] = "";
            beefObjects[3] = "";
            beefObjects[4] = "";
            beefObjects[5] = "";
            aModel.addRow(beefObjects);
            i++;
	}
    }
    public void updateCow(Spreadsheet ss, java.util.List<Cow_Data> beefData) {
        this.beefData = beefData;

        ss.aModel.getDataVector().removeAllElements();
        displayCowData(0);
        ss.aModel.fireTableDataChanged();
    }
    public void displayCowData(int type) {
        //setting the column name
        beefColumnNames[0] = "Title";
        beefColumnNames[1] = "Cow Tag";
	beefColumnNames[2] = "Bought Price";
	beefColumnNames[3] = "Sold Price";
	beefColumnNames[4] = "Bought Date";
        beefColumnNames[5] = "Sold Date";
	aModel.setColumnIdentifiers(beefColumnNames);
	if(bD == null) {
            this.table.setModel(aModel);
            return;
	}
        switch(type) {
            case 0:
                cowData();
                break;
            case 1:
                blankCowData();
                break;
            default:
                System.out.println("ERROR! displayHerdData");
                break;
        }
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while(en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new MyTableCellRenderer());
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
    public void cowData() {
        int i = 0;
        ListIterator<Cow_Data> beefdata_list = beefData.listIterator();
        //populating the tablemodel
        beefObjects[0] = "Cow Info";
        beefObjects[1] = null;
        beefObjects[2] = null;
        beefObjects[3] = null;
        beefObjects[4] = null;
        beefObjects[5] = null;
        aModel.addRow(beefObjects);
        i++;
        while(beefdata_list.hasNext()) {
            Cow_Data beef = beefdata_list.next();
            beefObjects[0] = "";
            beefObjects[1] = beef.getCowTag();
            beefObjects[2] = beef.getBoughtPrice();
            if(beef.getSoldPrice() == null || beef.getSoldPrice() == 0.0) {
                beefObjects[3] = "";
            }
            else {
                beefObjects[3] = beef.getSoldPrice();
            }
            beefObjects[4] = beef.getBoughtDate();
            if(beef.getSoldDate() != null) {
                beefObjects[5] = beef.getSoldDate();
            }
            else {
                beefObjects[5] = "";
            }
            aModel.addRow(beefObjects);
            i++;
	}
        fillCowTable(i);
    }
    // Medical
    public JPanel getMedicalSpreadsheet(int width, int type, int height) {
        this.width = width;
        this.height = height;
        this.bD = "Let Slide";

        table = new JTable(aModel);

        displayMedicalData(type);
        init();

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        return panel;
    }
    public void blankMedData() {
        //populating the tablemodel
        medObjects[0] = "";
        medObjects[1] = "";
        medObjects[2] = "";
        medObjects[3] = "";
        medObjects[4] = "";
        aModel.addRow(medObjects);
        int i = 0;
        while(i<20) {
            medObjects[0] = "";
            medObjects[1] = "";
            medObjects[2] = "";
            medObjects[3] = "";
            medObjects[4] = "";
            aModel.addRow(medObjects);
            i++;
	}
    }
    public void displayMedicalData(int type){
        int j = 0;
        //setting the column name
        medColumnNames[0] = "Title";
        medColumnNames[1] = "Cow Tag";
	medColumnNames[2] = "Info";
	medColumnNames[3] = "Date";
	medColumnNames[4] = "Price";
	aModel.setColumnIdentifiers(medColumnNames);
	if(bD == null) {
            this.table.setModel(aModel);
            return;
	}
        switch(type) {
            case 0:
                medicalData(j);
                break;
            case 1:
                blankMedData();
                break;
            default:
                System.out.println("ERROR! displayMedData");
                break;
        }

        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while(en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new MyTableCellRenderer());
        }
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
    public void medicalData(int j) {
        int i = 0;
        ListIterator<Medical_Data> medata_list = meData.listIterator();
        //populating the tablemodel
        medObjects[0] = "Medical Info";
        medObjects[1] = null;
        medObjects[2] = null;
        medObjects[3] = null;
        medObjects[4] = null;
        aModel.addRow(medObjects);
        i++;
        while(medata_list.hasNext()) {
            Medical_Data med = medata_list.next();
            medObjects[0] = "";
            medObjects[1] = med.getCowTag();
            medObjects[2] = med.getInfo();
            medObjects[3] = med.getDate();
            medObjects[4] = med.getExpense();
            aModel.addRow(medObjects);
            i++;
	}
        fillMedVetTable(i, j);
    }
    public void updateMedical(Spreadsheet ss, java.util.List<Medical_Data> meData) {
        this.meData = meData;

        ss.aModel.getDataVector().removeAllElements();
        displayMedicalData(0);
        ss.aModel.fireTableDataChanged();
    }
    // Vet Visit
    public JPanel getVetSpreadsheet(int width, int type, int height) {
        this.width = width;
        this.height = height;
        this.bD = "Let Slide";

        table = new JTable(aModel);

        displayVetVisitData(type);
        init();

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        return panel;
    }
    public void blankVetData() {
        //populating the tablemodel
        vvObjects[0] = "";
        vvObjects[1] = "";
        vvObjects[2] = "";
        vvObjects[3] = "";
        vvObjects[4] = "";
        aModel.addRow(vvObjects);
        int i = 0;
        while(i<20) {
            vvObjects[0] = "";
            vvObjects[1] = "";
            vvObjects[2] = "";
            vvObjects[3] = "";
            vvObjects[4] = "";
            aModel.addRow(vvObjects);
            i++;
	}
    }
    public void displayVetVisitData(int type) {
        int j = 0;
        //setting the column name
        vvColumnNames[0] = "Title";
        vvColumnNames[1] = "Cow Tag";
	vvColumnNames[2] = "Info";
	vvColumnNames[3] = "Date";
	vvColumnNames[4] = "Price";
	aModel.setColumnIdentifiers(vvColumnNames);
	if(bD == null) {
            this.table.setModel(aModel);
            return;
	}
        switch(type) {
            case 0:
                vetVisitData(j);
                break;
            case 1:
                blankVetData();
                break;
            default:
                System.out.println("ERROR! displayVetData");
                break;
        }

        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while(en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new MyTableCellRenderer());
        }
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
    public void vetVisitData(int j) {
        int i = 0;
        ListIterator<Vet_Visit_Data> vvdata_list = vvData.listIterator();
        //populating the tablemodel
        vvObjects[0] = "Vet Visit Info";
        vvObjects[1] = null;
        vvObjects[2] = null;
        vvObjects[3] = null;
        vvObjects[4] = null;
        aModel.addRow(vvObjects);
        i++;
        while(vvdata_list.hasNext()) {
            Vet_Visit_Data vv = vvdata_list.next();
            vvObjects[0] = "";
            vvObjects[1] = vv.getCowTag();
            vvObjects[2] = vv.getInfo();
            vvObjects[3] = vv.getDate();
            vvObjects[4] = vv.getExpense();
            aModel.addRow(vvObjects);
            i++;
	}
        fillMedVetTable(i, j);
    }
    public void updateVetVisits(Spreadsheet ss, java.util.List<Vet_Visit_Data> vvData) {
        this.vvData = vvData;

        ss.aModel.getDataVector().removeAllElements();
        displayVetVisitData(0);
        ss.aModel.fireTableDataChanged();
    }
    // Feed Expenses
    public JPanel getFeedSpreadsheet(int width, int type, int height, java.util.List<Feed_Data> feedData) {
        this.width = width;
        this.height = height;
        this.bD = "Let Slide";

        table = new JTable(aModel);

        displayFeedData(type);
        init();

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        return panel;
    }
    public void blankFeedData() {
        //populating the tablemodel
        feedObjects[0] = "";
        feedObjects[1] = "";
        feedObjects[2] = "";
        feedObjects[3] = "";
        feedObjects[4] = "";
        aModel.addRow(feedObjects);
        int i = 0;
        while(i<20) {
            feedObjects[0] = "";
            feedObjects[1] = "";
            feedObjects[2] = "";
            feedObjects[3] = "";
            feedObjects[4] = "";
            feedObjects[5] = "";
            feedObjects[6] = "";
            aModel.addRow(feedObjects);
            i++;
	}
    }
    public void displayFeedData(int type) {
        //setting the column name
        feedColumnNames[0] = "Title";
        feedColumnNames[1] = "Herd ID";
	feedColumnNames[2] = "Hay Expense";
	feedColumnNames[3] = "Nuts Expense";
	feedColumnNames[4] = "Silage Expense";
	feedColumnNames[5] = "Month";
	feedColumnNames[6] = "Year";
	aModel.setColumnIdentifiers(feedColumnNames);
	if(bD == null) {
            this.table.setModel(aModel);
            return;
	}
        switch(type) {
            case 0:
                feedExpenseData();
                break;
            case 1:
                blankFeedData();
                break;
            default:
                System.out.println("ERROR! displayFeedData");
                break;
        }

        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while(en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new MyTableCellRenderer());
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(6).setPreferredWidth(50);

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
    public void feedExpenseData() {
        int i = 0;
        ListIterator<Feed_Data> feeddata_list = feedData.listIterator();
        //populating the tablemodel
        feedObjects[0] = "Feed Expense Info";
        feedObjects[1] = null;
        feedObjects[2] = null;
        feedObjects[3] = null;
        feedObjects[4] = null;
        feedObjects[5] = null;
        feedObjects[6] = null;
        aModel.addRow(feedObjects);
        i++;
        while(feeddata_list.hasNext()) {
            Feed_Data feed = feeddata_list.next();
            feedObjects[0] = "";
            feedObjects[1] = feed.getHerd_id();
            feedObjects[2] = feed.getF_hay_expense();
            feedObjects[3] = feed.getF_nuts_expense();
            feedObjects[4] = feed.getF_silage_expense();
            feedObjects[5] = feed.getMonth();
            feedObjects[6] = feed.getYear();
            aModel.addRow(feedObjects);
            i++;
	}
        fillFeedTable(i);
    }
    public void updateFeedExpenses(Spreadsheet ss, java.util.List<Feed_Data> feedData) {
        this.feedData = feedData;

        ss.aModel.getDataVector().removeAllElements();
        displayFeedData(0);
        ss.aModel.fireTableDataChanged();
    }
}

class MyTableCellRenderer extends DefaultTableCellRenderer {
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
        if(value == "Herd Info" || value == "Cow Info" || value == "Medical Info" || value == "Vet Visit Info" || value == "Feed Expense Info") {
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