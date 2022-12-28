package Analysis;

import java.util.ArrayList;

import ComputationStrategies.APCStrategy;
import ComputationStrategies.Context;
import Fetchers.DataForCode;
import Viewers.ViewerMaker;
import statsVisualiser.gui.MainUI;

public class EducationvsHealthExpenditure implements Analysis {

	public ArrayList<DataForCode> fetched;
	public ArrayList<DataForCode> fetched2;
	
	public EducationvsHealthExpenditure(ArrayList<DataForCode> dataForCode) {
		this.fetched =dataForCode;
		// TODO Auto-generated constructor stub
		APCStrategy apc = new APCStrategy(this.fetched);
		Context context = new Context(apc);
		context.executeStrategy();
		fetched2=apc.getResult();
		this.fetched.get(0).setCodeName("Education");
		this.fetched.get(1).setCodeName("Health Expenditure");
	}

	public void  printResult() {
		// TODO Auto-generated method stub
		System.out.println("The Annual % Change of Government Expenditure on Education & Current Health Expenditure is: "+ fetched2.get(0).getValues() + fetched2.get(1).getValues());
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
