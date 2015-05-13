package com.example.steward.Presentors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.steward.R;
import com.example.steward.Adapters.ADPTCategoryGrid;
import com.example.steward.Adapters.ADPTExpandableListView;
import com.example.steward.Adapters.ADPTHorizontalListview;
import com.example.steward.Adapters.ADPTSubCategoryLs;
import com.example.steward.Handler.DatabaseHandler;
import com.example.steward.HorizontalListView.HorizontalListView;
import com.example.steward.Models.MODELOrder;
import com.example.steward.Models.MODELOrderToDel;
import com.example.steward.Models.MODELSubCategory;
import com.example.steward.Utils.UTILConstants;

public class ACTHomePage extends Activity implements OnItemClickListener,OnClickListener{

	GridView gvCat;
	HorizontalListView hlvTableNumber;
	ListView lvSubCat;
	List<MODELSubCategory> lsSubCat;
	TextView tvSubCatName,tvSubCatDesc;
	ImageView ivSubCatImage;
	LinearLayout llSubCatFull;
	EditText etTableDisp;
	Button btMakeOrder,btCheckOrder;
	String catSelected, itemSelected,tabSelected;
	List<MODELOrderToDel> lsOrderToDel;

	private String[] dataObjects = new String[]{ "Table #1","Table #2","Table #3","Table #4","Table #5","Table #6",
			"Table #7","Table #8","Table #9","Table #10","Table #11",};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

