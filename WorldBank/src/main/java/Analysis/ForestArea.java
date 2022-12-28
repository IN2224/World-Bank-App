package Analysis;

import java.util.ArrayList;

import ComputationStrategies.AverageStrategy;
import ComputationStrategies.Context;
import Fetchers.DataForCode;
import Viewers.ViewerMaker;
import statsVisualiser.gui.MainUI;

public class ForestArea implements Analysis {
	public ArrayList<DataForCode> results; 
	public double average;
	
	public ForestArea(ArrayList<DataForCode> fetched) {
		results=fetched;
		Context context = new Context(new AverageStrategy(results));
		results.get(0).setCodeName("Forest Area");
		average=context.executeStrategy().getAverage();
	}
	
	public void printResult() {
		System.out.println("The Average Forest Area is:" + average); 
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