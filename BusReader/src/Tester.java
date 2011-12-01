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

	private static final int RED = 0;
	private static final int BLUE = 1;
	
	private static JSONObject json = null;
	private static String ss = null;

	private static JSONParser jParser = new JSONParser();

	public static void main(String[] args) throws MalformedURLException {
		
		elCommanderI commander = new elTest();

		BusStop temp = new BusStop(2);
		temp.addPointContainer(-84.40524458885193, 33.780551555261134,
				-84.40334558486938, 33.77768482129236);
		busStops.add(temp);

		temp = new BusStop(3);
		temp.addPointContainer(-84.4034743309021, 33.78009259885047,
				-84.40083503723145, 33.77811287558266);
		busStops.add(temp);

		temp = new BusStop(4);
		temp.addPointContainer(33.77811287558266, -84.39952611923218,
				33.77845175037882, -84.39772367477417);
		busStops.add(temp);

		temp = new BusStop(5);
		temp.addPointContainer(33.77814854667699, -84.39617872238159,
				33.77657900447511, -84.39336776733398);
		busStops.add(temp);

		temp = new BusStop(6);
		temp.addPointContainer(33.77716758617094, -84.39270257949829,
				33.77461703628178, -84.39160823822021);
		busStops.add(temp);

		temp = new BusStop(7);
		temp.addPointContainer(33.774153291779676, -84.39229488372803,
				33.77101403221729, -84.39150094985962);
		busStops.add(temp);

		temp = new BusStop(8);
		temp.addPointContainer(33.77069296509672, -84.39244508743286,
				33.76951570869478, -84.39079284667969);
		temp.addPointContainer(33.77358252586945, -84.39699411392212,
				33.77063945379299, -84.39441919326782);
		busStops.add(temp);

		temp = new BusStop(9);
		temp.addPointContainer(33.77261934977339, -84.40050466156006,
				33.776828706294516, -84.4004743309021);
		busStops.add(temp);

		temp = new BusStop(10);
		temp.addPointContainer(33.788551555261134, -84.40024458885193,
				33.77268482129236, -84.40634558486938);
		busStops.add(temp);

		

		try {
			ss = jParser
					.readJsonFromUrl("http://walkpath.cip.gatech.edu/bus_position.php");

			System.out.println(ss);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		System.out.println("Text: " + ss);
		
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

			bus = new Bus(id, colorId);

			currentPoint = new PointD((Double) jsonObjects.get(x).get("lng"),
					(Double) jsonObjects.get(x).get("lat"));

			bus.setCurrentPoint(currentPoint);

			buses.add(bus);
		}

		for (int i = 0; i < busStops.size(); i++) {

			for (int c = 0; c < buses.size(); c++) {

				if (busStops.get(i).inBusStop(buses.get(c).getCurrentPoint())) {
					// commander.pinWriteDigital(busStops.get(i).getPort(),
					// true);
					System.out.println("Bus: " + buses.get(c).getId()
							+ " is at bus stop: " + busStops.get(i).getPort());
				}

				else {
					// commander.pinWriteDigital(busStops.get(i).getPort(),
					// false);
				}
			}
		}
	}
}
