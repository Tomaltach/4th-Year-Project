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

public class Basics_Bar_Chart_Report extends ApplicationFrame {
    private static final long serialVersionUID = 1L;
    SQL_Handler sql = new SQL_Handler();
    Panel_Elements gui = new Panel_Elements();

    List<Fertilizer_Data> fData;
    List<Gas_Data> gData;
    List<Labour_Data> lData;
    List<Lighting_Data> liData;
    List<Machinery_Data> maData;
    List<Mortgaged_Data> mData;
    List<Rented_Data> rData;
    List<Slurry_Data> sData;
    List<Water_Data> wData;

    Double f_expense = 0.0;
    Double g_expense = 0.0;
    Double l_expense = 0.0;
    Double li_expense = 0.0;
    Double ma_expense = 0.0;
    Double m_expense = 0.0;
    Double r_expense = 0.0;
    Double s_expense = 0.0;
    Double w_expense = 0.0;

    public Basics_Bar_Chart_Report(boolean show, String title) {
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
        // row keys...
        final String series1 = "Fertilizer: " + f_expense;
        final String series2 = "Gas: " + g_expense;
        final String series3 = "Labour: " + l_expense;
        final String series4 = "Lighting: " + li_expense;
        final String series5 = "Machinery: " + ma_expense;
        final String series6 = "Mortgaged: " + m_expense;
        final String series7 = "Rented: " + r_expense;
        final String series8 = "Slurry: " + s_expense;
        final String series9 = "Water: " + w_expense;

        // column keys...
        final String category1 = "Fertilizer";
        final String category2 = "Gas";
        final String category3 = "Labour";
        final String category4 = "Lighting";
        final String category5 = "Machinery";
        final String category6 = "Mortgaged";
        final String category7 = "Rented";
        final String category8 = "Slurry";
        final String category9 = "Water";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(f_expense, series1, category1);
        dataset.addValue(g_expense, series2, category2);
        dataset.addValue(l_expense, series3, category3);
        dataset.addValue(li_expense, series4, category4);
        dataset.addValue(ma_expense, series5, category5);
        dataset.addValue(m_expense, series6, category6);
        dataset.addValue(r_expense, series7, category7);
        dataset.addValue(s_expense, series8, category8);
        dataset.addValue(w_expense, series9, category9);

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
        calcFExpense();
        calcGExpense();
        calcLExpense();
        calcLiExpense();
        calcMaExpense();
        calcMExpense();
        calcRExpense();
        calcSExpense();
        calcWExpense();
    }
    private void selectAll() {
        try {
            fData = sql.select_fertilizer_expense();
            gData = sql.select_gas_expense();
            lData = sql.select_labour_expense();
            liData = sql.select_lighting_expense();
            maData = sql.select_machinery_expense();
            mData = sql.select_mortgaged_land();
            rData = sql.select_rented_land();
            sData = sql.select_slurry_expense();
            wData = sql.select_water_expense();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void calcFExpense() {
        ListIterator<Fertilizer_Data> data_list = fData.listIterator();
        //populating the tablemodel
        while(data_list.hasNext()) {
            Fertilizer_Data d = data_list.next();
            f_expense += d.getExpense();
	}
    }
    private void calcGExpense() {
        ListIterator<Gas_Data> data_list = gData.listIterator();
        //populating the tablemodel
        while(data_list.hasNext()) {
            Gas_Data d = data_list.next();
            g_expense += d.getExpense();
	}
    }
    private void calcLExpense() {
        ListIterator<Labour_Data> data_list = lData.listIterator();
        //populating the tablemodel
        while(data_list.hasNext()) {
            Labour_Data d = data_list.next();
            l_expense += d.getExpense();
	}
    }
    private void calcLiExpense() {
        ListIterator<Lighting_Data> data_list = liData.listIterator();
        //populating the tablemodel
        while(data_list.hasNext()) {
            Lighting_Data d = data_list.next();
            li_expense += d.getExpense();
	}
    }
    private void calcMaExpense() {
        ListIterator<Machinery_Data> data_list = maData.listIterator();
        //populating the tablemodel
        while(data_list.hasNext()) {
            Machinery_Data d = data_list.next();
            ma_expense += d.getExpense();
	}
    }
    private void calcMExpense() {
        ListIterator<Mortgaged_Data> data_list = mData.listIterator();
        //populating the tablemodel
        while(data_list.hasNext()) {
            Mortgaged_Data d = data_list.next();
            m_expense += d.getExpense();
	}
    }
    private void calcRExpense() {
        ListIterator<Rented_Data> data_list = rData.listIterator();
        //populating the tablemodel
        while(data_list.hasNext()) {
            Rented_Data d = data_list.next();
            r_expense += d.getExpense();
	}
    }
    private void calcSExpense() {
        ListIterator<Slurry_Data> data_list = sData.listIterator();
        //populating the tablemodel
        while(data_list.hasNext()) {
            Slurry_Data d = data_list.next();
            s_expense += d.getExpense();
	}
    }
    private void calcWExpense() {
        ListIterator<Water_Data> data_list = wData.listIterator();
        //populating the tablemodel
        while(data_list.hasNext()) {
            Water_Data d = data_list.next();
            w_expense += d.getExpense();
	}
    }

    public void setRef(JCheckBox ref) {
        gui.cbBasics = ref;
    }
    public void windowClosing(final WindowEvent evt){
        if(evt.getWindow() == this){
            dispose();
        }
    }
}