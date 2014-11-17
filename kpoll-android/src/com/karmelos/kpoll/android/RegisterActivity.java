package com.karmelos.kpoll.android;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.karmelos.kpoll.model.InterestArea;
import com.karmelos.kpoll.model.PollSurvey;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RegisterActivity extends Activity{
ArrayList<InterestArea> selectedInterest;
InterestAdapter interestAdapter;
ArrayList<InterestArea> availableInterest;
final String INTERESTURL = "www.karmelos.com.ng?available";
final String SENDINTEREST = "www.karmelos.com.ng/register?userid=";
final String GETINTEREST = "www.karmelos.com/interests";
static String userId;
static String password;
Button submit;
EditText passtext,phonetext;
final int REGISTER = 1;
final String REGRESPONSE = "success";
final String REGDIALOG = "Registration is successfull.Do you want to start recieving polls?";
final String FAILED = "Registration failed, please try again";
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reginterestxml);
		submit = (Button) findViewById(R.id.but);
		passtext = (EditText) findViewById(R.id.pass);
		phonetext = (EditText) findViewById(R.id.line);
		
		
		setUserId(userId);
		if(getUserId()!=null)
			phonetext.setVisibility(View.GONE);
		
		createView();

		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(phonetext.getVisibility() != View.GONE)
					setUserId(phonetext.getText().toString());
//				AllConnection allConnection = new AllConnection();
//				allConnection.setCurrentlist(selectedInterest);
//				allConnection.setType(REGISTER);
//				allConnection.execute(SENDINTEREST+getUserId());
			new HttpSendInt().execute(SENDINTEREST+getUserId());
				
				
				
			}
		});
}

private class InterestAdapter extends ArrayAdapter<InterestArea>{
	ArrayList<InterestArea> interest;
	public InterestAdapter(Context context,int viewId,ArrayList<InterestArea> list){
		super(context,viewId,list);
		interest = new ArrayList<>();
		interest.addAll(list);
	}
	private class ViewHolder{
		private TextView name;
		private CheckBox box;
	}
	
	public View getView(int position,View convertView,ViewGroup group){
		ViewHolder holder = null;
		CheckBox ischecked = null;
		if(convertView == null){
			LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflator.inflate(R.layout.interest, null);
			
			RelativeLayout relative = (RelativeLayout)convertView.findViewById(R.id.RelativeLayout1);
			
			holder = new ViewHolder();
			
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.box = (CheckBox) convertView.findViewById(R.id.interestcheck);
			final CheckBox checkBox = holder.box;
			convertView.setTag(holder);
			System.out.println("I GOT HERE");
			final InterestArea area = interest.get(position);
			relative.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(selectedInterest == null)
						selectedInterest = new ArrayList<>();
				RelativeLayout rel = (RelativeLayout) v;
//				InterestArea area = (InterestArea) rel.getTag();
				
				if(!checkBox.isChecked())                 {
					checkBox.setChecked(true);
				if(!selectedInterest.contains(area))
					selectedInterest.add(area);           }
				else if(checkBox.isChecked())             {
					checkBox.setChecked(false);
					if(selectedInterest.contains(area))
						selectedInterest.remove(area);
				}
				}
					
					
				
			});
//			ischecked = checkBox;
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		InterestArea toset = interest.get(position);
		holder.name.setText(toset.getDescription());
//		holder.box.setChecked(ischecked.isChecked());
		
		convertView.setTag(holder);
		return convertView;
	}
	
}


//private void getInterestArea(String url){
//   availableInterest =  getInterests(url);
//   
//}


	

private void createView(){
//	
//	if(isConnected())
//	new HttpGetInt().execute();
	
//	else{
		availableInterest = new ArrayList<InterestArea>();
		InterestArea int1 = new InterestArea();
		int1.setDescription("int1");
		InterestArea int2 = new InterestArea();
		int2.setDescription("int2");
		InterestArea int3 = new InterestArea();
		int3.setDescription("int3");
		
		availableInterest.add(int1);
		availableInterest.add(int2);
		availableInterest.add(int3);
		
//	}
	
	
	interestAdapter = new InterestAdapter(this, R.layout.interest, availableInterest);
	ListView listView = (ListView) findViewById(R.id.listView);
	listView.setAdapter(interestAdapter);
//	
	
}
/**
 * @return the userId
 */
