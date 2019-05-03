/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.drainyyyy.manageStream.core.Settings;



import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class JsonHandler {
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * TODO redo documentation
     */
    public static void writeJson(Object content, String path) {
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(content);
            FileWriter writer = new FileWriter(new File(path));
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(json);
            bufferedWriter.flush();
        } catch (Exception e) {
            Settings.log.exceptionHandler(e);
        }
    }

    /**
     * TODO redo documentation
     */
    public static HashMap readJson(String path) {
        HashMap jsonContent = null;

        try (FileReader reader = new FileReader(new File(path))) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            jsonContent = mapper.readValue(bufferedReader, HashMap.class);
        } catch (Exception e) {
            Settings.log.exceptionHandler(e);
        }
        return jsonContent;
    }

}
