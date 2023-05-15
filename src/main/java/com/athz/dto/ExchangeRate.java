package com.athz.dto;


import java.util.Map;

import com.google.gson.Gson;


/**
 * ExchangeRate DTO
 */
public class ExchangeRate {
	private boolean success;
	private Long timestamp;
	private String base;
	private String date;
	private Map<String, Double> rates;
	
	public ExchangeRate() {
		
	}
	
	/**
	 * @param success
	 * @param timestamp
	 * @param base
	 * @param date
	 * @param rates
	 */
	public ExchangeRate(boolean success, Long timestamp, String base, String date, Map<String, Double> rates) {
		this.success = success;
		this.timestamp = timestamp;
		this.base = base;
		this.date = date;
		this.rates = rates;
	}

	

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}


	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}


	/**
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return timestamp;
	}


	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}


	/**
	 * @return the base
	 */
	public String getBase() {
		return base;
	}


	/**
	 * @param base the base to set
	 */
	public void setBase(String base) {
		this.base = base;
	}


	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}


	/**
	 * @return the rates
	 */
	public Map<String, Double> getRates() {
		return rates;
	}


	/**
	 * @param rates the rates to set
	 */
	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}


	@Override
	public String toString() {
		
		return "success: "+this.success 
				+ " timestamp: "+this.timestamp 
				+ " base: "+this.base 
				+ " date: "+this.date 
				+ " rates: "+this.rates;
	}
	
	
	public static void main(String[] args) {
		String res = "{'success': true,'timestamp':1683480603,'base':'MXN','date':'2023-05-07','rates':{'EUR':0.050219,'GBP':0.044543,'JPY':7.590993,'KRW':74.19873,'USD':0.056298}}";
		Gson gson = new Gson();
		
		ExchangeRate ratesResult = gson.fromJson(res, ExchangeRate.class);
		System.out.println(ratesResult);
		System.out.println(ratesResult.getRates());
		
		for(String key: ratesResult.getRates().keySet()) {
			System.out.println(key + " " + ratesResult.getRates().get(key));
		}
		System.out.println();
		ratesResult.getRates().forEach((k,v)->System.out.println(k + " " + v));
	}
	
}
