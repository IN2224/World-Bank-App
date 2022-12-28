package Analysis;

import java.util.ArrayList;

import ComputationStrategies.APCStrategy;
import ComputationStrategies.Context;
import Fetchers.DataForCode;
import Viewers.ViewerMaker;
import statsVisualiser.gui.MainUI;

public class AirvsForest implements Analysis {
  
	public ArrayList<DataForCode> fetched;
	public ArrayList<DataForCode> fetched2;
	
	public AirvsForest(ArrayList<DataForCode> fetched) {
		this.fetched = fetched;
		APCStrategy apc = new APCStrategy(this.fetched);
		Context context = new Context(apc);
		context.executeStrategy();
		fetched2=apc.getResult();
		this.fetched2.get(0).setCodeName("Air Pollution");
		this.fetched2.get(1).setCodeName("Forest Area");
	}

	public void printResult() {
		System.out.println("The Annual % change in PM2.5 Air Pollution and Forest Area is :" +fetched2.get(0).getValues()+fetched2.get(1).getValues());
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