package ComputationStrategies;

import java.util.ArrayList;

import Fetchers.DataForCode;
import Fetchers.DataForYear;

public class APCStrategy implements Strategy{
	
	public DataForCode fetched2;
	private ArrayList<DataForCode> result;
	
	public APCStrategy(ArrayList<DataForCode> fetched) {
		this.result=fetched;
	}
	
	public DataForCode doProcessing() {
		ArrayList<DataForCode> results = new ArrayList<DataForCode>();
		for(int i = 0; i < this.result.size();i++) {
			ArrayList<DataForYear> temp = this.result.get(i).getData();
			DataForCode data = new DataForCode();
			for(int j = 1; j < temp.size();j++) {
				 if(temp.get(j-1).getData() == 0.0 && temp.get(j).getData() == 0) {
					data.addData(new DataForYear(temp.get(j).getYear()+1,0));
				}
				 else if (temp.get(j-1).getData() == 0.0 && temp.get(j).getData() != 0) {
					 data.addData(new DataForYear(temp.get(j).getYear()+1,temp.get(j).getData()));
				 }
				 else if (temp.get(j-1).getData() != 0.0 && temp.get(j).getData() == 0) {
					 data.addData(new DataForYear(temp.get(j).getYear()+1,temp.get(j-1).getData()));
				 }
				 else { 
					double end = temp.get(j).getData();
					double start = temp.get(j-1).getData();
					double apc = ((end-start)/start)*100;
					data.addData(new DataForYear(temp.get(j).getYear()+1,apc));
				}
			
			}
			data.setCode(this.result.get(i).getCode());
			results.add(data);
			}
		setResult(results); 
		return null;
	}
	
	public ArrayList<DataForCode> getResult() {
		return result;
	} 
	
	public void setResult(ArrayList<DataForCode> result) {
		this.result = result;
	} 

}
