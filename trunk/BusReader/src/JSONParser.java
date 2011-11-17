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

	public JSONParser(String input) {
		JsonString = input;

		Object obj = JSONValue.parse(JsonString);
		
		if (obj == null)
			System.out.println("Obj is null");

	JSONArray array = (JSONArray) obj;
		
//		JSONArray array=new JSONArray();
//		  array.add("hello");
//		  array.add(new Integer(123));
//		  array.add(new Boolean(false));
//		  array.add(null);
//		  array.add(new Double(123.45));
		  //System.out.print(array);

		if (array != null ){
			id = (JSONObject) array.get(1);
			
			
		}
		
		//else
			//id = "NULL";

	}
	
	public JSONObject getString(){
		return id; 
	}
}
