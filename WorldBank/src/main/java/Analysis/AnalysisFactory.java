package Analysis;

import java.util.ArrayList;
import java.util.List;

import Fetchers.DataFetcher;
import Fetchers.DataForCode;

public class AnalysisFactory {

	String analysis;
	String type;
	Integer from2;
	Integer to2;
	String country;
	List<String> codes = new ArrayList<String>();
	List<String> graph;
	
	public AnalysisFactory(Object selectedItem, Object from, Object to, Object countrycode) {
		// TODO Auto-generated constructor stub
		analysis = (String) selectedItem;
		from2 = Integer.parseInt((String) from);
		to2 = Integer.parseInt((String) to);
		country = (String) countrycode;
	}
	
	public Analysis createAnalysis() {
		if (analysis == "Annual % Change in CO2 emissions & Energy Use & PM2.5 Air Pollution") {
			codes.add("EN.ATM.CO2E.PC");
			codes.add("EG.USE.PCAP.KG.OE");
			codes.add("EN.ATM.PM25.MC.M3");
			return new CO2vsEnergyvsAirPollution(fetchdata(codes));
		}
		if (analysis == "Annual % change in PM2.5 Air Pollution and Forest Area") {
			codes.add("EN.ATM.PM25.MC.M3");
			codes.add("AG.LND.FRST.ZS");
			return new AirvsForest(fetchdata(codes));
		}
		if (analysis == "Ratio of CO2 Emissions (metric tons per capita) to GDP Per Capita") {
			codes.add("EN.ATM.CO2E.PC");
			codes.add("NY.GDP.PCAP.CD");
			return new CO2vsGDP(fetchdata(codes));
		}
		if (analysis == "Average Forest Area") {
			codes.add("AG.LND.FRST.ZS");
			return new ForestArea(fetchdata(codes));
		}
		if (analysis == "Average Government Expenditure on Education") {
			codes.add("SE.XPD.TOTL.GD.ZS");
			return new GovernmentExpEducation(fetchdata(codes));
		}
		if (analysis == "Ratio of Current Health Expenditure to No. of Hospital Beds") {
			codes.add("SH.XPD.CHEX.PC.CD");
			codes.add("SH.MED.BEDS.ZS");
			return new HealthvsNoofBeds(fetchdata(codes));
		}
		if (analysis == "Current health expenditure (% of GDP) & Infant Mortality Rate") {
			codes.add("SH.XPD.CHEX.GD.ZS");
			//codes.add("SH.ACS.MONY.Q1.ZS");
			codes.add("SP.DYN.IMRT.IN");
			return new HealthExpInfantMortality(fetchdata(codes));
		}
		if (analysis == "Annual % Change of Government Expenditure on Education & Current Health Expenditure") {
			codes.add("SE.XPD.TOTL.GD.ZS");
			codes.add("SH.XPD.CHEX.GD.ZS");
			return new EducationvsHealthExpenditure(fetchdata(codes));
		}
		
		return null;
		
}
	public  ArrayList<DataForCode> fetchdata(List<String> code) {
		ArrayList<DataForCode> allcodesdata = new ArrayList<DataForCode>();
		for (int i = 0; i < code.size(); i++) {
			   DataFetcher datafetch = new DataFetcher(); //fetch data for each relevant code
			   DataForCode thiscodedata = datafetch.getData(country, from2,to2, code.get(i));
			   allcodesdata.add(thiscodedata);
		}
		return allcodesdata;
	
    }
}
