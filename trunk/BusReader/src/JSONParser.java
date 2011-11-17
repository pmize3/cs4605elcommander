import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JSONParser {

	private String JsonString = new String();
	private Object outer;
	private JSONArray data;
	private ArrayList<String> ids;
	private JSONObject id;

	private ArrayList<JSONObject> buses = new ArrayList<JSONObject>();

	public JSONParser(String input) {
		JsonString = input;

		Object obj = JSONValue.parse(JsonString);

		if (obj == null)
			System.out.println("Obj is null");

		JSONArray array = (JSONArray) obj;

		if (array != null) {

			for (int i = 0; i < array.size(); i++) {

				id = (JSONObject) array.get(i);
				
				String color = (String)id.get("color");

				if (color.compareTo("red") == 0  || color.compareTo("blue") == 0 ) {
					buses.add((JSONObject) array.get(i));
				}
			}
		}

		// else
		// id = "NULL";

	}

	public JSONObject getString() {
		return id;
	}
	
	public ArrayList<JSONObject> getBuses(){
		return buses;
	}	
}
