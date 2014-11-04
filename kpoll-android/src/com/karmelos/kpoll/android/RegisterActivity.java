package com.karmelos.kpoll.android;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity{
private TextView connect;
private Button subMit;
private EditText passEdit;
private CheckBox swimming,politics,boxing,relationship,fashion,movie,rugby,computer,investment,football;
private List<String> interests  = new ArrayList<String>();
private String[] availableInterest = {"Swimming","Politics","Boxing","Relationship","Fashion","Movie","Rugby","Computer","Investment","Football"};

@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		connect = (TextView) findViewById(R.id.connected);
		connect.setBackgroundColor(Color.RED);
		subMit = (Button) findViewById(R.id.button1);
		passEdit = (EditText) findViewById(R.id.editText2);
		swimming = (CheckBox) findViewById(R.id.checkBox1);
		politics = (CheckBox) findViewById(R.id.checkBox2);
		boxing = (CheckBox) findViewById(R.id.checkBox3);
		relationship = (CheckBox) findViewById(R.id.checkBox4);
		fashion = (CheckBox) findViewById(R.id.checkBox5);
		movie = (CheckBox) findViewById(R.id.checkBox6);
		rugby = (CheckBox) findViewById(R.id.checkBox7);
		computer = (CheckBox) findViewById(R.id.checkBox8);
		investment = (CheckBox) findViewById(R.id.checkBox9);
		football = (CheckBox) findViewById(R.id.checkBox10);
		
		swimming.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					if(!interests.contains(availableInterest[0])){
						interests.add(availableInterest[0]);
						Toast.makeText(getBaseContext(), "checked " +availableInterest[0], Toast.LENGTH_SHORT).show();
					}
				}
				else if(!isChecked) {
					if(interests.contains(availableInterest[0])){
						interests.remove(availableInterest[0]);
						Toast.makeText(getBaseContext(), "unchecked " +availableInterest[0], Toast.LENGTH_SHORT).show();
					}
				}
				
			}
		});
		
		politics.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					if(!interests.contains(availableInterest[1])){
						interests.add(availableInterest[1]);
						Toast.makeText(getBaseContext(), "checked " +availableInterest[1], Toast.LENGTH_SHORT).show();
					}
				}
				else if(!isChecked) {
					if(interests.contains(availableInterest[1])){
						interests.remove(availableInterest[1]);
						Toast.makeText(getBaseContext(), "unchecked " +availableInterest[1], Toast.LENGTH_SHORT).show();
					}
				}
				
			}
		});
		
		boxing.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					if(!interests.contains(availableInterest[2])){
						interests.add(availableInterest[2]);
						Toast.makeText(getBaseContext(), "checked " +availableInterest[2], Toast.LENGTH_SHORT).show();
					}
				}
				else if(!isChecked) {
					if(interests.contains(availableInterest[2])){
						interests.remove(availableInterest[2]);
						Toast.makeText(getBaseContext(), "unchecked " +availableInterest[2], Toast.LENGTH_SHORT).show();
					}
				}
				
			}
		});
		
		relationship.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					if(!interests.contains(availableInterest[3])){
						interests.add(availableInterest[3]);
						Toast.makeText(getBaseContext(), "checked " +availableInterest[3], Toast.LENGTH_SHORT).show();
					}
				}
				else if(!isChecked) {
					if(interests.contains(availableInterest[3])){
						interests.remove(availableInterest[3]);
						Toast.makeText(getBaseContext(), "unchecked " +availableInterest[3], Toast.LENGTH_SHORT).show();
					}
				}
				
			}
		});
		
		fashion.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					if(!interests.contains(availableInterest[4])){
						interests.add(availableInterest[4]);
						Toast.makeText(getBaseContext(), "checked " +availableInterest[4], Toast.LENGTH_SHORT).show();
					}
				}
				else if(!isChecked) {
					if(interests.contains(availableInterest[4])){
						interests.remove(availableInterest[4]);
						Toast.makeText(getBaseContext(), "unchecked " +availableInterest[4], Toast.LENGTH_SHORT).show();
					}
				}
				
			}
		});
		
		movie.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					if(!interests.contains(availableInterest[5])){
						interests.add(availableInterest[5]);
						Toast.makeText(getBaseContext(), "checked " +availableInterest[5], Toast.LENGTH_SHORT).show();
					}
				}
				else if(!isChecked) {
					if(interests.contains(availableInterest[5])){
						interests.remove(availableInterest[5]);
						Toast.makeText(getBaseContext(), "unchecked " +availableInterest[5], Toast.LENGTH_SHORT).show();
					}
				}
				
			}
		});
		
		rugby.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					if(!interests.contains(availableInterest[6])){
						interests.add(availableInterest[6]);
						Toast.makeText(getBaseContext(), "checked " +availableInterest[6], Toast.LENGTH_SHORT).show();
					}
				}
				else if(!isChecked) {
					if(interests.contains(availableInterest[6])){
						interests.remove(availableInterest[6]);
						Toast.makeText(getBaseContext(), "unchecked " +availableInterest[6], Toast.LENGTH_SHORT).show();
					}
				}
				
			}
		});
		
		computer.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					if(!interests.contains(availableInterest[7])){
						interests.add(availableInterest[7]);
						Toast.makeText(getBaseContext(), "checked " +availableInterest[7], Toast.LENGTH_SHORT).show();
					}
				}
				else if(!isChecked) {
					if(interests.contains(availableInterest[7])){
						interests.remove(availableInterest[7]);
						Toast.makeText(getBaseContext(), "unchecked " +availableInterest[7], Toast.LENGTH_SHORT).show();
					}
				}
				
			}
		});
		
		investment.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					if(!interests.contains(availableInterest[8])){
						interests.add(availableInterest[8]);
						Toast.makeText(getBaseContext(), "checked " +availableInterest[8], Toast.LENGTH_SHORT).show();
					}
				}
				else if(!isChecked) {
					if(interests.contains(availableInterest[8])){
						interests.remove(availableInterest[8]);
						Toast.makeText(getBaseContext(), "unchecked " +availableInterest[8], Toast.LENGTH_SHORT).show();
					}
				}
				
			}
		});
		
		football.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					if(!interests.contains(availableInterest[9])){
						interests.add(availableInterest[9]);
						Toast.makeText(getBaseContext(), "checked " +availableInterest[9], Toast.LENGTH_SHORT).show();
					}
				}
				else if(!isChecked) {
					if(interests.contains(availableInterest[9])){
						interests.remove(availableInterest[9]);
						Toast.makeText(getBaseContext(), "unchecked " +availableInterest[9], Toast.LENGTH_SHORT).show();
					}
				}
				
			}
		});
		
