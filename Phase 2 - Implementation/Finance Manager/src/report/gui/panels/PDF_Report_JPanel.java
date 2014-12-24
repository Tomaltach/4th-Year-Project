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
import java.io.File;
import java.io.FileFilter;
import javax.swing.*;

import net.java.dev.designgridlayout.*;
import report.pdfs.*;

public class PDF_Report_JPanel extends JPanel implements ItemListener {
    Panel_Elements gui = new Panel_Elements();
    // Basics
    Basics_PDF_Report basicsPdf;
    Fertilizer_PDF_Report fertilizerPdf;
    Gas_PDF_Report gasPdf;
    Labour_PDF_Report labourPdf;
    Lighting_PDF_Report lightingPdf;
    Machinery_PDF_Report machineryPdf;
    Mortgaged_PDF_Report mortgagedPdf;
    Rented_PDF_Report rentedPdf;
    Slurry_PDF_Report slurryPdf;
    Water_PDF_Report waterPdf;
    // Beef
    Herd_PDF_Report herdPdf;
    Feed_PDF_Report feedPdf;
    Medical_PDF_Report medPdf;
    Vet_Visit_PDF_Report vetPdf;

    public PDF_Report_JPanel(){
        gui.pdfReportPanel = new JPanel();
        gui.baPanel = new JPanel();
        gui.bePanel = new JPanel();
        gui.panel = new JPanel();
        init();
    }
    public void init() {
        DesignGridLayout layout = new DesignGridLayout(gui.panel);
        showOptions(layout);
    }
    public JPanel pdfReportPanel() {
        return gui.pdfReportPanel;
    }
    public void showOptions(DesignGridLayout layout){
        setBasics();
        setBeef();
        
        layout.row().center().add(new JLabel("PDF REPORTS"));
        layout.row().grid().add(gui.baPanel)
                    .grid().empty()
                    .grid().add(gui.bePanel);

        gui.pdfReportPanel.add(gui.panel);
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
        String path = "Reports/";
        String basic = "Basic Reports/";
        String beef = "Beef Reports/";
        if(e.getStateChange() == ItemEvent.SELECTED){
            if(source == gui.cbBasics) {
                showBasicsReport();
                path += basic + "Basic Overview Reports";
            }
            else if(source == gui.cbFertilizer) {
                showFertilizerReport();
                path += basic + "Fertilizer Reports";
            }
            else if(source == gui.cbGas) {
                showGasReport();
                path += basic + "Gas Reports";
            }
            else if(source == gui.cbLabour) {
                showLabourReport();
                path += basic + "Labour Reports";
            }
            else if(source == gui.cbLighting) {
                showLightingReport();
                path += basic + "Lighting Reports";
            }
            else if(source == gui.cbMachinery) {
                showMachineryReport();
                path += basic + "Machinery Reports";
            }
            else if(source == gui.cbMortgaged) {
                showMortgagedReport();
                path += basic + "Mortgaged Reports";
            }
            else if(source == gui.cbRented) {
                showRentedReport();
                path += basic + "Rented Reports";
            }
            else if(source == gui.cbSlurry) {
                showSlurryReport();
                path += basic + "Slurry Reports";
            }
            else if(source == gui.cbWater) {
                showWaterReport();
                path += basic + "Water Reports";
            }
            else if(source == gui.cbHerd) {
                showHerdReport();
                path += beef + "Herd Reports";
            }
            else if(source == gui.cbFeed) {
                showFeedReport();
                path += beef + "Feed Reports";
            }
            else if(source == gui.cbMedical) {
                showMedicalReport();
                path += beef + "Medical Reports";
            }
            else if(source == gui.cbVetVisit) {
                showVetVisitReport();
                path += beef + "Vet Visit Reports";
            }
        }
        openPDF(path);
    }
    public void openPDF(String path) {
        try {
            File pdfFile = lastFileModified(path);
            //File pdfFile = new File(path);
            if(pdfFile.exists()) {
 		if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
		}
                else {
                    System.out.println("Awt Desktop is not supported!");
		}
            }
            else {
		System.out.println("File is not exists!");
            }
            System.out.println("Done");
        }
        catch(Exception ex) {
            ex.printStackTrace();
	}
    }
    public File lastFileModified(String dir) {
	File fl = new File(dir);
	File[] files = fl.listFiles(new FileFilter() {			
            public boolean accept(File file) {
		return file.isFile();
            }
	});
	long lastMod = Long.MIN_VALUE;
	File choise = null;
	for(File file : files) {
            if(file == null) {
                // do nothing
            }
            else if(file.lastModified() > lastMod) {
		choise = file;
		lastMod = file.lastModified();
            }
	}
	return choise;
    }
    // Basics
    public void showBasicsReport() {
        basicsPdf = new Basics_PDF_Report();
    }
    public void showFertilizerReport() {
        fertilizerPdf = new Fertilizer_PDF_Report();
    }
    public void showGasReport() {
        gasPdf = new Gas_PDF_Report();
    }
    public void showLabourReport() {
        labourPdf = new Labour_PDF_Report();
    }
    public void showLightingReport() {
        lightingPdf = new Lighting_PDF_Report();
    }
    public void showMachineryReport() {
        machineryPdf = new Machinery_PDF_Report();
    }
    public void showMortgagedReport() {
        mortgagedPdf = new Mortgaged_PDF_Report();
    }
    public void showRentedReport() {
        rentedPdf = new Rented_PDF_Report();
    }
    public void showSlurryReport() {
        slurryPdf = new Slurry_PDF_Report();
    }
    public void showWaterReport() {
        waterPdf = new Water_PDF_Report();
    }
    // Beef
    public void showHerdReport() {
        /*
         * show a comparison of all the herd total costs in a percentage of the
         * number of cattle in the herd as some herds may have more cattle than
         * others.
         * example: herd 1 = 10 cattle and £1000 expense while herd 2 = 20
         * cattle and £1500 expense. herd 2 would have less expense per cow
         */
        herdPdf = new Herd_PDF_Report();
    }
    public void showFeedReport() {
        /*
         * show a comparison of all the feed total costs in a percentage of the
         * number of cattle in the herd as some herds may have more cattle than
         * others.
         * example: herd 1 = 10 cattle and £1000 expense while herd 2 = 20
         * cattle and £1500 expense. herd 2 would have less expense per cow
         */
        feedPdf = new Feed_PDF_Report();
    }
    public void showMedicalReport() {
        /*
         * show a comparison of all medical expenses for a particular cow that
         * the user will choose. using autofill in the panel, the user will be
         * able to search and create a report for a certain cow.
         *
         * this may cause trouble in the save as I will most likely have to
         * create a create one for every cow once the save button is hit.
         */
        medPdf = new Medical_PDF_Report();
    }
    public void showVetVisitReport() {
        /*
         * show a comparison of all vet expenses for a particular cow that
         * the user will choose. using autofill in the panel, the user will be
         * able to search and create a report for a certain cow.
         *
         * this may cause trouble in the save as I will most likely have to
         * create a create one for every cow once the save button is hit.
         */
        vetPdf = new Vet_Visit_PDF_Report();
    }
}