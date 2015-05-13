package com.example.steward.Presentors;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.steward.R;
import com.example.steward.Datafetch.DFCategory;
import com.example.steward.Datafetch.DFSubCategory;
import com.example.steward.Utils.UTILConstants;

public class ACTSplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		if (isNetworkAvailable()) {
			new dataRetrieval().execute();	
		}else{

			AlertDialog alertDialog = new AlertDialog.Builder(ACTSplashScreen.this).create();
			alertDialog.setTitle("Warnig");
			alertDialog.setMessage("Please check internet connection");
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog,int which) 
				{
					ACTSplashScreen.this.finish();
				}
			});
			alertDialog.show();

		}

	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager 
		= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	private class dataRetrieval extends AsyncTask<Void,Void, Void>	{

		@Override
		protected Void doInBackground(Void... params) {
			UTILConstants.lsCategory = new DFCategory().retrieveCategories();
			UTILConstants.lsSubCategory = new DFSubCategory().retrieveSubCategories();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			Timer timer = new Timer();
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					startActivity(new Intent(ACTSplashScreen.this,ACTHomePage.class));
					ACTSplashScreen.this.finish();
				}
			};timer.schedule(task, 2000);
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ACTSplashScreen.this.finish();
	}
}
