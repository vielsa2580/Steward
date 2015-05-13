package com.example.steward.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.steward.R;

public class ADPTHorizontalListview extends BaseAdapter{

	Activity activity;
	private String[] dataObjects;
	
	public ADPTHorizontalListview(Activity activity,String[] dataObjects){
		super();
		this.dataObjects = dataObjects;
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataObjects.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return dataObjects[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup parent) {
		
		View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewitem, null);
		
		TextView title = (TextView) retval.findViewById(R.id.title);
		title.setText(dataObjects[position]);
		return retval;
	}

	
}
