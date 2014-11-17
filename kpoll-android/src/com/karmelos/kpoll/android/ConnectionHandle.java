//package com.karmelos.kpoll.android;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.json.JSONTokener;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.karmelos.kpoll.model.InterestArea;
//
//public  class ConnectionHandle {
//	
//	private static final int REGISTER = 1;
//	private static final int RESPOND = 2;
//	private static final int INTEREST_AREAS = 3;
//	private static int type;
//	
//	public static String sendInterest(String url,ArrayList<InterestArea> list){
//		HttpClient sendClient = new DefaultHttpClient();
//		HttpPost clientPost = new HttpPost();
//		JSONObject jobject = new JSONObject();
//		String result = "";
//		String jstring = jobject.toString();
//
//		try {
//			jobject.put("phoneid", RegisterActivity.getUserId());
//			if(type == REGISTER){
//				jobject.put("password", RegisterActivity.getPassword());}
//			else{
//				jobject.put("polltype", RespondActivity.getPollType());
//			}
//			jobject.put("interest", toArray(list));
//			
//			
//			
//			HttpResponse response = null;
//			
//			StringEntity entity = new StringEntity(jstring);
//			clientPost.setEntity(entity);
//			clientPost.setHeader("accept", "application/json");
//			clientPost.setHeader("content-type", "application/json");
//			response = sendClient.execute(clientPost);
//			InputStream stream = response.getEntity().getContent();
//			if (stream != null) {
//				BufferedReader buff = new BufferedReader(new InputStreamReader(
//						stream));
//				String line = "";
//				while ((line = buff.readLine()) != null) {
//					result += line;
//				}
//			}
//			else{
//				result = "Your request could not be processed";
//			}
//
//		} catch (Exception eo) {
//			eo.getClass().getCanonicalName();
//			eo.getMessage();
//		}
//
//		return result;
//	}
//	
//	public static ArrayList<InterestArea> getInterests(String url) {
//		InputStream stream = null;
//		HttpClient client = new DefaultHttpClient();
//		HttpGet clientGet = new HttpGet(url);
//		ArrayList<InterestArea> list = null;
//
//		try {
//			HttpResponse response = client.execute(clientGet);
//			stream = response.getEntity().getContent();
//		
////			JsonArray tokener = (JSONObject) new JSONTokener(IOUtils.toString(stream)).nextValue();
////			JSONArray interestArray = tokener.getJSONArray("interest");
////			list =  getListFromjson(tokener,list);
//			Gson gson = new Gson();
//			Reader read = new InputStreamReader(stream);
//			list = gson.fromJson(read, ArrayList.class);
//			
//
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//		
//	}
//	
//	
//	
////	private static String[] toArray(List<InterestArea> list) {
////		String[] stringArray = null;
////		if (list != null) {
////			stringArray = new String[list.size()];
////			stringArray = list.toArray(stringArray);
////		}
////		return stringArray;
////	}
//	
//	private static ArrayList<InterestArea> getListFromjson(JsonObject jArray,ArrayList<InterestArea> interests) {
//		InterestArea inter = new InterestArea();
//		if (jArray != null) {
//			JsonArray array = jArray.getAsJsonArray();
//			for (int i = 0; i < array.size(); i++) {
//			inter.setId(array.get(i).getAsJsonObject().get("").getAsLong());
//			inter.setDescription(array.get(i).getAsJsonObject().get("").getAsString());
//			interests.add(inter);
//					
//			}
//			}
//		return interests;
//		
//		}
//	
//	
//
//	/**
//	 * @return the type
//	 */
//	public static int getType() {
//		return type;
//	}
//
//	/**
//	 * @param type the type to set
//	 */
//	public static void setType(int type) {
//		ConnectionHandle.type = type;
//	}
//
//	/* (non-Javadoc)
//	 * @see android.os.AsyncTask#doInBackground(Params[])
//	 */
//
//	
//}
