package com.karmelos.kpoll.android;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import com.google.gson.Gson;
import com.karmelos.kpoll.model.PollSurvey;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.sax.StartElementListener;

public class AllConnection extends AsyncTask<String, String, String> {
	String URL = "www.karmelos.com/poll?=";
	String pollid;
	PollSurvey pollSurvey;
	
	
	@Override
	protected String doInBackground(String... params) {
		getPoll(params[0]);
		return "";
	}
	

	private PollSurvey getPoll(String url){ 
		PollSurvey pollSurvey = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet getpoll = new HttpGet(url);
		try {
			HttpResponse resp = client.execute(getpoll);
			InputStream stream = resp.getEntity().getContent();
			Reader reader = new InputStreamReader(stream);
			Gson gson = new Gson();
			 pollSurvey = gson.fromJson(reader, PollSurvey.class);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pollSurvey;
		
	}


	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Intent intent = new Intent(AllConnection.this, RespondActivity.class);
		Bundle bundle = new Bundle();
		bundle.putParcelable("poll", pollSurvey);
		intent.putExtras(bundle);
		St
	}
}
