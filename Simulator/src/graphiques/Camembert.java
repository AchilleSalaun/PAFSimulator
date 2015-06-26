package graphiques;

import java.util.ArrayList;

import modele.Puit;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.Rotation;

/**
 * A simple demonstration application showing how to create a pie chart using data from a
 * {@link DefaultPieDataset}.
 *
 */
public class Camembert extends ApplicationFrame {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Puit> puits;


    public Camembert(final String title, ArrayList<Puit> puits) {

        super(title);
        
        this.puits = puits;

        // create a dataset...
        final PieDataset dataset = createSampleDataset();
        
        // create the chart...
        final JFreeChart chart = createChart(dataset);
        
        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }
    
    /**
     * Creates a sample dataset for the demo.
     * 
     * @return A sample dataset.
     */
    private PieDataset createSampleDataset() {
        
        final DefaultPieDataset result = new DefaultPieDataset();
        
        for(Puit puit : puits){
        	result.setValue(puit.getName(), puit.getCompteur());
        }
        
        return result;
        
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A chart.
     */
    private JFreeChart createChart(final PieDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createPieChart3D(
            this.getTitle(),  // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false
        );

        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        return chart;
        
    }

}