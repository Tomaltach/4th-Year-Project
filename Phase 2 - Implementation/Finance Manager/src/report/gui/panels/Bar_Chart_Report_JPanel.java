package report.gui.panels;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

import net.java.dev.designgridlayout.*;
import report.barcharts.*;

public class Bar_Chart_Report_JPanel extends JPanel implements ItemListener {
    Panel_Elements gui = new Panel_Elements();
    // Basics
    Basics_Bar_Chart_Report basicsChart;
    Fertilizer_Bar_Chart_Report fertilizerChart;
    Gas_Bar_Chart_Report gasChart;
    Labour_Bar_Chart_Report labourChart;
    Lighting_Bar_Chart_Report lightingChart;
    Machinery_Bar_Chart_Report machineryChart;
    Mortgaged_Bar_Chart_Report mortgagedChart;
    Rented_Bar_Chart_Report rentedChart;
    Slurry_Bar_Chart_Report slurryChart;
    Water_Bar_Chart_Report waterChart;
    // Beef
    Herd_Bar_Chart_Report herdChart;
    Feed_Bar_Chart_Report feedChart;
    Medical_Bar_Chart_Report medChart;
    Vet_Visit_Bar_Chart_Report vetChart;

    public Bar_Chart_Report_JPanel(){
        gui.barChartReportPanel = new JPanel();
        gui.baPanel = new JPanel();
        gui.bePanel = new JPanel();
        gui.panel = new JPanel();
        init();
    }
    public void init() {
        DesignGridLayout layout = new DesignGridLayout(gui.panel);
        showOptions(layout);
    }
    public JPanel barChartReportPanel() {
        return gui.barChartReportPanel;
    }
    public void showOptions(DesignGridLayout layout){
        setBasics();
        setBeef();

        layout.row().center().add(new JLabel("BAR CHARTS"));
        layout.row().grid().add(gui.baPanel)
                    .grid().empty()
                    .grid().add(gui.bePanel);

        gui.barChartReportPanel.add(gui.panel);
    }
    public void setBasics() {
        gui.baPanel.setBorder(BorderFactory.createTitledBorder("Basics"));
        gui.baPanel.setLayout(new GridLayout(0, 1));	//0 rows, 1 Column
        gui.cbBasics = new JCheckBox("Basics");
        gui.cbBasics.addItemListener(this);
        gui.cbFertilizer = new JCheckBox("Fertilizer");
        gui.cbFertilizer.addItemListener(this);
        gui.cbGas = new JCheckBox("Gas");
        gui.cbGas.addItemListener(this);
        gui.cbLabour = new JCheckBox("Labour");
        gui.cbLabour.addItemListener(this);
        gui.cbLighting = new JCheckBox("Lighting");
        gui.cbLighting.addItemListener(this);
        gui.cbMachinery = new JCheckBox("Machinery");
        gui.cbMachinery.addItemListener(this);
        gui.cbMortgaged = new JCheckBox("Mortgaged");
        gui.cbMortgaged.addItemListener(this);
        gui.cbRented = new JCheckBox("Rented");
        gui.cbRented.addItemListener(this);
        gui.cbSlurry = new JCheckBox("Slurry");
        gui.cbSlurry.addItemListener(this);
        gui.cbWater = new JCheckBox("Water");
        gui.cbWater.addItemListener(this);

        gui.baPanel.add(gui.cbBasics);
        gui.baPanel.add(gui.cbFertilizer);
        gui.baPanel.add(gui.cbGas);
        gui.baPanel.add(gui.cbLabour);
        gui.baPanel.add(gui.cbLighting);
        gui.baPanel.add(gui.cbMachinery);
        gui.baPanel.add(gui.cbMortgaged);
        gui.baPanel.add(gui.cbRented);
        gui.baPanel.add(gui.cbSlurry);
        gui.baPanel.add(gui.cbWater);
    }
    public void setBeef() {
        gui.bePanel.setBorder(BorderFactory.createTitledBorder("Beef"));
        gui.bePanel.setLayout(new GridLayout(0, 1));	//0 rows, 1 Column
        gui.cbHerd = new JCheckBox("Herd");
        gui.cbHerd.addItemListener(this);
        gui.cbFeed = new JCheckBox("Feed");
        gui.cbFeed.addItemListener(this);
        gui.cbMedical = new JCheckBox("Medical");
        gui.cbMedical.addItemListener(this);
        gui.cbVetVisit = new JCheckBox("Vet Visit");
        gui.cbVetVisit.addItemListener(this);

        gui.bePanel.add(gui.cbHerd);
        gui.bePanel.add(gui.cbFeed);
        gui.bePanel.add(gui.cbMedical);
        gui.bePanel.add(gui.cbVetVisit);
    }

