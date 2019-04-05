/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.utils;

import com.github.drainyyyy.manageStream.core.Settings;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class JsonHandler {

    /** Write into a Json file
     *
     * @param content The content that should be written in form of a JSONObject
     * @param path The path where the json file is
     *
     * @see JSONObject
     *
     * @since 1.0.0
     */
    public static void writeJson(JSONObject content, String path) {
        try (FileWriter writer = new FileWriter(new File(path))){
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(content.toJSONString());
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
            Settings.log.error(e.getMessage());
        }
    }

    /** Get the content of a json file
     *
     * @param path The path where the json file is
     * @return The files content
     *
     * @see JSONParser
     *
     * @since 1.0.0
     */
    public static Object readJson(String path) {
        JSONParser parser = new JSONParser();
        Object jsonContent = null;

        try (FileReader reader = new FileReader(new File(path))) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            jsonContent = parser.parse(bufferedReader);
        } catch (Exception e) {
            e.printStackTrace();
            Settings.log.error(e.getMessage());
        }
        return jsonContent;
    }

}
