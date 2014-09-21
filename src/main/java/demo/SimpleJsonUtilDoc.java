package demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Hello world!
 *
 */
public class SimpleJsonUtilDoc 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException, ParseException
    {   
        JSONParser parser = new JSONParser();
        
        /*Parses text/json document into JSON Array if the file has array of json elements or JsonObject if 
         * the file has only one json element.
         * Parser takes Reader object of the file as Input or String(of Json Data) as Input
         * Finding Json Array Size: jsonArray.size()
         * Getting Json Object in a Json Array: jsonArray.get(<index>)
         * Getting Value of key in a Json Object: jsonObject.get(<key>)
         * Returns: Json Array if Json Object is in an Array or Json Object if only one Json Object
        */
        
        /*Test: 1
         * String str = "[{\"NAME\":\"Alan\",\"AGE\":12,\"COLOR\":\"blue\",\"MESG\":[\"hi\",\"how\"]}]";
        Object obj = parser.parse(str);
        */
        
        /*Test: 2 Starts from here*/
        Object obj = parser.parse(new FileReader("test.json")); //Parse JSON text into java object from the input source.
                
        JSONArray jsonarray = (JSONArray)obj;

        for (int i=0; i<jsonarray.size(); i++) {

        JSONObject jsonObject= (JSONObject)jsonarray.get(i);
        String name = (String) jsonObject.get("NAME");
        System.out.print(name+"\t");
        long age = (Long) jsonObject.get("AGE");
        System.out.print(age+"\t");

        // loop array
        JSONArray msg = (JSONArray) jsonObject.get("MESG");//A Json Object can contain a Json Array for a particular key
        
        //System.out.println(" Size of message jsonArray is:"+ msg.size());
        System.out.println(" Array Elements are:"+ msg.get(0)+ " "+ msg.get(1));
        
        Iterator iterator = msg.iterator(); //iterator object on msg array
        if(iterator.hasNext())
        	System.out.print(iterator.next());	
        while (iterator.hasNext()) {
        System.out.print(" "+iterator.next());	
        }
        System.out.println();//Add new line for each record
        }
    }
}
