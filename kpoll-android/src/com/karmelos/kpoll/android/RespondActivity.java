package com.karmelos.kpoll.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RespondActivity extends Activity {
private static final int CHOICE_POLL	 = 1;
private static final int FREETEXT_POLL = 2;
private static final int KEYWORD = 3;
private EditText freeEdit;
private CheckBox yesCheck,noCheck,maybeCheck;
private Button respond;
private static int pollType;
private LinearLayout lineLayout;
private String phonenumber, answer;
private RadioGroup choiceGroup;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_respond);
		freeEdit = (EditText) findViewById(R.id.editText1);
		
		
		freeEdit = (EditText) findViewById(R.id.editText1);
		yesCheck = (CheckBox) findViewById(R.id.checkBox1);
		noCheck = (CheckBox) findViewById(R.id.checkBox2);
		maybeCheck = (CheckBox) findViewById(R.id.checkBox3);
		lineLayout = (LinearLayout) findViewById(R.id.linearLayout2);
		respond = (Button) findViewById(R.id.button1);
		
		
		switch (pollType) {
		case CHOICE_POLL:
			freeEdit.setVisibility(View.GONE);
			break;
		case FREETEXT_POLL:
			lineLayout.setVisibility(View.GONE);
			break;
		case KEYWORD:
			lineLayout.setVisibility(View.GONE);
			//freeEdit.setInputType(TextView.s
			break;
		}
		
	}
	
	private void response(){
		phonenumber = "";
		if(pollType == FREETEXT_POLL || pollType == KEYWORD){
			answer = freeEdit.getText().toString();
			
		}
		else if(pollType ==CHOICE_POLL){
			if(yesCheck.isChecked())
				answer = yesCheck.getText().toString();
			else if(noCheck.isSelected())
				answer = noCheck.getText().toString();
			else if (maybeCheck.isSelected())
				answer = maybeCheck.getText().toString();
			else answer = null;
			
		}
		
	}
	
	private String connection(String url){
		InputStream inputStream = null;
		String result = "";
		try{
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost();
			JSONObject jObject = new JSONObject();
			jObject.accumulate("answer", answer);
			jObject.accumulate("phoneNumber", phonenumber);
			jObject.accumulate("pollType", pollType);
			
			String jString = jObject.toString();
			StringEntity entity = new StringEntity(jString);
			
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept","application/json");
			httpPost.setHeader("content-type","application/json");
			
			HttpResponse response = client.execute(httpPost);
			inputStream = response.getEntity().getContent();
			
			if(inputStream != null){
				result = convertHttpResponse(inputStream);
			}
			
			else{
				result = "Error submitting your response";
			}
		
		}
		catch(IOException io){
			Log.d("convertResponseErrorIO", io.getMessage());
		}
		catch (JSONException json) {
		Log.d("convertJsonError", json.getMessage());
		}
		return result;
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
			Log.d("convertResponseError", io.getMessage());
		}
		
		return result;
	}
	
	private class HttpAsync extends AsyncTask<String, String, String>{

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected String doInBackground(String... params) {
			return connection(params[0]);
			 
		}
		
		
	} 
}
