import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
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

	public JSONParser() {
		
	}
	
	public void parseString(String input) {
		JsonString = input;

		Object obj = JSONValue.parse(JsonString);

		if (obj == null)
			System.out.println("Obj is null");

		JSONArray array = (JSONArray) obj;

		if (array != null) {

			for (int i = 0; i < array.size(); i++) {

				id = (JSONObject) array.get(i);

				String color = (String) id.get("color");

				if (color.compareTo("red") == 0 || color.compareTo("blue") == 0) {
					buses.add((JSONObject) array.get(i));
				}
			}
		}
	}

	public static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public String readJsonFromUrl(String url) throws IOException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			
			return jsonText;
			
		} finally {
			is.close();
		}
	}

	public JSONObject getString() {
		return id;
	}

	public ArrayList<JSONObject> getBuses() {
		return buses;
	}
}