//		while(isConnected()){
//			connect.setText("Connected");
//			connect.setBackgroundColor(Color.GREEN);
//			
//		}
		
		subMit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			new HttpAsync().execute("karmelos.com.ng/kpol");
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
	
		
		return super.onOptionsItemSelected(item);
	}

	private String[] convertListToArray(List<String> list){
		String[] interestArea = null;
		if(list.size()>0){
		 interestArea = new String[list.size()];
			interestArea = list.toArray(interestArea);
		}
		return interestArea;
	}
	
	private String connection(String url){
		InputStream inputStream = null;
		String stringResponse = "";
		
		try{
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost();
		
		JSONObject jObject = new JSONObject();
		jObject.accumulate("phoneNumber", "");
		jObject.accumulate("password", passEdit.getText().toString());
		jObject.accumulate("interests", convertListToArray(interests));
		
		String jString = jObject.toString();
		StringEntity entity = new StringEntity(jString);
		
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept","application/json");
		httpPost.setHeader("content-type","application/json");
		
		HttpResponse response = httpClient.execute(httpPost);
		inputStream  = response.getEntity().getContent();
		if(inputStream != null){
			stringResponse = convertHttpResponse(inputStream);
		}
		else {
			stringResponse = "Your response could not be processed";
		}
		
		
		
		}
		catch(Exception eo){
			
		}
		
		return stringResponse;
	}
	
	private String convertHttpResponse(InputStream stream){
		String result = "";
		String line = "";
		try{
			BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
			
			while((line = buffer.readLine()) != null){
				result += line;
			}
			stream.close();
		}
		catch(IOException io){
			Log.d("convertResponse", io.getMessage());
		}
		
		return result;
	}
	
	class HttpAsync extends AsyncTask<String, String, String>{

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return connection(params[0]);
		}
		
		
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
	

	}

