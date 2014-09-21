//Comment added in GitHub Repo
package demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Hello world!
 *
 */
public class SimpleJsonUtil 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException, ParseException
    {   
        JSONParser parser = new JSONParser();
        
        Object obj = parser.parse(new FileReader("test.json"));
        
        //Object obj =  JSONValue.parse(new FileReader("test.json"));
        /*
         * Alternative to parse JSON text
         * 
         * Object obj = (JSONObject) JSONValue.parse(new FileReader("frommessage.txt"));
         * */
                
        JSONArray jsonarray = (JSONArray)obj;

        for (int i=0; i<jsonarray.size(); i++) {

        JSONObject jsonObject= (JSONObject)jsonarray.get(i);
        String name = (String) jsonObject.get("NAME");
        System.out.print(name+"\t");
        long age = (Long) jsonObject.get("AGE");
        System.out.print(age+"\t");

        // loop array
        JSONArray msg = (JSONArray) jsonObject.get("MESG");
        Iterator iterator = msg.iterator(); 
        if(iterator.hasNext())
        	System.out.print(iterator.next());	
        while (iterator.hasNext()) {
        System.out.print(" "+iterator.next());	
        }
        System.out.println();        }
    }
}
