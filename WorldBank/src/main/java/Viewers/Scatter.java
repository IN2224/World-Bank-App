package Viewers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Fetchers.DataForCode;
import statsVisualiser.gui.MainUI;

public class Scatter implements Viewer {

	public void create(ArrayList<DataForCode> result) {
		 
	XYSeriesCollection dataset = new XYSeriesCollection();
	XYSeries series1 = new XYSeries(result.get(0).getCodeName());
	 for (int i=result.get(0).getValues().size()-1;i>=0;i--) {
		series1.add(result.get(0).getYears().get(i),result.get(0).getValues().get(i));
		
	 } 
	dataset.addSeries(series1);
	 if (result.size() >= 2) {
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
	
	XYPlot plot = new XYPlot();
	XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
	plot.setDataset(0, dataset);
	plot.setRenderer(0, itemrenderer1);
	DateAxis domainAxis = new DateAxis("Year");
	plot.setDomainAxis(domainAxis);
	plot.setRangeAxis(new NumberAxis("Ratio"));

	plot.mapDatasetToRangeAxis(0, 0);
	plot.mapDatasetToRangeAxis(1, 1); 

	//JFreeChart scatterChart = new JFreeChart("Scatter plot",
		//	new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
	 JFreeChart scatterChart = ChartFactory.createScatterPlot("Scatter", "X", "Y", dataset,
	            PlotOrientation.VERTICAL, true, true, false);

	ChartPanel chartPanel = new ChartPanel(scatterChart);
	chartPanel.setPreferredSize(new Dimension(400, 300));
	chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	chartPanel.setBackground(Color.white);
	MainUI.west.add(chartPanel);
	MainUI.getInstance().getContentPane().add(MainUI.west, BorderLayout.WEST);
	MainUI.getInstance().pack();
	}

	
}
