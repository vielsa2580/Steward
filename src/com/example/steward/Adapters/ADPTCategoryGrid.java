package com.example.steward.Adapters;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.steward.R;
import com.example.steward.Models.MODELMainCategory;

public class ADPTCategoryGrid extends BaseAdapter{

	Activity activity;
	List<MODELMainCategory> listCat;
	
	public ADPTCategoryGrid(Activity activity,List<MODELMainCategory> listCat){
		super();
		this.activity = activity;
		this.listCat = listCat;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listCat.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listCat.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup parent) {
		
		View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewitem, null);
		
		final RelativeLayout llParent = (RelativeLayout)retval.findViewById(R.id.llHoriparent);
		
		TextView title = (TextView) retval.findViewById(R.id.title);
		title.setText(listCat.get(position).getName().toString());
		llParent.setTag(position);
//		llParent.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//
//				Toast.makeText(activity, ""+llParent.getTag().toString(), Toast.LENGTH_SHORT).show();
//			}
//		});

		return retval;
	}

}