		initializer();
		populator();
	}

	private void initializer(){
		gvCat = (GridView)findViewById(R.id.gvCat);
		gvCat.setOnItemClickListener(this);
		hlvTableNumber = (HorizontalListView)findViewById(R.id.listview);
		hlvTableNumber.setOnItemClickListener(tableListClick);
		lvSubCat = (ListView)findViewById(R.id.lvSubCat);
		lvSubCat.setOnItemClickListener(listItemClick);
		tvSubCatDesc =(TextView)findViewById(R.id.tvSubCatDesc);
		tvSubCatName =(TextView)findViewById(R.id.tvSubCatName);
		ivSubCatImage = (ImageView)findViewById(R.id.ivSubCatImage);
		llSubCatFull = (LinearLayout)findViewById(R.id.llSubCatFull);
		etTableDisp = (EditText)findViewById(R.id.etTableDisplay);
		btMakeOrder = (Button)findViewById(R.id.btMakeOrder);
		btMakeOrder.setOnClickListener(this);
		btCheckOrder = (Button)findViewById(R.id.btCheckOrder);
		btCheckOrder.setOnClickListener(this);
	}

	private void populator(){

		hlvTableNumber.setAdapter(new ADPTHorizontalListview(ACTHomePage.this,dataObjects));

		if (UTILConstants.lsCategory != null && UTILConstants.lsCategory.size() != 0) {
			gvCat.setAdapter(new ADPTCategoryGrid(ACTHomePage.this, UTILConstants.lsCategory));
			catSelected = UTILConstants.lsCategory.get(0).getName();
		}

		if (UTILConstants.lsSubCategory != null && UTILConstants.lsSubCategory.size() != 0) {
			lsSubCat = subCatDiv("1", UTILConstants.lsSubCategory);
			itemSelected = UTILConstants.lsSubCategory.get(0).getName();

			if( lsSubCat != null){
				if (lsSubCat.size() != 0) {
					llSubCatFull.setVisibility(View.VISIBLE);
					lvSubCat.setVisibility(View.VISIBLE);
					lvSubCat.setAdapter(new ADPTSubCategoryLs(ACTHomePage.this, lsSubCat,0));
					substituteData(lsSubCat.get(0));
				}else{
					lvSubCat.setVisibility(View.INVISIBLE);
					llSubCatFull.setVisibility(View.INVISIBLE);
				}
			}else{
				lvSubCat.setVisibility(View.INVISIBLE);
				llSubCatFull.setVisibility(View.INVISIBLE);
			}
		}
	}

	private OnItemClickListener tableListClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int pos,long arg3) {
			etTableDisp.setText(""+dataObjects[pos]);
			tabSelected = dataObjects[pos];
		}
	};

	private OnItemClickListener listItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {

			RelativeLayout rlParent = (RelativeLayout)view.findViewById(R.id.rlLsParent);
			ImageView ivIndicator = (ImageView)view.findViewById(R.id.ivIndicator);
			
			for (int i = 0; i < lsSubCat.size(); i++) {
				if (rlParent.getTag().equals(lsSubCat.get(position).getId())) {
					ivIndicator.setVisibility(View.VISIBLE);
					lvSubCat.setAdapter(new ADPTSubCategoryLs(ACTHomePage.this, lsSubCat,position));
					itemSelected = lsSubCat.get(position).getName();
					substituteData(lsSubCat.get(position));
				}else{
					ivIndicator.setVisibility(View.GONE);
					lvSubCat.setAdapter(new ADPTSubCategoryLs(ACTHomePage.this, lsSubCat,position));
				}
			}

		}
	};

	private List<MODELSubCategory> subCatDiv(String catId, List<MODELSubCategory> subCat){

		List<MODELSubCategory> lsDivSubCat = new ArrayList<MODELSubCategory>();

		for (int i = 0; i < subCat.size(); i++) {
			if (catId.equals(subCat.get(i).getCategory().toString())) {
				lsDivSubCat.add(subCat.get(i));
			}
		}
		return lsDivSubCat;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3) {

		catSelected = UTILConstants.lsCategory.get(pos).getName();
		lsSubCat = subCatDiv(UTILConstants.lsCategory.get(pos).getId(), UTILConstants.lsSubCategory);
		if (lsSubCat != null && lsSubCat.size() !=0) {
			itemSelected = lsSubCat.get(0).getName();
			lvSubCat.setVisibility(View.VISIBLE);
			llSubCatFull.setVisibility(View.VISIBLE);
			lvSubCat.setAdapter(new ADPTSubCategoryLs(ACTHomePage.this, lsSubCat,0));
			substituteData(lsSubCat.get(0));	
		}else{
			lvSubCat.setVisibility(View.INVISIBLE);
			llSubCatFull.setVisibility(View.INVISIBLE);
		}
	}

	private void substituteData(MODELSubCategory subCategory){
		tvSubCatDesc.setText(subCategory.getDesc());
		tvSubCatName.setText(subCategory.getName());
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btMakeOrder:
			submitToDB();
			break;

		case R.id.btCheckOrder:
			//			showTheDB();
			showOrderCheckDialog();
			break;

		default:
			break;
		}

	}

	private void submitToDB(){

		DatabaseHandler dbHandler = new DatabaseHandler(ACTHomePage.this);

		if (catSelected != null && tabSelected != null && itemSelected != null) {
			MODELOrder order = new MODELOrder(catSelected, tabSelected, itemSelected);
			dbHandler.addOrder(order);	
		} else {
			Toast.makeText(this, "Please pick a table", Toast.LENGTH_SHORT).show();
		}

	}

	private List<Object> expandableLVChild(List<MODELOrder> orderList){

		List<Object> childList = new ArrayList<Object>();
		HashSet<String> nonRedundant = new HashSet<String>();

		if(orderList != null){

			for (int i = 0; i < orderList.size(); i++) {
				nonRedundant.add(orderList.get(i).getTableNumber());
			}

			List<MODELOrder> tempOrderList = new ArrayList<MODELOrder>();
			tempOrderList = new ArrayList<MODELOrder>(orderList);


			for (Iterator<String> iter = nonRedundant.iterator(); iter.hasNext(); ) {
				List<String> tempList  = new ArrayList<String>();
				String obj = iter.next();
				for (int j = 0; j < tempOrderList.size(); j++) {

					String temp = tempOrderList.get(j).getTableNumber();
					if (obj.equals(temp)) {
						tempList.add(tempOrderList.get(j).getItem());
						//						tempOrderList.remove(j);
					}

				}
				//				iter.remove();
				childList.add(tempList);
			}
		}
		return childList;
	}

	private void showOrderCheckDialog(){

		final Dialog dialog = new Dialog(ACTHomePage.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.setContentView(R.layout.dialog_checkorder);

		List<MODELOrder> lsOrder = new ArrayList<MODELOrder>();
		lsOrder = new DatabaseHandler(ACTHomePage.this).getAllOrder();

		TextView tvDialogHeader = (TextView)dialog.findViewById(R.id.tvDialogHeading);
		UTILConstants.setTypeFace(ACTHomePage.this, tvDialogHeader, 20);
		
		if (lsOrder.size() == 0) {
			tvDialogHeader.setText("No Data");
		}else{
			tvDialogHeader.setText("Orders");
		}


		lsOrderToDel = new ArrayList<MODELOrderToDel>();

		ArrayList<Object> childList = new ArrayList<Object>();
		childList = (ArrayList<Object>) expandableLVChild(lsOrder);

		HashSet<String> groupTempList = new HashSet<String>();
		groupTempList = (HashSet<String>) new DatabaseHandler(ACTHomePage.this).getAllTables();

		ArrayList<String> groupList = new ArrayList<String>(groupTempList);

		ExpandableListView eplvOrderCheck = (ExpandableListView)dialog.findViewById(R.id.eplvShowOrder);
		final ADPTExpandableListView adptEplv = new ADPTExpandableListView(groupList, childList,ACTHomePage.this,dialog);
		eplvOrderCheck.setAdapter(adptEplv);

		LinearLayout btDialogCancel = (LinearLayout)dialog.findViewById(R.id.btDialogCancel);
		btDialogCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		LinearLayout btDialogOk = (LinearLayout)dialog.findViewById(R.id.btDialogOk);
		btDialogOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				delFromList(lsOrderToDel);
				dialog.dismiss();
			}
		});

		dialog.show();
	}

	public void addToDelList(MODELOrderToDel del){
		lsOrderToDel.add(del);
	}

	private void delFromList(List<MODELOrderToDel> delList){
		for (int i = 0; i < delList.size(); i++) {
			new DatabaseHandler(ACTHomePage.this).deleteOrder(delList.get(i));
		}
	}

}
