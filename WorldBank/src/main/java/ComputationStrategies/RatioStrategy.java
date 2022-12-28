package ComputationStrategies;

import java.util.ArrayList;

import Fetchers.DataForCode;
import Fetchers.DataForYear;

public class RatioStrategy implements Strategy{

	private DataForCode results;
	private ArrayList<DataForCode> fetched;

	public RatioStrategy(ArrayList<DataForCode> fetched) {
		this.fetched = fetched;
	}

	public DataForCode doProcessing() {
		
		DataForCode results = new DataForCode();
		ArrayList<DataForYear> data1 = this.fetched.get(0).getData();
		ArrayList<DataForYear> data2 = fetched.get(1).getData();
		
		for(int i = 0; i < fetched.get(0).getSize();i++) {
			double ratio = data1.get(i).getData()/data2.get(i).getData();
			DataForYear data = new DataForYear(data1.get(i).getYear(),ratio);
			results.addData(data);
		}
		this.fetched.add(this.results);
		this.results = results; 
		
		return this.results;
	}

	public ArrayList<DataForCode> getResult() {
		// TODO Auto-generated method stub
		return this.fetched;
	}

	public void setResult(ArrayList<DataForCode> fetched) {
		this.fetched = fetched;
	}
}
