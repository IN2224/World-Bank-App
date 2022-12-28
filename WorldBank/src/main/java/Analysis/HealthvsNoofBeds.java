package Analysis;


import java.util.ArrayList;

import ComputationStrategies.Context;
import ComputationStrategies.RatioStrategy;
import Fetchers.DataForCode;
import Viewers.ViewerMaker;
import statsVisualiser.gui.MainUI;

public class HealthvsNoofBeds implements Analysis {

	protected ArrayList<DataForCode> fetched;
	protected DataForCode results; 
	protected ArrayList<DataForCode> fetched2 = new ArrayList<DataForCode>();
	
	public HealthvsNoofBeds(ArrayList<DataForCode> fetched) {
		this.fetched = fetched;
		RatioStrategy ratio = new RatioStrategy(this.fetched);
		Context context = new Context(ratio);
		fetched2.add(context.executeStrategy());
		//fetched2 = ratio.getResult();
		this.fetched2.get(0).setCodeName("Ratio of Health expenditure vs No of beds");
	}
	
	public void printResult() {
		// TODO Auto-generated method stub
		System.out.println("The ratio of Government Expenditure on Health vs Number of Beds is: " + fetched2.get(0).getValues() + fetched.get(0).getYears());
		ViewerMaker make = new ViewerMaker();
		if(MainUI.getInstance().getGraph().contains("Bar Chart"))
		make.createBar(fetched2);
		if(MainUI.getInstance().getGraph().contains("Line Chart"))
		make.createLine(fetched2);
		if(MainUI.getInstance().getGraph().contains("Scatter Chart"))
		make.createScatter(fetched2);
		if(MainUI.getInstance().getGraph().contains("Report")) 
		make.createReport(fetched2);
	}

}