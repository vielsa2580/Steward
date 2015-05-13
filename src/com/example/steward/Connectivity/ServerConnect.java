package com.example.steward.Connectivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.steward.Utils.UTILConstants;


public class ServerConnect {

	public JSONObject requestServer(String service){

		InputStream is=null;
		JSONObject jObject = null;
		
		try{
			DefaultHttpClient httpClient = new DefaultHttpClient();

			HttpGet httpGet = new HttpGet(UTILConstants.serverurl+service);				
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

			//convert response to string

			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			String result=sb.toString();

			jObject = new JSONObject(result);

		}catch(JSONException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jObject;

	}
}
