package com.imanager.consume.domain;

import java.util.Date;

import com.imanager.common.Basement;

public class ConsumeItem extends Basement{
	
	private int consumeItemId;
	
	private String itemName;
	
	private String address;
	
	private double price;
	
	private int quantity;
	
	private double totalPrice;
	
	private Date feeDate;
	
	private String inOrOut;
	
	private ConsumeType consumeType;
	
	private String weekday;

	public int getConsumeItemId() {
		return consumeItemId;
	}

	public void setConsumeItemId(int consumeItemId) {
		this.consumeItemId = consumeItemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getFeeDate() {
		return feeDate;
	}

	public void setFeeDate(Date feeDate) {
		this.feeDate = feeDate;
	}

	public String getInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}

	public ConsumeType getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(ConsumeType consumeType) {
		this.consumeType = consumeType;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

}
