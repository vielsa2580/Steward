package com.example.steward.Adapters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.steward.R;
import com.example.steward.Models.MODELOrderToDel;
import com.example.steward.Presentors.ACTHomePage;

public class ADPTExpandableListView extends BaseExpandableListAdapter {

	private Activity activity;
	private ArrayList<Object> childtems;
	private LayoutInflater inflater;
	Dialog dialog;
	private ArrayList<String> parentItems, child;
	List<MODELOrderToDel> lsTempDelItems = new ArrayList<MODELOrderToDel>();
	HashSet<Integer> expandOrCollapseGroup;

	public ADPTExpandableListView(ArrayList<String> parents, ArrayList<Object> childern,Activity activity,Dialog dialog) {
		this.parentItems = parents;
		this.childtems = childern;
		this.activity = activity;
		this.dialog = dialog;
	}


	@Override
	public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

		child = (ArrayList<String>) childtems.get(groupPosition);
		inflater=(LayoutInflater)activity.getSystemService(activity.getApplicationContext().LAYOUT_INFLATER_SERVICE);



		if (convertView == null) {
			convertView = inflater.inflate(R.layout.eplv_child, null);
		}


		TextView textView = (TextView) convertView.findViewById(R.id.tvEplvChild);
		textView.setText(child.get(childPosition));

		ToggleButton tbAddDelOrder = (ToggleButton)convertView.findViewById(R.id.tbAddDelOrder);
		tbAddDelOrder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				//				child.remove(childPosition);
				//				ExpandableListView eplvOrderCheck = (ExpandableListView)dialog.findViewById(R.id.eplvShowOrder);
				//				ADPTExpandableListView adptEplv = new ADPTExpandableListView(parentItems, childtems,activity,dialog);
				//				eplvOrderCheck.setAdapter(adptEplv);
				MODELOrderToDel del = new MODELOrderToDel(parentItems.get(groupPosition), child.get(childPosition));
				((ACTHomePage)activity).addToDelList(del);
			}
		});

		return convertView;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

		if (expandOrCollapseGroup != null && expandOrCollapseGroup.size() != 0) {
			for (Iterator<Integer> iter = expandOrCollapseGroup.iterator(); iter.hasNext();) {
				Integer pos = iter.next();
				if (pos == groupPosition) {
					if (isExpanded == false) {
						ExpandableListView eplvOrderCheck = (ExpandableListView)dialog.findViewById(R.id.eplvShowOrder);
						eplvOrderCheck.expandGroup(groupPosition);
					}

				}
			}	
		}

		inflater=(LayoutInflater)activity.getSystemService(activity.getApplicationContext().LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.eplv_group, null);
		}

		((CheckedTextView) convertView).setText(parentItems.get(groupPosition));
		((CheckedTextView) convertView).setChecked(isExpanded);

		return convertView;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return ((ArrayList<String>) childtems.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}

	@Override
	public int getGroupCount() {
		return parentItems.size();
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);

	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}