public static String getUserId() {
	return userId;
}
/**
 * @param userId the userId to set
 */
public static void setUserId(String userId) {
	RegisterActivity.userId = userId;
}
/**
 * @return the password
 */
public static String getPassword() {
	return password;
}
/**
 * @param password the password to set
 */
public static void setPassword(String password) {
	RegisterActivity.password = password;
}

private class HttpSendInt extends AsyncTask<String, String , String>{

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
//		int REGISTER = 1;
//		ConnectionHandle.setType(REGISTER);
//		setPassword(passtext.getText().toString());
		 return makeConnection(params[0]);
		
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		
			AlertDialog.Builder dialog = new AlertDialog.Builder(getApplicationContext());
			dialog.setTitle(REGRESPONSE);
			dialog.setIcon(R.drawable.ic_launcher);
			dialog.setMessage(result.equals(REGRESPONSE)?REGRESPONSE:FAILED);
			dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();					
				}
			});
			
		
	}
	
	
	
}

private class HttpGetInt extends AsyncTask<String, String, ArrayList<InterestArea>>{

	@Override
	protected ArrayList<InterestArea> doInBackground(String... params) {
	
		availableInterest=  getInterests(params[0]);
	return availableInterest;
		
	}
	
}

private String userId(){
	TelephonyManager tel = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
	String number = tel.getLine1Number();
	return number;
	
	
}

private String makeConnection(String url){
	InputStream stream = null;
//	String result = "";
	HttpClient sendClient = new DefaultHttpClient();
	HttpPost clientPost = new HttpPost();
	JSONObject jobject = new JSONObject();
	
	String result = "";
//	String jstring = jobject.toString();
	try{
		jobject.put("userid", getUserId());
		jobject.put("password", passtext.getText().toString());
		jobject.put("interest", selectedInterest);
		StringEntity se = new StringEntity(jobject.toString());
		se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		clientPost.setEntity(se);
		
		HttpResponse response = sendClient.execute(clientPost);
		if(response.getStatusLine().getStatusCode() == 200){
			stream = response.getEntity().getContent();
			
			BufferedReader buf = new BufferedReader(new InputStreamReader(stream));
			String line = "";
			while((line = buf.readLine()) != null)
				result +=line;	
		}
		else{
			result = "There was a problem processing you request: result is not ok";
			
		}
		
		
	}
	
	catch(Exception eo){
		result = "There was a problem processing you request";	
	}
	return result;
	
	
}

public static ArrayList<InterestArea> getInterests(String url) {
	InputStream stream = null;
	HttpClient client = new DefaultHttpClient();
	HttpGet clientGet = new HttpGet(url);
	ArrayList<InterestArea> list = null;

	try {
		HttpResponse response = client.execute(clientGet);
		stream = response.getEntity().getContent();
	
//		JsonArray tokener = (JSONObject) new JSONTokener(IOUtils.toString(stream)).nextValue();
//		JSONArray interestArray = tokener.getJSONArray("interest");
//		list =  getListFromjson(tokener,list);
		Gson gson = new Gson();
		Reader read = new InputStreamReader(stream);
		list = gson.fromJson(read, ArrayList.class);
		

	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
	
}

private boolean isConnected(){
	ConnectivityManager conMAn = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
	NetworkInfo networkInfo  = conMAn.getActiveNetworkInfo();
	if(networkInfo != null && networkInfo.isConnected()){
		return true;
		
	}
	else{
		return false;
	}
}
//private PollSurvey getPolls(String url){
//	HttpClient client = new DefaultHttpClient();
//	HttpGet getpoll = new HttpGet(url);
//	HttpResponse pollResp;
//	InputStream stream = null; 
//	try {
//		pollResp = client.execute(getpoll);
//		stream = pollResp.getEntity().getContent();
//	} catch (ClientProtocolException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//	BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//	Gson gson = new Gson();
//	PollSurvey survey = gson.fromJson(reader, PollSurvey.class);
//	return survey;
//	
//	
//}
//


	}

