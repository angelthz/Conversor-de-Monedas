package com.athz.model;

import javax.swing.ImageIcon;

public class Currency {
	private double amount;
	private double currency;
	private String tag;
	private String name;
	/**
	 * @param amount
	 * @param currency
	 * @param tag
	 * @param name
	 */
	public Currency(double amount, double currency, String tag, String name) {
		this.amount = amount;
		this.currency = currency;
		this.tag = tag;
		this.name = name;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the currency
	 */
	public double getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(double currency) {
		this.currency = currency;
	}
	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.tag+" "+this.name + " " + this.currency;
	}
	

	
}
