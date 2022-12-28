package Fetchers;

public class DataForYear {
	
	private int year2;
    private double data2; 

	public DataForYear(int year, double data) {
			super();
			year2 = year;
			data2 = data;
	}
	
	public int getYear() {
		return year2;
	}

	public void setYear(int year) {
		year2 = year;
	}

	public double getData() {
		return data2;
	}

	public void setData(double data) {
		data2 = data;
	}
	
	
	}
