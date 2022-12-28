package ComputationStrategies;

import Fetchers.DataForCode;

public class Context {
	
	private Strategy strategy;

	public Context(Strategy strategy) {
		// TODO Auto-generated constructor stub
		this.strategy=strategy;
	}

	public DataForCode executeStrategy() {
		// TODO Auto-generated method stub
		return strategy.doProcessing();
	}


	
}
