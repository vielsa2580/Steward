package com.example.steward.Models;


public class MODELOrderToDel {

	String cat,item;

	public MODELOrderToDel(String cat, String item){
		super();
		this.cat = cat;
		this.item = item;
	}
	
	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
}
