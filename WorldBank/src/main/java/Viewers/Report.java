package Viewers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Fetchers.DataForCode;
import statsVisualiser.gui.MainUI;

public class Report implements Viewer{


	public void create(ArrayList<DataForCode> result) {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		
		StringBuffer output1 = new StringBuffer();
		output1.append("Report "+ "\n" + "------------------------------" + "\n");
		for (int i=result.get(0).getValues().size()-1;i>=0;i--) {
			output1.append(result.get(0).getYears().get(i) + "\n");
			output1.append(result.get(0).getCodeName()+"->" +result.get(0).getValues().get(i) + "\n");
			if (result.size() >=2) output1.append(result.get(1).getCodeName()+"->" + result.get(1).getValues().get(i) + "\n");
			if (result.size() ==3) output1.append(result.get(2).getCodeName()+"->" + result.get(2).getValues().get(i) + "\n");
		}
		System.out.println(output1);

		report.setText(output1.toString());
		JScrollPane outputScrollPane = new JScrollPane(report);
		MainUI.west.add(outputScrollPane);
		MainUI.getInstance().getContentPane().add(MainUI.west, BorderLayout.NORTH);
		MainUI.getInstance().pack();
	}
	
}
