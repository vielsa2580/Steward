package com.example.steward.Models;


public class MODELOrder {

	String id,tableNumber,category,item;
	
	public MODELOrder(){
		super();
	}

	public MODELOrder(MODELOrder modelOrder){
		super();
		this.id = modelOrder.getId();
		this.tableNumber = modelOrder.getTableNumber();
		this.category = modelOrder.getCategory();
		this.item = modelOrder.getItem();
	}

	public MODELOrder(String id, String category,String tableNumber, String item){
		super();
		this.id = id;
		this.tableNumber = tableNumber;
		this.category = category;
		this.item = item;
	}
	
	public MODELOrder(String category,String tableNumber, String item){
		super();
		this.tableNumber = tableNumber;
		this.category = category;
		this.item = item;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}


}
