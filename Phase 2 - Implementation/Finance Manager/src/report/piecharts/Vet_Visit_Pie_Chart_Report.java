package report.piecharts;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.awt.event.WindowEvent;
import java.io.File;
import java.util.*;
import javax.swing.JFrame;

import beefmodule.datahandler.*;
import beefmodule.sqlhandler.*;
import javax.swing.JCheckBox;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import report.gui.panels.*;

public class Vet_Visit_Pie_Chart_Report extends JFrame {
    private static final long serialVersionUID = 1L;
    SQL_Beef_Handler sql = new SQL_Beef_Handler();
    Panel_Elements gui = new Panel_Elements();

    DefaultPieDataset result = new DefaultPieDataset();
    //PieDataset dataset;
    List<Vet_Visit_Data> vData;

    Double vet_cost = 0.0;
    Double total = 0.0;

    String[] data;

    public Vet_Visit_Pie_Chart_Report(boolean show, String applicationTitle, String chartTitle) {
        super(applicationTitle);
        // Get data
        calculateExpenses();
	// This will create the dataset
	PieDataset dataset = createDataset();
	// based on the dataset we create the chart
	JFreeChart chart = createChart(dataset, chartTitle);
        if(show == true) {
            // we put the chart into a panel
            ChartPanel chartPanel = new ChartPanel(chart);
            // default size
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
            // add it to our application
            setContentPane(chartPanel);
        }
    }
    /**
      * Creates a sample dataset
      */
    private PieDataset createDataset() {
        return result;
    }
    /**
      * Creates a chart
      */
    private JFreeChart createChart(PieDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createPieChart3D(title,          // chart title
                dataset,                // data
      	  	true,                   // include legend
      		true,
      		false);
    	PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
    	try {
            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File file = new File("pie_chart.png");
            ChartUtilities.saveChartAsPNG(file, chart, 400, 350, info);
        } catch(Exception e) {

        }
        return chart;
    }
    private void calculateExpenses() {
        selectAll();
        getData();
        getExpenses();
    }
    private void selectAll() {
        try {
            vData = sql.select_vet_visit_data();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void getData() {
        int i = 0;
        int rowSize = vData.size() + 1;
        data = new String[rowSize];
        ListIterator<Vet_Visit_Data> data_list = vData.listIterator();
        while(data_list.hasNext()) {
            Vet_Visit_Data vet = data_list.next();
            data[i] = vet.getCowTag();
            i++;
	}
        data[i] = "";
    }
    private void getExpenses() {
        //populating the tablemodel
        int i = 0;
        int j = 0;
        int k = -1;
        boolean frt = true;
        int size = vData.size();
        boolean check = false;
        boolean equals = false;
        String tag = data[i];
        String[] used = new String[size];
        // to load used array
        for(j=0; j<size; j++) {
            used[j] = "";
        }
        while(i<=size) {
            ListIterator<Vet_Visit_Data> data_list = vData.listIterator();

            while(data_list.hasNext()) {
                Vet_Visit_Data d = data_list.next();

                for(j=0; j<used.length-1; j++) {
                    String usedBefore = used[j];
                    if(!usedBefore.equals("") || frt == true) {
                        frt = false;
                        if(!tag.equals(usedBefore)) {
                            if(tag.equals(d.getCowTag())) {
                                getVetExpense(tag);
                                total += vet_cost;
                                vet_cost = 0.0;
                                check = true;
                            }
                        }
                    }
                }
                vet_cost = 0.0;
            }
            k = i;
            i++;
            if(k <= size) {
                for(j=0; j<size; j++) {
                    if(!used[j].equals(tag)) {
                        equals = true;
                    }
                    else {
                        equals = false;
                    }
                }
                if(i <= data.length-1) {
                    if(equals == true) {
                        if(check == true) {
                            addToPie(tag);
                        }
                        total = 0.0;
                        used[i-1] = tag;
                        tag = data[i];
                    }
                    else {
                        tag = data[i];
                        total = 0.0;
                        used[i-1] = "";
                    }
                }
            }
        }
    }private void getVetExpense(String i) {
        ListIterator<Vet_Visit_Data> data_list = vData.listIterator();

        while(data_list.hasNext()) {
            Vet_Visit_Data d = data_list.next();
            if(i.equals(d.getCowTag())) {
                vet_cost += d.getExpense();
            }
        }
    }
    private void addToPie(String i) {
        result.setValue("Cow Tag " + i + ": " + total, total);
    }

    public void setRef(JCheckBox ref) {
        gui.cbVetVisit = ref;
    }
    public void windowClosing(final WindowEvent evt){
        if(evt.getWindow() == this){
            dispose();
        }
    }
}