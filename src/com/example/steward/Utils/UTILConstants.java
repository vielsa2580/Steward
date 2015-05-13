package com.example.steward.Utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.example.steward.Models.MODELMainCategory;
import com.example.steward.Models.MODELSubCategory;

public class UTILConstants {
	
//	----------- FUNCTIONING DATA-------------------------------------------
//	public static String serverurl = "http://10.0.2.2/android_connect/";
	public static String serverurl = "http://192.168.0.100/android_connect/";
	
//	----------------------------------- APP DATA --------------------------
	
	public static List<MODELMainCategory> lsCategory = new ArrayList<MODELMainCategory>();
	public static List<MODELSubCategory> lsSubCategory = new ArrayList<MODELSubCategory>();
	
	
	public static void setTypeFace(Context context,TextView view,int size){
		Typeface font = Typeface.createFromAsset(context.getAssets(), "BNEfG98.ttf");
		view.setTypeface(font);
		view.setTextSize(size);
	}

}
