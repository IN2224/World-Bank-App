package ComputationStrategies;

import java.util.ArrayList;

import Fetchers.DataForCode;

public class AverageStrategy implements Strategy {
	public DataForCode fetched2;
	ArrayList<Double> fetchedvalues2;
	public double average;
	public double sum = 0;
	
	public AverageStrategy(ArrayList<DataForCode> fetchedvalues) {
		// TODO Auto-generated constructor stub
		fetched2=fetchedvalues.get(0);
		fetchedvalues2 = fetched2.getValues();
		
	}

	public DataForCode doProcessing() {
		for(int i = 0; i < fetchedvalues2.size();i++) {
			sum += fetchedvalues2.get(i);
		}
		
		fetched2.setAverage(average = sum/fetchedvalues2.size()); 
		return fetched2;
	}
	
}
