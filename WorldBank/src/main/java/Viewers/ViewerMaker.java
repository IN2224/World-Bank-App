package Viewers;

import java.util.ArrayList;

import Fetchers.DataForCode;

public class ViewerMaker {
	
	private Viewer line;
	private Viewer pie;
	private Viewer bar;
	private Viewer report;
	private Viewer scatter;
	
	public ViewerMaker(){
		line = new Line();
		pie = new Pie();
		bar = new Bar();
		report = new Report();
		scatter = new Scatter();
	}

	public void createLine(ArrayList<DataForCode> result) {
		line.create(result);
	}
	public void createPie(ArrayList<DataForCode> result) {
		pie.create(result);
	}
	public void createBar(ArrayList<DataForCode> result) {
		bar.create(result);
	}
	public void createReport(ArrayList<DataForCode> result) {
		report.create(result);
	}
	public void createScatter(ArrayList<DataForCode> result) {
		scatter.create(result);
	}
}
