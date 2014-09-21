package moredemos;
import org.json.simple.JSONObject;


public interface LogEntry {

	public JSONObject toJSON();
	public int hashCode();
	public boolean equals(Object obj);
}
