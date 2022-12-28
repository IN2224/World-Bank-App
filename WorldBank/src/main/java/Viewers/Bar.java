package Viewers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;

import Fetchers.DataForCode;
import statsVisualiser.gui.MainUI;


public class Bar implements Viewer {

	public void create(ArrayList<DataForCode> result) {
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	for (int i=result.get(0).getValues().size()-1;i>=0;i--) {
		dataset.setValue(result.get(0).getValues().get(i),result.get(0).getCodeName(),result.get(0).getYears().get(i));
	}
	//DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
	if (result.size() >=2) {
		//DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
			 for (int i=result.get(1).getValues().size()-1;i>=0;i--) {
					dataset.setValue(result.get(1).getValues().get(i),result.get(1).getCodeName(),result.get(1).getYears().get(i));
				} 
		}
	//DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
		if (result.size() == 3) {
			//DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
			 for (int i=result.get(2).getValues().size()-1;i>=0;i--) {
				 dataset.setValue(result.get(2).getValues().get(i),result.get(2).getCodeName(),result.get(2).getYears().get(i));
				} 
		}

	CategoryPlot plot = new CategoryPlot();
	BarRenderer barrenderer1 = new BarRenderer();
	BarRenderer barrenderer2 = new BarRenderer();
	BarRenderer barrenderer3 = new BarRenderer();
	plot.setDataset(0, dataset);
	plot.setRenderer(0, barrenderer1);
	plot.setRenderer(0, barrenderer2);
	plot.setRenderer(0, barrenderer3);
	
	CategoryAxis domainAxis = new CategoryAxis("Year");
	//plot.setDomainAxis(domainAxis);
	//plot.setRangeAxis(new NumberAxis());
	//plot.mapDatasetToRangeAxis(0, 0);

	//JFreeChart barChart = new JFreeChart("Result","Year","Values"
		//	new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
	JFreeChart barchart=ChartFactory.createBarChart(  
	        "Bar Chart","Year","Value",dataset,PlotOrientation.VERTICAL,true,true,false  
	       );  
	ChartPanel chartPanel = new ChartPanel(barchart);
	chartPanel.setPreferredSize(new Dimension(400, 300));
	chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	chartPanel.setBackground(Color.white);
	MainUI.west.add(chartPanel);
	MainUI.getInstance().getContentPane().add(MainUI.west, BorderLayout.WEST);
	MainUI.getInstance().pack();
	
	}

}
