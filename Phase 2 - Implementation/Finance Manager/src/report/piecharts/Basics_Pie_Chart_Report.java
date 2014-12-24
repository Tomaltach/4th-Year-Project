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

import financemanager.datahandler.*;
import financemanager.sqlhandler.SQL_Handler;
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

public class Basics_Pie_Chart_Report extends JFrame {
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

    public Basics_Pie_Chart_Report(boolean show, String applicationTitle, String chartTitle) {
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
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Fertilizer: " + f_expense, f_expense);
        result.setValue("Gas: " + g_expense, g_expense);
        result.setValue("Labour: " + l_expense, l_expense);
        result.setValue("Lighting: " + li_expense, li_expense);
        result.setValue("Machinery: " + ma_expense, ma_expense);
        result.setValue("Mortgage: " + m_expense, m_expense);
        result.setValue("Rent: " + r_expense, r_expense);
        result.setValue("Slurry: " + s_expense, s_expense);
        result.setValue("Water: " + w_expense, w_expense);
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