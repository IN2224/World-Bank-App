/*************************************************
 * FALL 2022
 * EECS 3311 GUI SAMPLE CODE
 * ONLT AS A REFERENCE TO SEE THE USE OF THE jFree FRAMEWORK
 * THE CODE BELOW DOES NOT DEPICT THE DESIGN TO BE FOLLOWED 
 */

package statsVisualiser.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Analysis.AnalysisFactory;

//MainUI is a singleton class
public class MainUI extends JFrame implements ActionListener  {
	private static final long serialVersionUID = 1L;
	private static MainUI instance;
	public static JPanel west = new JPanel();
	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}
	JComboBox<String> countriesList;
	JComboBox<String> methodsList;
	JComboBox<String> fromList;
	JComboBox<String> toList;
	JComboBox<String> viewsList;
	JButton addView;
	JButton removeView;
	JButton recalculate;
	static JLabel label = new JLabel(new ImageIcon("src\\icon_worldbank.png"));
	
	private MainUI() {
		// Set window title
		super("Country Statistics");

		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		Vector<String> countriesNames = new Vector<String>();
		countriesNames.add("USA");
		countriesNames.add("Canada");
		countriesNames.add("France");
		countriesNames.add("China");
		countriesNames.add("Brazil");
		countriesNames.sort(null);
		countriesList = new JComboBox<String>(countriesNames);
		countriesList.addActionListener(this);
		
		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = new Vector<String>();
		for (int i = 2021; i >= 2010; i--) {
			years.add("" + i);
		}
		fromList = new JComboBox<String>(years);
		fromList.addActionListener(this);
		toList = new JComboBox<String>(years);
		toList.addActionListener(this);

		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);

		// Set bottom bar
		recalculate = new JButton("Recalculate");
		recalculate.addActionListener(this);
		

		JLabel viewsLabel = new JLabel("Available Views: ");

		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Select chart");
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Report");
		viewsList = new JComboBox<String>(viewsNames);
		viewsList.addActionListener(this);
		addView = new JButton("+");
		addView.addActionListener(this);
		removeView = new JButton("-");
		removeView.addActionListener(this);

		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		Vector<String> methodsNames = new Vector<String>();
		methodsNames.add("Annual % Change in CO2 emissions & Energy Use & PM2.5 Air Pollution");
		methodsNames.add("Annual % change in PM2.5 Air Pollution and Forest Area");
		methodsNames.add("Ratio of CO2 Emissions (metric tons per capita) to GDP Per Capita");
		methodsNames.add("Average Forest Area");
		methodsNames.add("Average Government Expenditure on Education");
		methodsNames.add("Ratio of Current Health Expenditure to No. of Hospital Beds");
		methodsNames.add("Current health expenditure (% of GDP) & Infant Mortality Rate");
		methodsNames.add("Annual % Change of Government Expenditure on Education & Current Health Expenditure");

		methodsList = new JComboBox<String>(methodsNames);
		methodsList.addActionListener(this);
		
		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		JPanel east = new JPanel();

		// Set charts region
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(2, 3));
		//createCharts(west);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
	}
	

	public static void main(String[] args)  {
		
		
		JFrame mainframe = MainUI.getInstance();
		label.setBounds(500, 500, 400, 400);
        label.setVisible(true);
        mainframe.getContentPane().add(new JLabel("World Bank App", JLabel.LEADING));
        mainframe.getContentPane().add(label);
		Login login = new Login(mainframe);
		login.readFile();
		mainframe.setSize(1200, 750);
		//mainframe.pack();  
		
		
	}
	
	private List<String> graph = new ArrayList<String>();
	
	public void actionPerformed(ActionEvent e) {
		Subject subject = new Subject();
		new CountrySelector(subject);
		new YearSelector(subject);
		new ChartSelector(subject);
		subject.setState(e);
		  if(e.getSource() == countriesList) {
			   CountrySelector data = new CountrySelector(countriesList.getSelectedItem());
			   data.readFile();
			  } 
		 if(e.getSource()==toList) {
			   YearSelector fromto = new YearSelector(fromList.getSelectedItem(), toList.getSelectedItem());
			   fromto.validYearChoice();
			  } 
		  if(e.getSource()==recalculate) {
			 MainUI.getInstance().getContentPane().remove(label);
			 YearSelector fromto = new YearSelector(fromList.getSelectedItem(), toList.getSelectedItem(),methodsList.getSelectedItem());
			 CountrySelector data = new CountrySelector(countriesList.getSelectedItem());
			 data.readFile(); //check if country data available 
			   String countrycode = data.getCountryCode(); //get short code for country
			   if (data.countryAvailable() == true && fromto.validYearChoice() == true && fromto.readFile() == true) {
			   AnalysisFactory factory = new AnalysisFactory((String)methodsList.getSelectedItem(), fromList.getSelectedItem(),toList.getSelectedItem(),countrycode);  
			   factory.createAnalysis().printResult(); //get relevant codes of analysis
			   ChartSelector charts = new ChartSelector(graph);
			   charts.create();
			   
		  }
		  
		  } 
		 if(e.getSource() == addView) {
			graph.add((String)viewsList.getSelectedItem());
			System.out.println(graph);
		  }
		  if(e.getSource() == removeView) {
			 graph.remove((String)viewsList.getSelectedItem());
			 System.out.println(graph);
		  }
		  
		  }


	public List<String> getGraph() {
		return graph;
	}


	public void setGraph(List<String> graph) {
		this.graph = graph;
	}
	}
	

