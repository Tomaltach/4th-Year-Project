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

import financemanager.datahandler.*;
import financemanager.sqlhandler.SQL_Handler;
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

public class Machinery_Bar_Chart_Report extends ApplicationFrame {
    private static final long serialVersionUID = 1L;
    SQL_Handler sql = new SQL_Handler();
    Panel_Elements gui = new Panel_Elements();

    // create the dataset...
    final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    List<Machinery_Data> maData;

    public Machinery_Bar_Chart_Report(boolean show, String title) {
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
        getMaExpenses();
    }
    private void selectAll() {
        try {
            maData = sql.select_machinery_expense();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void getMaExpenses() {
        ListIterator<Machinery_Data> data_list = maData.listIterator();
        //populating the tablemodel
        while(data_list.hasNext()) {
            Machinery_Data d = data_list.next();
            // row keys...
            String series = d.getMonth() + "-" + d.getYear() + ": " + d.getExpense();
            // column keys...
            String category = d.getMonth() + "-" + d.getYear();

            dataset.addValue(d.getExpense(), series, category);
	}
    }

    public void setRef(JCheckBox ref) {
        gui.cbMachinery = ref;
    }
    public void windowClosing(final WindowEvent evt){
        if(evt.getWindow() == this){
            dispose();
        }
    }
}