package Fetchers;

import java.util.ArrayList;
import java.util.List;

public class DataForCode {
	
	private ArrayList<DataForYear> data2; 
	private String code2; 
	private double average;
	private int year; 
	private String codename;
	private List<String> graph;
	public DataForCode() {
		data2 = new ArrayList<DataForYear>();
	}
	
	public DataForCode(ArrayList<DataForYear> data, String code){
		super();
		data2 = data;
		code2 = code;
	}
	
	public ArrayList<Double> getValues(){
		ArrayList<Double> values = new ArrayList<Double>();
		for(int i = 0; i < data2.size();i++){
			values.add(data2.get(i).getData());
		}
		return values; 
	}
	public ArrayList<Integer> getYears(){
		ArrayList<Integer> years = new ArrayList<Integer>();
		for(int i = 0; i < data2.size();i++){
			years.add(data2.get(i).getYear());
		}
		return years; 
	}
	
	public ArrayList<DataForYear> getData(){
		return data2;
	}
	public void setData(ArrayList<DataForYear> data){
		data2 = data;
	}
	public String getCode(){
		return code2;
	}
	public void setCode(String code){
		code2 = code;
	}
	
	public void addData(DataForYear data){
		data2.add(data);
	}
	
	public int getSize(){
		return data2.size();
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double d) {
		this.average = d;
	}

	public void setCodeName(String name) {
		this.codename = name;
	}
	
	public String getCodeName() {
		return this.codename;
	}
	
	public void addYears(int year) {
		this.year= year;
	}
	
	public List<String> getGraph(){
		return this.graph;
	}
	
	public void setGraph(List<String> graph){
		this.graph = graph;
	}
}
