import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Tester {
	
	static ArrayList<BusStop> busStops = new ArrayList<BusStop>();
	static ArrayList<Bus> buses = new ArrayList<Bus>();
	static ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
	
	 private static final int RED = 0;
	 private static final int BLUE = 1;
	 
	public static void main(String[] args) {
		String s = ("[{\"id\":\"440\",\"color\":\"red\",\"lat\":33.7875,\"lng\":-84.405425,\"plat\":33.787472,\"plng\":-84.405443,\"speed\":0}," +
				"{\"id\":\"436\",\"color\":\"green\",\"lat\":33.773795,\"lng\":-84.401693,\"plat\":33.774035,\"plng\":-84.40209,\"speed\":31.65068}," +
						"{\"id\":\"442\",\"color\":\"green\",\"lat\":33.787365,\"lng\":-84.40557,\"plat\":33.787377,\"plng\":-84.405575,\"speed\":0}," +
								"{\"id\":\"403\",\"color\":\"yellow\",\"lat\":33.787447,\"lng\":-84.405262,\"plat\":33.787442,\"plng\":-84.405267,\"speed\":0}," +
										"{\"id\":\"408\",\"color\":\"yellow\",\"lat\":33.773232,\"lng\":-84.397078,\"plat\":33.773187,\"plng\":-84.39704,\"speed\":8.09324}," +
												"{\"id\":\"437\",\"color\":\"green\",\"lat\":33.78742,\"lng\":-84.405493,\"plat\":33.787432,\"plng\":-84.405438,\"speed\":0}," +
														"{\"id\":\"401\",\"color\":\"yellow\",\"lat\":33.778533,\"lng\":-84.3981,\"plat\":33.778523,\"plng\":-84.398105,\"speed\":0}," +
																"{\"id\":\"420\",\"color\":\"yellow\",\"lat\":33.799462,\"lng\":-84.345147,\"plat\":33.799225,\"plng\":-84.346047,\"speed\":62.11608}," +
																		"{\"id\":\"407\",\"color\":\"green\",\"lat\":33.787437,\"lng\":-84.405438,\"plat\":33.7874,\"plng\":-84.40546,\"speed\":0.38892}," +
																				"{\"id\":\"438\",\"color\":\"red\",\"lat\":33.77827,\"lng\":-84.397778,\"plat\":33.778258,\"plng\":-84.398195,\"speed\":23.613}," +
																						"{\"id\":\"405\",\"color\":\"red\",\"lat\":33.787288,\"lng\":-84.405468,\"plat\":33.787293,\"plng\":-84.405458,\"speed\":0}," +
																								"{\"id\":\"443\",\"color\":\"blue\",\"lat\":33.771595,\"lng\":-84.39555,\"plat\":33.77217,\"plng\":-84.395543,\"speed\":20.0942}," +
																										"{\"id\":\"434\",\"color\":\"blue\",\"lat\":33.787328,\"lng\":-84.40615,\"plat\":33.787345,\"plng\":-84.406113,\"speed\":0}," +
																												"{\"id\":\"433\",\"color\":\"blue\",\"lat\":33.787422,\"lng\":-84.406073,\"plat\":33.787445,\"plng\":-84.406038,\"speed\":0}]");
		
		
		elCommanderI commander = new elTest();
		
		BusStop temp = new BusStop(2);
		temp.addPointContainer(33.780551555261134,-84.40524458885193, 33.77768482129236,-84.40334558486938);
		busStops.add(temp);
		
		 temp = new BusStop(3);
		temp.addPointContainer(33.78009259885047, -84.4034743309021, 33.77811287558266, -84.40083503723145);
		busStops.add(temp);
		
		 temp = new BusStop(4);
		temp.addPointContainer(33.77811287558266, -84.39952611923218, 33.77845175037882, -84.39772367477417);
		busStops.add(temp);
		
		 temp = new BusStop(5);
		temp.addPointContainer(33.77814854667699, -84.39617872238159, 33.77657900447511, -84.39336776733398);
		busStops.add(temp);
		
		 temp = new BusStop(6);
		temp.addPointContainer(33.77716758617094, -84.39270257949829, 33.77461703628178, -84.39160823822021);
		busStops.add(temp);
		
		 temp = new BusStop(7);
		temp.addPointContainer(33.774153291779676, -84.39229488372803, 33.77101403221729, -84.39150094985962);
		busStops.add(temp);
		
		 temp = new BusStop(8);
		temp.addPointContainer(33.77069296509672, -84.39244508743286, 33.76951570869478, -84.39079284667969);
		temp.addPointContainer(33.77358252586945, -84.39699411392212, 33.77063945379299, -84.39441919326782);
		busStops.add(temp);
		
		 temp = new BusStop(9);
		temp.addPointContainer(33.77261934977339, -84.40050466156006, 33.776828706294516, -84.4004743309021);
		busStops.add(temp);
		
		
		JSONParser parser = new JSONParser(s);
		System.out.println(parser.getString());
		
		JSONObject obj = parser.getString();
		
		System.out.println(obj.get("lat"));
		System.out.println(obj.get("lng"));
		
		PointContainer point = new PointContainer(33.788551555261134, -84.40024458885193, 33.77268482129236, -84.40634558486938);
		
		temp = new BusStop(10);
		temp.addPointContainer(33.788551555261134, -84.40024458885193, 33.77268482129236, -84.40634558486938);
		busStops.add(temp);
	
		
		PointD point2 = new PointD( (Double)obj.get("lat"),  (Double)obj.get("lng"));
		
		jsonObjects = parser.getBuses();
		
		String color;
		int colorId = 0;
		int id;
		
		System.out.println("Size: " + jsonObjects.size());
		
		for(int x = 0; x < jsonObjects.size(); x++)
		{
			System.out.println(jsonObjects.get(x).get("color"));
			
			color = (String) jsonObjects.get(x).get("color");
			
			if(color.compareTo("red") == 0)
				colorId = RED;
			
			else if(color.compareTo("blue") == 0)
				colorId = BLUE;
					
			id = Integer.parseInt((String)jsonObjects.get(x).get("id"));
			buses.add(new Bus(id, colorId));
			
		}
		
		
		
		for(int i = 0; i < busStops.size(); i++){
			
			if(busStops.get(i).inBusStop(point2)){
				commander.pinWriteDigital(busStops.get(i).getPort(), true);
			}
			
			else{
				commander.pinWriteDigital(busStops.get(i).getPort(), false);
			}
			
		}

		
		for(int i = 0; i < buses.size(); i++){
			
			System.out.println("Color: " + buses.get(i).getColor());
			System.out.println("ID: " + buses.get(i).getId());		
		}

		
		
		
		//System.out.println("Valid: " + point.isLocation(point2));
	}
}
