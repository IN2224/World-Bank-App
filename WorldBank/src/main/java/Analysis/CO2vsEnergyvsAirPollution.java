package Analysis;

import java.util.ArrayList;

import ComputationStrategies.APCStrategy;
import ComputationStrategies.Context;
import Fetchers.DataForCode;
import Viewers.ViewerMaker;
import statsVisualiser.gui.MainUI;

public class CO2vsEnergyvsAirPollution implements Analysis {

	public ArrayList<DataForCode> fetched;
	public ArrayList<DataForCode> fetched2;
	
	public CO2vsEnergyvsAirPollution(ArrayList<DataForCode> fetched) {
		this.fetched = fetched;
		APCStrategy apc = new APCStrategy(this.fetched);
		Context context = new Context(apc);
		context.executeStrategy();
		fetched2=apc.getResult();
		this.fetched2.get(0).setCodeName("Co2 emissions");
		this.fetched2.get(1).setCodeName("Energy Use");
		this.fetched2.get(2).setCodeName("Air Pollution");
	}
	
	public void printResult() {
		System.out.println("The Annual % Change in CO2 emissions & Energy Use & PM2.5 Air Pollution is: " +fetched2.get(0).getValues()+fetched2.get(1).getValues()+fetched2.get(2).getValues());
	//3-series (Bar, 
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