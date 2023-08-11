package com.athz.controller;


import com.athz.dao.ExchangeRateDao;
import com.athz.dto.ExchangeRate;

public class ExchangeRateController {
	private ExchangeRateDao dao;
	

	public ExchangeRateController(String base) {
		this.dao = new ExchangeRateDao(base);
	}
	
	/**
	 * Return a ExchangeRateDTO.
	 * @return ExchangeRate
	 */
	public ExchangeRate getExchangeRates() {
		return dao.getExchangeRate();
	}
	
	/**
	 * Return a currency rate.
	 * @param tag : Tag for the currency
	 * @return	String with the value of the currency rate
	 */
	public Double getCurrencyRate(String tag) {
		return dao.getCurrencyExchange(tag);
	}
	
	/**
	 * Converts a local currency to foreign currency.
	 * @param amount : Local currency amount to convert to foreign currency.
	 * @param target : Tag of the foreign currency i.e: "MXN", "USD", "EUR"
	 * @return Double: Value of the local currency to foreign currency.
	 */
	public double toForeignCurrency(Double amount, String target) {
		return amount * getCurrencyRate(target);
	}

	/**
	 * Converts a foreign currency to local currency.
	 * @param amount : Foreign currency amount to convert to local currency.
	 * @param target : Tag of the foreign currency i.e: "MXN", "USD", "EUR"
	 * @return Double: Value of the foireign currency to local currency.
	 */
	public double toLocaleCurrency(Double amount, String target) {
		return amount / getCurrencyRate(target);
	}
}
