/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.utils;

import com.github.drainyyyy.manageStream.core.Settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        return readJson(path);
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
        HashMap config = readConfig();

        ArrayList<String> targets = new ArrayList<>(Arrays.asList(target.split("\\.")));
        HashMap currentHashMap = config;
        Object output = null;

        for (String key : targets) {
            if (currentHashMap.get(key) instanceof HashMap) {
                currentHashMap = (HashMap) currentHashMap.get(key);
                output = currentHashMap;
            } else {
                output = currentHashMap.get(key);
            }
        }
        return output;
    }

    /** Writes the given value to the requested target
     *
     * @param target The targeted path
     * @param key The key of the dictionary that's getting written
     * @param value The value dictionary that's getting written
     *
     * @see ConfigHandler#readConfig(String)
     */
    public static void writeConfig(String target, String key, Object value) {
        HashMap config = readConfig();
        ArrayList<String> targets = new ArrayList<>(Arrays.asList(target.split("\\.")));
        Map currentHashMap = config;

        for (String targetKey : targets) {
            currentHashMap = (Map) currentHashMap.get(targetKey);
        }
        currentHashMap.put(key, value);
        writeJson(config, Settings.appDir + "/config.json");
    }

    /** Tries to read the requested and if the path doesn't exist it will get created
     *
     * @param target the targeted value
     * @return the targeted value if it exists, if not it creates the path and returns null
     *
     * @see JsonHandler
     * @see ConfigHandler#readConfig(String)
     * @see ConfigHandler#writeConfig(String, String, Object)
     *
     * @since 1.0.0
     */
    public static Object readWrite(String target) {
        if (readConfig(target) != null)  {
            return readConfig(target);
        }

        ArrayList<String> targets = new ArrayList<>(Arrays.asList(target.split("\\.")));
        String key = targets.get(targets.size() - 1);
        String targetPath = target.replace("." + key, "");
        ArrayList<String> targetPathParts = new ArrayList<>(Arrays.asList(targetPath.split("\\.")));

        StringBuilder path = new StringBuilder();
        for (int i = 0; i <= targetPathParts.size() - 1; i++) {
            path.append(targetPathParts.get(i));
            String pathString = path.toString();
            if (readConfig(pathString) == null) {
                writeConfig(pathString.replace(targetPathParts.get(i), ""), targetPathParts.get(i), new HashMap<>());
            }
            path.append(".");
        }

        writeConfig(targetPath, key, null);
        return readConfig(target);
    }
}
