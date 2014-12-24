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

public class Herd_Pie_Chart_Report extends JFrame {
    private static final long serialVersionUID = 1L;
    SQL_Beef_Handler sql = new SQL_Beef_Handler();
    Panel_Elements gui = new Panel_Elements();

    DefaultPieDataset result = new DefaultPieDataset();
    //PieDataset dataset;
    List<Herd_Data> hData;
    List<Feed_Data> fData;
    List<Medical_Data> mData;
    List<Vet_Visit_Data> vData;
    List<CowTag_Data> cData;

    int herd_size = 0;
    Double feed_cost = 0.0;
    Double med_cost = 0.0;
    Double vet_cost = 0.0;
    Double total = 0.0;

    public Herd_Pie_Chart_Report(boolean show, String applicationTitle, String chartTitle) {
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
        getExpenses();
    }
    private void selectAll() {
        try {
            hData = sql.select_herd_data();
            fData = sql.select_feed_data();
            mData = sql.select_medical_data();
            vData = sql.select_vet_visit_data();
            cData = sql.select_herd_linker_data();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void getExpenses() {
        //populating the tablemodel
        int i = 0;
        int size = hData.size();
        boolean check = false;
        while(i<=size) {
            ListIterator<Herd_Data> data_list = hData.listIterator();
        
            while(data_list.hasNext()) {
                Herd_Data d = data_list.next();
                if(i == d.getHerd_id()) {
                    check = true;
                    getFeedExpense(i);
                    String cowtag = getCowTag(i);
                    getMedExpense(cowtag);
                    getVetExpense(cowtag);
                    total += feed_cost;
                    total += med_cost;
                    total += vet_cost;
                    herd_size++;
                }
                feed_cost = 0.0;
                med_cost = 0.0;
                vet_cost = 0.0;
            }
            if(check == true) {
                addToPie(i);
            }
            herd_size = 0;
            total = 0.0;
            i++;
        }
    }
    private void getFeedExpense(int i) {
        ListIterator<Feed_Data> data_list = fData.listIterator();

        while(data_list.hasNext()) {
            Feed_Data d = data_list.next();
            if(i == d.getHerd_id()) {
                feed_cost += d.getF_hay_expense();
                feed_cost += d.getF_nuts_expense();
                feed_cost += d.getF_silage_expense();
            }
        }
    }
    private String getCowTag(int i) {
        ListIterator<CowTag_Data> data_list = cData.listIterator();
        while(data_list.hasNext()) {
            CowTag_Data d = data_list.next();
            if(i == d.getHerd_id()) {
                return d.getCowTag();
            }
        }
        return "";
    }
    private void getMedExpense(String cowTag) {
        ListIterator<Medical_Data> data_list = mData.listIterator();
        while(data_list.hasNext()) {
            Medical_Data d = data_list.next();
            if(cowTag.equals(d.getCowTag())) {
                med_cost += d.getExpense();
            }
        }
    }
    private void getVetExpense(String cowTag) {
        ListIterator<Vet_Visit_Data> data_list = vData.listIterator();

        while(data_list.hasNext()) {
            Vet_Visit_Data d = data_list.next();
            if(cowTag.equals(d.getCowTag())) {
                vet_cost += d.getExpense();
            }
        }
    }
    private void addToPie(int i) {
        result.setValue("Herd " + i + ": " + total, total);
    }

    public void setRef(JCheckBox ref) {
        gui.cbHerd = ref;
    }
    public void windowClosing(final WindowEvent evt){
        if(evt.getWindow() == this){
            dispose();
        }
    }
}