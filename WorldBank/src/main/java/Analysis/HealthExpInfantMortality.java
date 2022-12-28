package Analysis;

import java.util.ArrayList;

import Fetchers.DataForCode;
import Viewers.ViewerMaker;
import statsVisualiser.gui.MainUI;

public class HealthExpInfantMortality implements Analysis{

	
	public ArrayList<DataForCode> fetched;
	protected DataForCode results;
	protected DataForCode result2; 
	
	public HealthExpInfantMortality(ArrayList<DataForCode> arrayList) {
		this.fetched = arrayList;
		results = fetched.get(0);
		result2 = fetched.get(1);
		this.fetched.get(0).setCodeName("Health expenditure");
		this.fetched.get(1).setCodeName("Infant Mortality Rate");
	}

	public void printResult() {
		// TODO Auto-generated method stub
		System.out.println("Health expenditure (% of GDP): " + results.getValues() +"Infant Mortality Rate is: " + result2.getValues());
		ViewerMaker make = new ViewerMaker();
		if(MainUI.getInstance().getGraph().contains("Bar Chart"))
			make.createBar(fetched);
		if(MainUI.getInstance().getGraph().contains("Line Chart"))
			make.createLine(fetched);
		if(MainUI.getInstance().getGraph().contains("Scatter Chart"))
			make.createScatter(fetched);
		if(MainUI.getInstance().getGraph().contains("Report")) 
			make.createReport(fetched);
	}

}
