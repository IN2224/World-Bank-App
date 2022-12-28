package Viewers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import Fetchers.DataForCode;
import statsVisualiser.gui.MainUI;

public class Pie implements Viewer{

	//private double average;
	ChartPanel chartPanel;

		
		public void create(ArrayList<DataForCode> result) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		 dataset.setValue(result.get(0).getCodeName(), result.get(0).getAverage());
		 dataset.setValue("All other purposes", 100-result.get(0).getAverage());
			
		 JFreeChart pieChart = ChartFactory.createPieChart("Average Government Expenditure on " + result.get(0).getCodeName()+" vs all other purposes",
				 dataset, true, true, false);
		chartPanel = new ChartPanel(pieChart);
		chartPanel.setPreferredSize(new Dimension(400,300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.blue);
		MainUI.west.add(chartPanel);
		MainUI.getInstance().getContentPane().add(MainUI.west, BorderLayout.WEST);
		MainUI.getInstance().pack();
		}	
		
		
}
