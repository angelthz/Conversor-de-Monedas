package com.athz.dao;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.athz.model.Currency;
import com.google.gson.Gson;

public class CurrencyDao {
	private Gson gson;
	
	public CurrencyDao() {
		this.gson = new Gson();
	}
	
	public List<Currency> getCurrencies() {
		Map<String, String> names = this.getSymbols();
		Map<String, Double> rates = this.getRates();
		List<Currency> result = new ArrayList<>();
		
		for(Map.Entry<String, Double> rate : rates.entrySet()) {
			Currency currency = new Currency(1, rate.getValue(), rate.getKey(), names.get(rate.getKey()));
			result.add(currency);
			//System.out.println(currency);
		}
		
		return result;
	}
	
	private Map<String, Double>  getRates() {
		Map<?,?> rates = null;
		try(Reader reader = new FileReader("../Conversor-de-monedas/src/main/resources/com/athz/data/Rates.json")){
			rates = this.gson.fromJson(reader, Map.class);
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		return (Map<String, Double>) rates.get("rates");
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, String> getSymbols() {
		Map<?, ?> names = null;
		Map<String, String> sym = null;

		try(Reader reader = new FileReader("../Conversor-de-monedas/src/main/resources/com/athz/data/Symbols.json")){
			names = this.gson.fromJson(reader, Map.class);
			sym = (Map<String, String>) names.get("symbols");
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return sym;
	}
	
	public void mostrarNombre(String tag) {
		System.out.println( getSymbols().get(tag));
		System.out.println( this.getRates().get(tag));
	}
	
	/*public static void main(String[] args) {
		CurrencyDao cdo = new CurrencyDao();
		cdo.getCurrencies();
	}*/
}
