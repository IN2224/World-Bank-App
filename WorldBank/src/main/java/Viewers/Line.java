package Viewers;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Fetchers.DataForCode;
import statsVisualiser.gui.MainUI;

public class Line implements Viewer {

	public void create(ArrayList<DataForCode> result) {
		XYSeries series1 = new XYSeries(result.get(0).getCodeName());
		XYSeriesCollection dataset = new XYSeriesCollection();
		String title;
		for (int i=result.get(0).getValues().size()-1;i>=0;i--) {
			series1.add(result.get(0).getYears().get(i),result.get(0).getValues().get(i));
		}
		dataset.addSeries(series1);
		
		if (result.size() >=2) {
		 XYSeries series2 = new XYSeries(result.get(1).getCodeName());
			 for (int i=result.get(1).getValues().size()-1;i>=0;i--) {
					series2.add(result.get(1).getYears().get(i),result.get(1).getValues().get(i));
				} 
			 dataset.addSeries(series2);
		}
		if (result.size() == 3) {
			 XYSeries series3 = new XYSeries(result.get(2).getCodeName());
			 for (int i=result.get(2).getValues().size()-1;i>=0;i--) {
					series3.add(result.get(2).getYears().get(i),result.get(2).getValues().get(i));
				} 
			 dataset.addSeries(series3);
		}

		JFreeChart chart = ChartFactory.createXYLineChart("Line Chart", "Year", "", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(
				new TextTitle("Line Chart", new Font("Serif", java.awt.Font.BOLD, 18)));

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		MainUI.west.add(chartPanel);
		MainUI.getInstance().getContentPane().add(MainUI.west, BorderLayout.WEST);
		MainUI.getInstance().pack();
	}


}