    // Others
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        if(e.getStateChange() == ItemEvent.SELECTED){
            if(source == gui.cbBasics) {
                basicsChart = showBasicsReport();
            }
            else if(source == gui.cbFertilizer) {
                fertilizerChart = showFertilizerReport();
            }
            else if(source == gui.cbGas) {
                gasChart = showGasReport();
            }
            else if(source == gui.cbLabour) {
                labourChart = showLabourReport();
            }
            else if(source == gui.cbLighting) {
                lightingChart = showLightingReport();
            }
            else if(source == gui.cbMachinery) {
                machineryChart = showMachineryReport();
            }
            else if(source == gui.cbMortgaged) {
                mortgagedChart = showMortgagedReport();
            }
            else if(source == gui.cbRented) {
                rentedChart = showRentedReport();
            }
            else if(source == gui.cbSlurry) {
                slurryChart = showSlurryReport();
            }
            else if(source == gui.cbWater) {
                waterChart = showWaterReport();
            }
            else if(source == gui.cbHerd) {
                herdChart = showHerdReport();
            }
            else if(source == gui.cbFeed) {
                feedChart = showFeedReport();
            }
            else if(source == gui.cbMedical) {
                medChart = showMedicalReport();
            }
            else if(source == gui.cbVetVisit) {
                vetChart = showVetVisitReport();
            }
        }
        else if(e.getStateChange() == ItemEvent.DESELECTED) {
            if(source == gui.cbBasics) {
                basicsChart.dispose();
            }
            else if(source == gui.cbFertilizer) {
                fertilizerChart.dispose();
            }
            else if(source == gui.cbGas) {
                gasChart.dispose();
            }
            else if(source == gui.cbLabour) {
                labourChart.dispose();
            }
            else if(source == gui.cbLighting) {
                lightingChart.dispose();
            }
            else if(source == gui.cbMachinery) {
                machineryChart.dispose();
            }
            else if(source == gui.cbMortgaged) {
                mortgagedChart.dispose();
            }
            else if(source == gui.cbRented) {
                rentedChart.dispose();
            }
            else if(source == gui.cbSlurry) {
                slurryChart.dispose();
            }
            else if(source == gui.cbWater) {
                waterChart.dispose();
            }
            else if(source == gui.cbHerd) {
                herdChart.dispose();
            }
            else if(source == gui.cbFeed) {
                feedChart.dispose();
            }
            else if(source == gui.cbMedical) {
                medChart.dispose();
            }
            else if(source == gui.cbVetVisit) {
                vetChart.dispose();
            }
        }
    }
    // Basics
    public Basics_Bar_Chart_Report showBasicsReport() {
        boolean show = true;
        basicsChart = new Basics_Bar_Chart_Report(show, "Basic Expenses Overview");
        basicsChart.setPreferredSize(new Dimension(700, 600));
        basicsChart.pack();
        basicsChart.setVisible(true);
        basicsChart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        basicsChart.setRef(gui.cbBasics);
        return basicsChart;
    }
    public Fertilizer_Bar_Chart_Report showFertilizerReport() {
        boolean show = true;
        fertilizerChart = new Fertilizer_Bar_Chart_Report(show, "Ferilizer Expenses Overview");
        fertilizerChart.setPreferredSize(new Dimension(700, 600));
        fertilizerChart.pack();
        fertilizerChart.setVisible(true);
        fertilizerChart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fertilizerChart.setRef(gui.cbFertilizer);
        return fertilizerChart;
    }
    public Gas_Bar_Chart_Report showGasReport() {
        boolean show = true;
        gasChart = new Gas_Bar_Chart_Report(show, "Gas Expenses Overview");
        gasChart.setPreferredSize(new Dimension(700, 600));
        gasChart.pack();
        gasChart.setVisible(true);
        gasChart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        gasChart.setRef(gui.cbGas);
        return gasChart;
    }
    public Labour_Bar_Chart_Report showLabourReport() {
        boolean show = true;
        labourChart = new Labour_Bar_Chart_Report(show, "Labour Expenses Overview");
        labourChart.setPreferredSize(new Dimension(700, 600));
        labourChart.pack();
        labourChart.setVisible(true);
        labourChart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        labourChart.setRef(gui.cbLabour);
        return labourChart;
    }
    public Lighting_Bar_Chart_Report showLightingReport() {
        boolean show = true;
        lightingChart = new Lighting_Bar_Chart_Report(show, "Lighting Expenses Overview");
        lightingChart.setPreferredSize(new Dimension(700, 600));
        lightingChart.pack();
        lightingChart.setVisible(true);
        lightingChart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        lightingChart.setRef(gui.cbLighting);
        return lightingChart;
    }
    public Machinery_Bar_Chart_Report showMachineryReport() {
        boolean show = true;
        machineryChart = new Machinery_Bar_Chart_Report(show, "Machinery Expenses Overview");
        machineryChart.setPreferredSize(new Dimension(700, 600));
        machineryChart.pack();
        machineryChart.setVisible(true);
        machineryChart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        machineryChart.setRef(gui.cbMachinery);
        return machineryChart;
    }
    public Mortgaged_Bar_Chart_Report showMortgagedReport() {
        boolean show = true;
        mortgagedChart = new Mortgaged_Bar_Chart_Report(show, "Mortgaged Expenses Overview");
        mortgagedChart.setPreferredSize(new Dimension(700, 600));
        mortgagedChart.pack();
        mortgagedChart.setVisible(true);
        mortgagedChart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mortgagedChart.setRef(gui.cbMortgaged);
        return mortgagedChart;
    }
    public Rented_Bar_Chart_Report showRentedReport() {
        boolean show = true;
        rentedChart = new Rented_Bar_Chart_Report(show, "Rented Expenses Overview");
        rentedChart.setPreferredSize(new Dimension(700, 600));
        rentedChart.pack();
        rentedChart.setVisible(true);
        rentedChart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        rentedChart.setRef(gui.cbRented);
        return rentedChart;
    }
    public Slurry_Bar_Chart_Report showSlurryReport() {
        boolean show = true;
        slurryChart = new Slurry_Bar_Chart_Report(show, "Slurry Expenses Overview");
        slurryChart.setPreferredSize(new Dimension(700, 600));
        slurryChart.pack();
        slurryChart.setVisible(true);
        slurryChart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        slurryChart.setRef(gui.cbSlurry);
        return slurryChart;
    }
    public Water_Bar_Chart_Report showWaterReport() {
        boolean show = true;
        waterChart = new Water_Bar_Chart_Report(show, "Water Expenses Overview");
        waterChart.setPreferredSize(new Dimension(700, 600));
        waterChart.pack();
        waterChart.setVisible(true);
        waterChart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        waterChart.setRef(gui.cbWater);
        return waterChart;
    }
    // Beef
    public Herd_Bar_Chart_Report showHerdReport() {
        /*
         * show a comparison of all the herd total costs in a percentage of the
         * number of cattle in the herd as some herds may have more cattle than
         * others.
         * example: herd 1 = 10 cattle and £1000 expense while herd 2 = 20
         * cattle and £1500 expense. herd 2 would have less expense per cow
         */
        boolean show = true;
        herdChart = new Herd_Bar_Chart_Report(show, "Herd Expenses Overview");
        herdChart.setPreferredSize(new Dimension(700, 600));
        herdChart.pack();
        herdChart.setVisible(true);
        herdChart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        herdChart.setRef(gui.cbHerd);
        return herdChart;
    }
    public Feed_Bar_Chart_Report showFeedReport() {
        /*
         * show a comparison of all the feed total costs in a percentage of the
         * number of cattle in the herd as some herds may have more cattle than
         * others.
         * example: herd 1 = 10 cattle and £1000 expense while herd 2 = 20
         * cattle and £1500 expense. herd 2 would have less expense per cow
         */
        boolean show = true;
        feedChart = new Feed_Bar_Chart_Report(show, "Feed Expenses Overview");
        feedChart.setPreferredSize(new Dimension(700, 600));
        feedChart.pack();
        feedChart.setVisible(true);
        feedChart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        feedChart.setRef(gui.cbFeed);
        return feedChart;
    }
    public Medical_Bar_Chart_Report showMedicalReport() {
        /*
         * show a comparison of all medical expenses for a particular cow that
         * the user will choose. using autofill in the panel, the user will be
         * able to search and create a report for a certain cow.
         *
         * this may cause trouble in the save as I will most likely have to
         * create a create one for every cow once the save button is hit.
         */
        boolean show = true;
        medChart = new Medical_Bar_Chart_Report(show, "Medical Expenses Overview");
        medChart.setPreferredSize(new Dimension(700, 600));
        medChart.pack();
        medChart.setVisible(true);
        medChart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        medChart.setRef(gui.cbMedical);
        return medChart;
    }
    public Vet_Visit_Bar_Chart_Report showVetVisitReport() {
        /*
         * show a comparison of all vet expenses for a particular cow that
         * the user will choose. using autofill in the panel, the user will be
         * able to search and create a report for a certain cow.
         *
         * this may cause trouble in the save as I will most likely have to
         * create a create one for every cow once the save button is hit.
         */
        boolean show = true;
        vetChart = new Vet_Visit_Bar_Chart_Report(show, "Vet Visit Expenses Overview");
        vetChart.setPreferredSize(new Dimension(700, 600));
        vetChart.pack();
        vetChart.setVisible(true);
        vetChart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        vetChart.setRef(gui.cbVetVisit);
        return vetChart;
    }
}