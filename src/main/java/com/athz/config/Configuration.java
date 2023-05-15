package com.athz.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Configuration {
    //Path for configuration file
    private final String CONF_PATH = System.getProperty("user.dir") + "/resources/conf/config.json";
    //Gson for read and write json files
    private Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
	//JSON object to store all properties from config.json
    private JsonObject configuration;

    /**
     * Read a config.json file and load all configuration properties
     * into a JsonObject
     */
    public Configuration(){
        getConfigurationProperties();
    }

    /**
     * Read config.json file
     */
    private void getConfigurationProperties(){
        File userJson = new File(CONF_PATH);
		
        try(Reader reader = new FileReader(userJson)){
            this.configuration = gson.fromJson(reader, JsonObject.class);
            System.out.println(gson.toJson(this.configuration));
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    /**
     * Update a configuration property.
     * @param key : property key to update
     * @param value : value to update
     */
    public void setConfigurationProperty(String key, String value){
        File userJson = new File(CONF_PATH);

        try(FileWriter writer = new FileWriter(userJson)){
            this.configuration.addProperty(key, value);
            gson.toJson(this.configuration, writer);
        }
        catch(IOException e){

        }
    }

    /**
     * Return a property configuration from config.json as String or null
     * if the property doesnt exists.
     * @param key : Property Key
     * @return String: Value of the property key
     */
    public String getConfigurationProperty(String key){
        return this.configuration.get(key).getAsString();
    }
	
}
