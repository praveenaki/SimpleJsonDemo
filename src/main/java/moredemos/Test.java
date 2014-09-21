package moredemos;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Test {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		try {
			 
			Object obj = parser.parse(new FileReader("frommessage.txt"));
	 
			JSONObject jsonObject = (JSONObject) obj;
			LogEntry entry = new FromLineEntry(jsonObject);
			System.out.println(entry.toJSON());
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

	}

}
