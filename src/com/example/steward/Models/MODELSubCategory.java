package com.example.steward.Models;

public class MODELSubCategory {
	
	String id,name,price,desc,category;

	public MODELSubCategory(){
		super();
	}

	public MODELSubCategory(String id, String name, String price, String desc, String category){
		
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.desc = desc;
		this.category = category;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
