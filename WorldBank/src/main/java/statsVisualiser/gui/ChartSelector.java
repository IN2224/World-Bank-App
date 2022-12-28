package statsVisualiser.gui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


public class ChartSelector extends Observer{

	private List<String> graph = new ArrayList<String>();
	private String analysis;
	
	public ChartSelector(List<String> graph) {
		// TODO Auto-generated constructor stub
		this.graph = graph;
	} 

	public ChartSelector(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
		}
	
	public void create() {
		this.graph.remove("Select Viewer");
		        if (this.graph.contains("Pie Chart")) {
		        	if(MainUI.getInstance().methodsList.getSelectedItem().equals("Average Government Expenditure on Education") == false
		        			&& MainUI.getInstance().methodsList.getSelectedItem().equals("Average Forest Area") == false) {
		        		this.graph.remove("Pie Chart");
		        	JOptionPane.showMessageDialog(MainUI.getInstance(), "Pie Chart not applicable for this analysis. Please choose another viewer.");
		        }
		      }
		      if (this.graph.isEmpty()){
		    	  JOptionPane.showMessageDialog(MainUI.getInstance(), "Please choose a viewer type.");
		      }        
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == MainUI.getInstance().addView) {
			graph.add((String)MainUI.getInstance().viewsList.getSelectedItem());
		System.out.println(graph);
		}
		if (e.getSource() == MainUI.getInstance().removeView) {
			graph.remove((String)MainUI.getInstance().viewsList.getSelectedItem());
			System.out.println(graph);
		}
		//System.out.println("Chart Selected: " + MainUI.getInstance().viewsList.getSelectedItem());
	}

}
