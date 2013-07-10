package com.npu.orderApp.domain;

import java.text.NumberFormat;
import java.util.Date;


public class Order {
	private int id;
	private Date date;
	private String cusnum;
	private float amount;
	private static NumberFormat dlrFormatter = NumberFormat.getCurrencyInstance();
	
	public Order(Date orderDate, String cusNumber) {
		date = orderDate;
		cusnum = cusNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCusnum() {
		return cusnum;
	}
	
	public boolean equals(Object otherObj) {
		Order otherOrder;
		
		if (!(otherObj instanceof Order)) return false;
		otherOrder = (Order) otherObj;
		if (id == otherOrder.id && id != 0) {
			return true;
		}
		
		return false;
	}
	
	public String toString() {
		String amountStr = dlrFormatter.format(amount);
		String orderStr = ("Order(id: " + id + " date: " + date + " cusnum: " + cusnum + " amount: " + amountStr + ")");
		return orderStr;
	}
	
}
