/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.utils;

import com.github.drainyyyy.manageStream.core.Settings;
import com.github.drainyyyy.manageStream.misc.Templates;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class ConfigHandler extends JsonHandler {

    /**
     * Get the whole config
     * <br>
     * Parses the whole config into a HashMap
     *
     * @return The config as HashMap
     * @see JsonHandler
     * @since 1.0.0
     */
    public static HashMap readConfig() {
        String path = Settings.appDir + "/config.json";
        return (HashMap) readJson(path);
    }

    /**
     * Get chosen parts of the config
     * <br>
     * Parses the target you want into a Object.<br>
     * With the target String you can navigate through the config and the result will get returned.
     *
     * @param target A String with the keys to navigate through the config. The different keys get split by '.' (e.g. "test.test2").
     * @return The result of what you have been navigating to.
     * @see JsonHandler
     * @since 1.0.0
     */
    public static Object readConfig(String target) {
        String path = Settings.appDir + "/config.json";
        HashMap config = (HashMap) readJson(path);

        ArrayList<String> targets = new ArrayList<>(Arrays.asList(target.split("\\.")));
        HashMap currentHashMap = config;
        Object output = null;

        for (String key : targets) {
            if (currentHashMap.get(key) instanceof HashMap) {
                currentHashMap = (HashMap) currentHashMap.get(key);
            }
            output = currentHashMap;
        }
        return output;
    }

    public static void checkConfig() throws IOException {
        String configFile = readConfig().toString();
        String configTemplate = Templates.config;
        String path = Settings.appDir + "/config.json";
        //TODO check if config doesn't equal template but value of key doesn't matter
        if (!configFile.equals(configTemplate)) {
            File config = new File(path);
            FileWriter fileWriter = new FileWriter(config);
            BufferedWriter configWriter = new BufferedWriter(fileWriter);

            configWriter.write(configTemplate);
            configWriter.flush();
        }
    }

}
