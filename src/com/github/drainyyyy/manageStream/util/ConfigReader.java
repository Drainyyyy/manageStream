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

public class ConfigReader {

    private String directory;

    public ConfigReader(String directory) {

        this.directory = directory;

    }

    public HashMap readConfig() {

        try {
            Object configFile = new JSONParser().parse(new FileReader(this.directory));

            JSONObject configJson = (JSONObject) configFile;

            HashMap<String, HashMap<String, HashMap>> config = configJson;

            return config;
        } catch (Exception e) {
            Log.err(e.getMessage());
            e.printStackTrace();
        }

        return null;

    }

}
