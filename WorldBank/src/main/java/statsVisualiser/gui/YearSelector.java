package statsVisualiser.gui;


import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class YearSelector extends Observer{
	
	Integer from;
	Integer to;
	String analysisType;
	private Subject subject;
	
	//Pass the arguments for 'from','to', and 'analysis type' selected by user
	 public YearSelector(Object fromSelected, Object toSelected, Object analysisSelected) {
		from = Integer.parseInt((String) fromSelected);
		to = Integer.parseInt((String) toSelected);
		analysisType = (String) analysisSelected;
		
	} 
	
	
	 public YearSelector(Object selectedItem, Object selectedItem2) {
		// TODO Auto-generated constructor stub
		from = Integer.parseInt((String) selectedItem);
		to = Integer.parseInt((String) selectedItem2);
	} 

	public YearSelector(Subject subject) {
		// TODO Auto-generated constructor stub
		this.subject = subject;
		this.subject.attach(this);
		
	}


	public boolean validYearChoice() {
	// Make sure that 'from' year is before 'to' year
		boolean valid;
		if(to - from < 0) {
			JOptionPane.showMessageDialog(MainUI.getInstance(), "Please enter a year after " + from +" in the 'To' field.");
			valid = false;
		}
		else {
			valid =true;
		}
		return valid;
	}
	
	//Extract analysis types available and years available from csv file and store in hashmap
	
	
	
	public boolean readFile() {
		HashMap<String,List<Integer>> analysis = new HashMap<String,List<Integer>>();
	File file = new File("src\\analysisTypesByYear.csv");
	Scanner scan;
	try {
		scan = new Scanner(file);
		scan.useDelimiter(",|\n");
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
            String[] lineSplit = line.split("\\s*:\\s*");
            String[] yearsList = (lineSplit[1].split(","));
           
            List<Integer> yearsListInt = new ArrayList<Integer>();
            for(int i = 0;i < yearsList.length;i++)
            {
               yearsListInt.add(Integer.parseInt(yearsList[i]));
            }
            
            analysis.put(lineSplit[0], yearsListInt);
		}
		
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	}
	
	//Make sure the analysis for the selected years exists
	//if(analysis.containsKey(analysisType)) {
		
		if(analysis.get(analysisType).contains(from) && analysis.get(analysisType).contains(to)){
			System.out.println("Data for the selected years exists for "+ analysisType+from+to);
		}
		else {
			JOptionPane.showMessageDialog(MainUI.getInstance(), "Analysis for the selected years is not available. Only the following years are available "+ analysis.get(analysisType));
			return false;
		}
	//}
	return true;
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == MainUI.getInstance().toList) {
			System.out.println("To year Selected");
		}
		
	}
	
	}
	

