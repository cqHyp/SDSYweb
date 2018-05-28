package web.util;

import net.sf.json.JSONObject;

public class JSONTools {
	
	public static String createJsonString(String key,Object value){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(key, value);
		return jsonObject.toString();
	}
	public static JSONObject createJsonObject(String key,Object value) {
		JSONObject jsonObject=new JSONObject();
		jsonObject.put(key, value);
		return jsonObject;
		
	}
}
