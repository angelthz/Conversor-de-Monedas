package com.athz.dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GuardedObject;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import org.apache.hc.client5.http.fluent.Request;

import com.athz.config.Configuration;
import com.athz.dto.ExchangeRate;
import com.google.gson.Gson;

public class ExchangeRateDao {
	//Store a API request as a Object
	private ExchangeRate exchangeRate;
	//Configuration object has all properties
	private final Configuration conf = new Configuration();
	//gson
	private Gson gson = new Gson();

	/**
	 * Initializes a ExchangeRate object reading a RequestResult.json.
	 * After 7 days of the last request to Fixer API its time to update
	 * the RequestResult.json this is automatically.
	 * @param base : TAG for base currency
	 */
	public ExchangeRateDao(String base) {
		if(checkForUpadteExhanges())
			updateExchangeRates(base);
		readRates();
	}

	/**
	 * Initializes a ExhangeRate object reading a RequestResult.json file
	 */
	private void readRates() {
		File file = new File(conf.getConfigurationProperty("path.data"));
		System.out.println("Reading exchange rates");
		try ( FileReader reader = new FileReader(file) ) {
			if (file.exists())
				this.exchangeRate = gson.fromJson(reader, ExchangeRate.class);
			else
				this.exchangeRate = null;
			System.out.println(exchangeRate);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Update the RequestResult.json file
	 * @param rates : ExhangeRate object with the data updated.
	 */
	private void writeRates(ExchangeRate rates) {
		File file = new File(conf.getConfigurationProperty("path.data"));
		try ( FileWriter writer = new FileWriter(file) ) {
				gson.toJson(rates, writer);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Calls to fixer api request to get the last Exchange Rates.
	 * This request will be called after 7 days of the last update.
	 * @param base : TAG of the Currency Base
	 */
	private void updateExchangeRates(String base) {
		String url = conf.getConfigurationProperty("api.url") + base;
		String key = conf.getConfigurationProperty("api.key");

		System.out.println("Updating Exchange Rates");
		try {
			String result = Request.get(url)
					.addHeader("apikey", key).addHeader("Accept", "Application/json")
					.execute().returnContent().asString();
			
			writeRates( gson.fromJson(result, ExchangeRate.class) );

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * Si la fecha de actualizacion es igual o supera los 7 dias hay que actualizar los datos
	 * que devuelve la API.
	 * @return true si se tiene que actualizar. false si no han pasado 7 dias
	 */
	private boolean checkForUpadteExhanges(){
		TemporalUnit DAYS = ChronoUnit.DAYS;
		LocalDate lastUpdateDate = LocalDate.parse(conf.getConfigurationProperty("api.last.update"));
		
		if( DAYS.between(lastUpdateDate, LocalDate.now()) >= 7 ){
			conf.setConfigurationProperty("api.last.update", LocalDate.now().toString());
			return true;
		}
		else
			return false;
	}

	/**
	 * Return a ExhangeRate object with the currencies rates info.
	 * @return ExchangeRate object.
	 */
	public ExchangeRate getExchangeRate() {
		return this.exchangeRate;
	}

	/**
	 * Return a currency rate, especified from the currency TAG.
	 * @param tag : TAG Currency rate
	 * @return : Double value of the currency rate
	 */
	public Double getCurrencyExchange(String tag) {
		return this.exchangeRate.getRates().get(tag);
	}

	public static void main(String[] args) {
		ExchangeRateDao exchangeDao = new ExchangeRateDao("MXN");
		System.out.println(exchangeDao.checkForUpadteExhanges());

	}
}
