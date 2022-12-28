package Analysis;


import java.util.ArrayList;

import ComputationStrategies.Context;
import ComputationStrategies.RatioStrategy;
import Fetchers.DataForCode;
import Viewers.ViewerMaker;
import statsVisualiser.gui.MainUI;

public class CO2vsGDP implements Analysis {

	protected ArrayList<DataForCode> fetched;
	protected ArrayList<DataForCode> fetched2 = new ArrayList<DataForCode>();
	protected DataForCode results; 
	
	public CO2vsGDP(ArrayList<DataForCode> fetched) {
		this.fetched = fetched;
		RatioStrategy ratio = new RatioStrategy(this.fetched);
		Context context = new Context(ratio);
		fetched2.add(context.executeStrategy());
		//fetched2 = ratio.getResult();
		this.fetched2.get(0).setCodeName("Ratio of CO2 vs GDP");
	}

	public void printResult() {
		System.out.println("The ratio of CO2 and GDP is: "+ fetched2.get(0).getValues() + fetched.get(0).getYears());
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