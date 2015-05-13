package com.example.steward.Datafetch;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.steward.Connectivity.ServerConnect;
import com.example.steward.Models.MODELSubCategory;

public class DFSubCategory {

	public List<MODELSubCategory> retrieveSubCategories(){

		List<MODELSubCategory> listCategory = new ArrayList<MODELSubCategory>();
		MODELSubCategory subCategory;
		JSONObject jsonObj = new JSONObject();
		jsonObj = new ServerConnect().requestServer("tables.php");
		
		try {
			if (jsonObj == null) {
				return null;
			} else {

				if (jsonObj.get("subcategory") == null) {
					subCategory = new MODELSubCategory("", "", "", "", "");
					listCategory.add(subCategory);
				}else{
					JSONArray arrayOne = new JSONArray();	
					arrayOne = (JSONArray) jsonObj.get("subcategory");
					JSONObject jsonObject2;
					for (int i = 0; i < arrayOne.length(); i++) {
						jsonObject2=(JSONObject) arrayOne.get(i);

						subCategory = new MODELSubCategory(jsonObject2.getString("id"),jsonObject2.getString("name"),jsonObject2.getString("price"),jsonObject2.getString("description"),jsonObject2.getString("categoryIdFk"));			
						listCategory.add(subCategory);
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
