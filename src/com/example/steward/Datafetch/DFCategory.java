package com.example.steward.Datafetch;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.steward.Connectivity.ServerConnect;
import com.example.steward.Models.MODELMainCategory;

public class DFCategory {

	public List<MODELMainCategory> retrieveCategories(){

		List<MODELMainCategory> listCategory = new ArrayList<MODELMainCategory>();
		MODELMainCategory mainCategory;
		JSONObject jsonObj = new JSONObject();
		jsonObj = new ServerConnect().requestServer("category.php");
		
		try {
			if (jsonObj == null) {
				return null;
			} else {

				if (jsonObj.get("category") == null) {
					mainCategory = new MODELMainCategory("","");
					listCategory.add(mainCategory);
				}else{
					JSONArray arrayOne = new JSONArray();	
					arrayOne = (JSONArray) jsonObj.get("category");
					JSONObject jsonObject2;
					for (int i = 0; i < arrayOne.length(); i++) {
						jsonObject2=(JSONObject) arrayOne.get(i);

						mainCategory = new MODELMainCategory(jsonObject2.getString("id"),jsonObject2.getString("name"));			
						listCategory.add(mainCategory);
					}

				}

			} 
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCategory;

	}
}
