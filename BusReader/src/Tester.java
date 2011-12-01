import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Tester {

	static ArrayList<BusStop> busStops = new ArrayList<BusStop>();
	static ArrayList<Bus> buses = new ArrayList<Bus>();
	static ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
	static ArrayList<Integer> idList = new ArrayList<Integer>();

	private static final int RED = 0;
	private static final int BLUE = 1;

	private static JSONObject json = null;
	private static String ss = null;

	private static JSONParser jParser = new JSONParser();

	public static void main(String[] args) throws MalformedURLException {

		elCommanderI commander = new elTest();

		BusStop temp = new BusStop(2, commander);
		temp.addPointContainer(-84.405223, 33.780159, -84.402691, 33.778964);
		temp.addPointContainer(-84.405223, 33.780159, -84.403614, 33.777769);
		busStops.add(temp);

		temp = new BusStop(3, commander);
		temp.addPointContainer(-84.402112, 33.778964, -84.39885, 33.777716);
		busStops.add(temp);

		temp = new BusStop(4, commander);
		temp.addPointContainer(-84.398099, 33.779499, -84.395159, 33.776699);
		busStops.add(temp);

		temp = new BusStop(5, commander);
		temp.addPointContainer(-84.394644, 33.777876, -84.391576, 33.776467);
		busStops.add(temp);

		temp = new BusStop(6, commander);
		temp.addPointContainer(-84.392563, 33.776164, -84.39134, 33.77356);
		busStops.add(temp);

		temp = new BusStop(7, commander);
		temp.addPointContainer(-84.393014, 33.773114, -84.390846, 33.769101);
		busStops.add(temp);

		temp = new BusStop(8, commander);
		temp.addPointContainer(-84.399515, 33.773899, -84.394516, 33.771544);
		busStops.add(temp);

		temp = new BusStop(9, commander);
		temp.addPointContainer(-84.403313, 33.777002, -84.400889, 33.774202);
		busStops.add(temp);

		while (true) {

			try {
				ss = jParser
						.readJsonFromUrl("http://walkpath.cip.gatech.edu/bus_position.php");

//				System.out.println(ss);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			System.out.println("Text: " + ss);

			jParser.parseString(ss);

			JSONObject obj = jParser.getString();

			jsonObjects = jParser.getBuses();

			String color;
			PointD currentPoint;
			int colorId = 0;
			int id;
			
			Bus bus;

			for (int x = 0; x < jsonObjects.size(); x++) {

				color = (String) jsonObjects.get(x).get("color");

				if (color.compareTo("red") == 0)
					colorId = RED;

				else if (color.compareTo("blue") == 0)
					colorId = BLUE;

				id = Integer.parseInt((String) jsonObjects.get(x).get("id"));
			
				int found = -1;
				
				for(int c = 0; c < buses.size(); c++){
					
					if(buses.get(c).id == id){
						found = c;
					}
				}
				
				currentPoint = new PointD((Double) jsonObjects.get(x)
						.get("lng"), (Double) jsonObjects.get(x).get("lat"));
				
				if(found < 0 ){

					bus = new Bus(id, colorId);
					bus.setCurrentPoint(currentPoint);
					buses.add(bus);	
				}
				
				else{
					buses.get(found).setCurrentPoint(currentPoint);
				}

				idList.add(id);		
			}
			
			for(int i = 0; i < buses.size(); i++){
				
				if(!(idList.contains((Object) buses.get(i).id))){
					buses.remove(i);
				}
				
			}

			for (int i = 0; i < busStops.size(); i++) {

				for (int c = 0; c < buses.size(); c++) {

					if (busStops.get(i).inBusStop(
							buses.get(c).getCurrentPoint())) {
						// commander.pinWriteDigital(busStops.get(i).getPort(),
						// true);
						System.out.println("Bus: " + buses.get(c).getId()
								+ " is at bus stop: "
								+ busStops.get(i).getPort());
						
						buses.get(c).setCurrentStop(busStops.get(i));
					}

					else {
						// commander.pinWriteDigital(busStops.get(i).getPort(),
						// false);
					}
				}
			}
			
			System.out.println("==================================================");

			try {
				Thread.sleep(30000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
}
