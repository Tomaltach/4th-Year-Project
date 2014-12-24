package report.barcharts;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JCheckBox;

import beefmodule.datahandler.*;
import beefmodule.sqlhandler.SQL_Beef_Handler;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import report.gui.panels.*;

public class Feed_Bar_Chart_Report extends ApplicationFrame {
    private static final long serialVersionUID = 1L;
    SQL_Beef_Handler sql = new SQL_Beef_Handler();
    Panel_Elements gui = new Panel_Elements();

    // create the dataset...
    final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    List<Feed_Data> fData;

    int herd_size = 0;
    Double feed_cost = 0.0;
    Double total = 0.0;

    public Feed_Bar_Chart_Report(boolean show, String title) {
        super(title);

        // Get data
        calculateExpenses();
	// This will create the dataset
        CategoryDataset dataset = createDataset();
	// based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, title);
        if(show == true) {
            // we put the chart into a panel
            ChartPanel chartPanel = new ChartPanel(chart);
            // default size
            chartPanel.setPreferredSize(new java.awt.Dimension(700, 600));
            // add it to our application
            setContentPane(chartPanel);
        }
    }
    /**
     * Returns a sample dataset.
     *
     * @return The dataset.
     */
    private CategoryDataset createDataset() {
        return dataset;
    }
    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset, String title) {
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            title,         // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        try {
            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File file = new File("bar_chart.png");
            ChartUtilities.saveChartAsPNG(file, chart, 500, 600, info);
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
            fData = sql.select_feed_data();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void getExpenses() {
        //populating the tablemodel
        int i = 0;
        int id = 0;
        int size = fData.size();
        boolean check = false;
        while(i<=size) {
            ListIterator<Feed_Data> data_list = fData.listIterator();

            while(data_list.hasNext()) {
                Feed_Data d = data_list.next();
                if(i == d.getHerd_id()) {
                    id = d.getHerd_id();
                    check = true;
                    getFeedExpense(i);
                    total += feed_cost;
                    herd_size++;
                }
                feed_cost = 0.0;
            }
            if(check == true) {
                addToBar(id);
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
    private void addToBar(int i) {
        // row keys...
        String series = "Herd " + i + ": " + total;
        // column keys...
        String category = "Herd " + i;
        dataset.addValue(total, series, category);
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