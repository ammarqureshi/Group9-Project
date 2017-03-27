import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;

import java.awt.BasicStroke;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends ApplicationFrame {

	private DefaultCategoryDataset dataset;
	private JFreeChart lineChart;

	public LineChart(String applicationTitle, String chartTitle) {
		super(applicationTitle);
		lineChart = ChartFactory.createLineChart(chartTitle, "Project Preference Received", "Number of groups",
				createDataset(), PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		setContentPane(chartPanel);
		//CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
		//plot.getRenderer().setSeriesStroke(1, new BasicStroke(2.0f));
	}

	private DefaultCategoryDataset createDataset() {
		dataset = new DefaultCategoryDataset();
		dataset.addValue(0, "Groups", "1");
		dataset.addValue(0, "Groups", "2");
		dataset.addValue(0, "Groups", "3");
		dataset.addValue(0, "Groups", "4");
		dataset.addValue(0, "Groups", "5");
		dataset.addValue(0, "Groups", "6");
		dataset.addValue(0, "Groups", "7");
		dataset.addValue(0, "Groups", "8");
		dataset.addValue(0, "Groups", "9");
		dataset.addValue(0, "Groups", "10");
		return dataset;
	}

	public void editDataset(int project) {
		if (project <= 10) {
			String projString = "" + project;
			dataset.incrementValue(1, "Groups", projString);
		}
	}


	public void displayLineChart(LineChart happyLine) {
		happyLine.pack();
		RefineryUtilities.centerFrameOnScreen(happyLine);
		happyLine.setVisible(true);
	}

	public void saveAsPNG() {
		File lineChartFile = new File("LineChart.png");
			try {
				ChartUtilities.saveChartAsPNG(lineChartFile, lineChart, 640, 480);
			} catch (IOException e) {
				System.err.print("WHAT THE FUCK?!");
				e.printStackTrace();
			}
	}
}
