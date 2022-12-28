package statsVisualiser.gui;


import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CountrySelector extends Observer{
	
	private String countrySelected;
	JFrame mainframe2 = new JFrame(); 
	public CountrySelector(Object object) {
		countrySelected =(String) object;
	}
	
	public CountrySelector(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
		}
	
	
	HashMap<String,String> countryData = new HashMap<String,String>();
	
	
	public void readFile() {
	File file = new File("src\\dataAvailable.csv");
	Scanner scan;
	try {
		scan = new Scanner(file);
		scan.useDelimiter(",|\n");
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
            String[] lineSplit = line.split("\\s*,\\s*");
            countryData.put(lineSplit[0], lineSplit[1]);
		}
		
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	}
	
	if(countryData.get(countrySelected).equals("no data available")){
		JOptionPane.showMessageDialog(MainUI.getInstance(), "Data not available for the selected country at this time.");
	}
	
	}
	
	public boolean countryAvailable() {
		if(countryData.get(countrySelected).equals("data available")) {
		return true;
		}
		else {
			return false;
		}
		
	}

	public String getCountryCode() {
		String code = null;
		// TODO Auto-generated method stub
		if(countrySelected == "Canada") {
			 code = "can";
		}
		if(countrySelected == "Brazil") {
			 code = "BRA";
		}
		return code;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == MainUI.getInstance().countriesList) {
			   //CountrySelector data = new CountrySelector(MainUI.getInstance().countriesList.getSelectedItem());
			   System.out.println("Country Selected: ");
			  }
	}
	
	

}
