package com.athz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.athz.dao.CurrencyDao;
import com.athz.model.Currency;

public class CurrencyController {
	private CurrencyDao currencyDao;
	private List<Currency> currencies;
	private String base;
	
	public CurrencyController(String base) {
		this.base = base;
		this.currencyDao = new CurrencyDao();
	}
	
	public List<Currency> getCurrencies() {
		if(this.currencies == null)
			this.currencies = this.currencyDao.getCurrencies();
		return this.currencies;
	}
	
	public Currency getCurrency(String currencyTag) {
		return null;
	}
	
	public double toForeignCurrency(Double amount, String target) {
		this.getCurrencies();
		return amount * this.filter(target).getCurrency();
	}

	public double toLocaleCurrency(Double amount, String target) {
		this.getCurrencies();
		return amount / this.filter(target).getCurrency();
	}
	
	
	public Currency filter(String tag) {
		/*List<Currency> res = this.rates.stream()
				.filter(cu -> cu.getTag().equals(tag))
				.collect(Collectors.toList());*/
		
		Currency res = this.currencies.stream()
				.filter(cu -> cu.getTag().equals(tag))
				.findAny().orElseGet(()->{
					return new Currency(0,0,"undefined","undefined");
				});
	
		return res;
	}
	
	public static void main(String[] args) {
		CurrencyController co = new CurrencyController("MXN");
		
		System.out.println(co.toForeignCurrency(500.00, "EUR"));
		System.out.println(co.toLocaleCurrency(25.2235, "EUR"));
	}
}
