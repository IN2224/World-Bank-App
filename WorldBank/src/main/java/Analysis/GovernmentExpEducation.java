package Analysis;

import java.util.ArrayList;

import ComputationStrategies.AverageStrategy;
import ComputationStrategies.Context;
import Fetchers.DataForCode;
import Viewers.ViewerMaker;
import statsVisualiser.gui.MainUI;

public class GovernmentExpEducation implements Analysis {
	
	public ArrayList<DataForCode> results; 
	public double average;
	
	
	public GovernmentExpEducation(ArrayList<DataForCode> fetched) {
		results=fetched;
		Context context = new Context(new AverageStrategy(results));
		average=context.executeStrategy().getAverage();
		results.get(0).setCodeName("Education");
		
	}

	public void printResult() {
		System.out.println("The Average Government Expenditure on Education is:" + average); 
		ViewerMaker make = new ViewerMaker();
		if(MainUI.getInstance().getGraph().contains("Pie Chart"))
		make.createPie(results);
		if(MainUI.getInstance().getGraph().contains("Bar Chart"))
			make.createBar(results);
		if(MainUI.getInstance().getGraph().contains("Line Chart"))
			make.createLine(results);
		if(MainUI.getInstance().getGraph().contains("Scatter Chart"))
			make.createScatter(results);
		if(MainUI.getInstance().getGraph().contains("Report")) 
			make.createReport(results);
	}

}