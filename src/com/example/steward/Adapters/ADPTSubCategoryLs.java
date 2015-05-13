package com.example.steward.Adapters;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.steward.R;
import com.example.steward.Models.MODELSubCategory;

public class ADPTSubCategoryLs extends BaseAdapter{

	Activity activity;
	List<MODELSubCategory> listSubCat;
	int indicatorVal;
	
	public ADPTSubCategoryLs(Activity activity,List<MODELSubCategory> listSubCat,int indicatorVal){
		super();
		this.activity = activity;
		this.listSubCat = listSubCat;
		this.indicatorVal = indicatorVal;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listSubCat.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listSubCat.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup parent) {
		
		View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_subcatview, null);
		
		final RelativeLayout llParent = (RelativeLayout)retval.findViewById(R.id.rlLsParent);
		
		TextView title = (TextView) retval.findViewById(R.id.lvTitle);
		title.setText(listSubCat.get(position).getName().toString());
		
		ImageView ivIndicator = (ImageView)retval.findViewById(R.id.ivIndicator);
		if (indicatorVal == position) {
			ivIndicator.setVisibility(View.VISIBLE);	
		}else{
			ivIndicator.setVisibility(View.GONE);
		}
		
		
		llParent.setTag(listSubCat.get(position).getId());
		return retval;
	}

}
