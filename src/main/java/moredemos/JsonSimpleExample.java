package moredemos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
public class JsonSimpleExample {
     public static void main(String[] args) {
 
	JSONParser parser = new JSONParser();
 
	try {
 
		Object obj = parser.parse(new FileReader("frommessage.txt"));
 
		JSONObject jsonObject = (JSONObject) obj;
 
		/*String name = (String) jsonObject.get("message");
		System.out.println(name);
 
		String host = (String)jsonObject.get("host");
		System.out.println(host);*/
 
		// loop array
		JSONArray tags = (JSONArray) jsonObject.get("tags");
		if(tags.contains("from")){
			FromLineEntry fromLine = new FromLineEntry(jsonObject);
			System.out.println(fromLine.toJSON());
		}
		
		/*Iterator<String> iterator = msg.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}*/
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}
 
     }
 
}