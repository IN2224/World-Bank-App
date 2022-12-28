package Fetchers;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;


public class DataFetcher {

		public static String createURL(String country, int from, int to, String code) {
			String url = String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%d:%d&format=json", country,code,from,to);
			//System.out.println(url);
			return url; 
		}
		public DataForCode getData(String country, Object from, Object to, String code) {
			int from2 = (Integer) from;
			int to2 = (Integer) to;
			String urlString = createURL(country, from2, to2, code);
			ArrayList<DataForYear> fulldataforcode = new ArrayList<DataForYear>();
			try {
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responseCode = conn.getResponseCode();
				if(responseCode == 200 ) {
					StringBuilder inline = new StringBuilder();
					Scanner scanner = new Scanner(url.openStream());
					while(scanner.hasNext()) {
						inline.append(scanner.nextLine());
					}
					scanner.close();
					JsonArray jsonArray = new JsonParser().parse(inline.toString()).getAsJsonArray();
					double data = 0; 
					int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
					for (int i1 = 0; i1 < sizeOfResults; i1++) {
						int year = jsonArray.get(1).getAsJsonArray().get(i1).getAsJsonObject().get("date").getAsInt();
						
						if (jsonArray.get(1).getAsJsonArray().get(i1).getAsJsonObject().get("value").isJsonNull())
							data = 0;
						else
							data = jsonArray.get(1).getAsJsonArray().get(i1).getAsJsonObject().get("value")
									.getAsDouble();
						DataForYear dataforyear = new DataForYear(year,data);
						fulldataforcode.add(dataforyear);
						System.out.println("Data fetch for "+ code + ", Year: "+ year +",Result:" + data);
					}
				}
			}
			catch (IOException e) {
				
			}
			
			DataForCode fetched = new DataForCode(fulldataforcode, code);
			return fetched;
			}
		
		

	}
