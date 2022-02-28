package com.company.models;

import java.util.ArrayList;

import com.opencsv.bean.CsvBindByPosition;

public class Seller {

	@CsvBindByPosition(position = 0)
	String name;
	String country;
	int itemsNumber;
	double itemsValue;
	double shippingValue;
	double totalValue;
	ArrayList<Card> availableCards = new ArrayList<>();
	ArrayList<Card> notAvailableCards = new ArrayList<>();

	public String getName() {
		return name;
	}

	public int getItemsNumber() {
		return itemsNumber;
	}

	public void setItemsNumber(int itemsNumber) {
		this.itemsNumber = itemsNumber;
	}

	public double getItemsValue() {
		return itemsValue;
	}

	public void setItemsValue(double itemsValue) {
		this.itemsValue = itemsValue;
	}

	public double getShippingValue() {
		return shippingValue;
	}

	public void setShippingValue(double shippingValue) {
		this.shippingValue = shippingValue;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public ArrayList<Card> getAvailableCards() {
		return availableCards;
	}

	public void setAvailableCards(ArrayList<Card> availableCards) {
		this.availableCards = availableCards;
	}

	public ArrayList<Card> getNotAvailableCards() {
		return notAvailableCards;
	}

	public void setNotAvailableCards(ArrayList<Card> notAvailableCards) {
		this.notAvailableCards = notAvailableCards;
	}

}
