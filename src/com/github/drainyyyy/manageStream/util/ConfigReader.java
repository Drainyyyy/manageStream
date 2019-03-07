/*
 * Copyright (c) 2018 - 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.*;
import java.util.HashMap;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class ConfigReader {

    /** Converts the config.json to a java HashMap
     * <br>
     * Converts the config.json content with json-simple to an accessible java HashMap.
     *
     * @param directory The directory the config.json is in.
     * @return A HashMap with the content of config.json
     *
     * @see JSONObject
     *
     * @since 1.0
     */
    public static HashMap<String, HashMap> readConfig(String directory) {

        try {
            Object configFile = new JSONParser().parse(new FileReader(directory));

            JSONObject configJson = (JSONObject) configFile;

            HashMap<String, HashMap> config = configJson;

            return config;
        } catch (Exception e) {
            Log.err(e.getMessage());
            e.printStackTrace();
        }

        return null;

    }

}
