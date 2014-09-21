package moredemos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CopyOfFromLineEntry implements LogEntry{
		
	private String type;
	
	private List<String> tags = new ArrayList<String>();
	
	//private Date timestamp;
	
	private Calendar timestamp;
	
	private String host, qid, from, size, nrcpts, msgid, fromRelay;
	
	private String sourceHost;
	
	private String sourcePath;
	
	private String message = "";
	
	private boolean filter = false;
	
	//private static String fromRegex = "hi";
	
	private static String[] FORMATS = new String[]{"yyyy-MM-dd'T'HH:mm:ss.SSS",
		"yyyy.MM.dd G 'at' HH:mm:ss z",
		"yyyyy.MMMMM.dd GGG hh:mm aaa",
		"EEE, d MMM yyyy HH:mm:ss Z",
		"yyMMddHHmmssZ"};
	
	public CopyOfFromLineEntry(JSONObject json){
		Pattern patternFrom;
		Matcher matcherFrom;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		String fromRegex = "(.{15})\\s(.*?)\\s.*:\\s(.*?):.*from=<(.*?)>.*size=(.*?),.*nrcpts=(.*?),.*msgid=<(.*?)>.*relay=(.*)";
		patternFrom = Pattern.compile(fromRegex);
				
		message = (String)json.get("message");
		
		matcherFrom = patternFrom.matcher(message.trim());
		if (matcherFrom.matches()) {
			String date = matcherFrom.group(1);
			timestamp = getCaledarDate(date, 2014);
			host = matcherFrom.group(2);
			qid = matcherFrom.group(3);
			from = matcherFrom.group(4);
			size = matcherFrom.group(5);
			nrcpts = matcherFrom.group(6);
			msgid = matcherFrom.group(7);
			fromRelay = matcherFrom.group(8);
		}
		else
			System.out.println("Doens't Match a fromline");
		
		sourceHost = (String)json.get("host");
		sourcePath = (String)json.get("path");
		
		type = (String)json.get("type");
		JSONArray array = (JSONArray)json.get("tags");
		tags.addAll(array);
		
	}
	
	public Date parseDate(String value){
		for(int i = 0; i < FORMATS.length; i++){
			SimpleDateFormat format = new SimpleDateFormat(FORMATS[i]);
			Date temp;
			try {
				temp = format.parse(value);
				if(temp != null)
					return temp;
			} catch (ParseException e) {}
		}
		return null;
	}

	public int getMonth(String mon) {
		int month = -1;
		if (mon.equalsIgnoreCase("jan"))
			month = 0;
		else if (mon.equalsIgnoreCase("feb"))
			month = 1;
		else if (mon.equalsIgnoreCase("mar"))
			month = 2;
		else if (mon.equalsIgnoreCase("apr"))
			month = 3;
		else if (mon.equalsIgnoreCase("may"))
			month = 4;
		else if (mon.equalsIgnoreCase("jun"))
			month = 5;
		else if (mon.equalsIgnoreCase("jul"))
			month = 6;
		else if (mon.equalsIgnoreCase("aug"))
			month = 7;
		else if (mon.equalsIgnoreCase("sep"))
			month = 8;
		else if (mon.equalsIgnoreCase("oct"))
			month = 9;
		else if (mon.equalsIgnoreCase("nov"))
			month = 10;
		else if (mon.equalsIgnoreCase("dec"))
			month = 11;
		return month;

	}
	
	public Calendar getCaledarDate(String timestamp, int year) {
		String time = "";
		try {
			String t[] = timestamp.split("\\s+");
			time = t[2];
			Calendar c = Calendar.getInstance();
			
			int month = getMonth(t[0]);
			int date = Integer.parseInt(t[1]);

			String time_in_day[] = time.split(":");
			int hourOfDay = Integer.parseInt(time_in_day[0]);
			int minute = Integer.parseInt(time_in_day[1]);
			int second = Integer.parseInt(time_in_day[2]);
			c.set(year, month, date, hourOfDay, minute, second);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSON(){
		JSONObject json = new JSONObject();
		json.put("@timestamp",DateFormat.getDateInstance().format(timestamp.getTime()));
		json.put("host",sourceHost);
		json.put("path",sourcePath);
		json.put("message",message);
		json.put("host",host);
		json.put("qid",qid);
		json.put("from",from);
		json.put("size",size);
		json.put("nrcpts",nrcpts);
		json.put("msgid",msgid);
		json.put("fromRelay",fromRelay);
		json.put("type",type);
		JSONArray temp = new JSONArray();
		temp.addAll(tags);
		json.put("tags", temp);
		return json;
	}
	

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getNrcpts() {
		return nrcpts;
	}

	public void setNrcpts(String nrcpts) {
		this.nrcpts = nrcpts;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getFromRelay() {
		return fromRelay;
	}

	public void setFromRelay(String fromRelay) {
		this.fromRelay = fromRelay;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isFilter() {
		return filter;
	}

	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	public List<String> getTags() {
		return tags;
	}
	
	public void addTag(String tag){
		tags.add(tag);
	}

	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	public String getSourceHost() {
		return sourceHost;
	}

	public String getSourcePath() {
		sourcePath.contains("");
		return sourcePath;
	}

	public String getMessage() {
		return message;
	}

	public void setSourceHost(String sourceHost) {
		this.sourceHost = sourceHost;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (filter ? 1231 : 1237);
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result
				+ ((sourceHost == null) ? 0 : sourceHost.hashCode());
		result = prime * result
				+ ((sourcePath == null) ? 0 : sourcePath.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CopyOfFromLineEntry other = (CopyOfFromLineEntry) obj;
		if (filter != other.filter)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (sourceHost == null) {
			if (other.sourceHost != null)
				return false;
		} else if (!sourceHost.equals(other.sourceHost))
			return false;
		if (sourcePath == null) {
			if (other.sourcePath != null)
				return false;
		} else if (!sourcePath.equals(other.sourcePath))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
