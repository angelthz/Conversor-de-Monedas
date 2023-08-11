package com.athz.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class ReadConfigurations {

    //Path for configuration file
    private static final String CONF_PATH = System.getProperty("user.dir") + "/resources/conf/config.json";
    //Gson for read and write json files
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
	//JSON object to store all properties from config.json
    private static JsonObject configurations;

    private ReadConfigurations(){};

    public static JsonObject getConfigurations(){
        File userJson = new File(CONF_PATH);
        try(Reader reader = new FileReader(userJson)){
            if(configurations == null)
                configurations = gson.fromJson(reader, JsonObject.class);
             Log.logger.info("Configuraciones cargadas");
        }
        catch(IOException e){
            Log.logger.warning(e.getMessage());
        }
        return configurations;
    }
}
