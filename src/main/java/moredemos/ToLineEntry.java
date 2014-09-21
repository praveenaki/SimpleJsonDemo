package moredemos;

import org.json.simple.JSONObject;

public class ToLineEntry implements LogEntry{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("host","ubuntu");
		return json;
	}

}
