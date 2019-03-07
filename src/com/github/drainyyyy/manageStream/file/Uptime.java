/*
 * Copyright (c) 2018 - 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.file;

import com.github.drainyyyy.manageStream.util.ConfigReader;
import com.github.drainyyyy.manageStream.util.Log;

import java.util.HashMap;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class Uptime extends FileHandler {

    String fileName = "uptime.txt";

    /** Creates an uptime file.
     * <br>
     * As soon as this gets started every second the current uptime (since the start of the method) gets written into a file.<br>
     * There's also the possibility to write the time you already done into the config. Than the uptime counts from your time.
     *
     * @see FileHandler
     * @see File
     * @see ConfigReader
     *
     * @since 1.0
     */
    @Override
    public void start() {

        HashMap<String, HashMap> config = ConfigReader.readConfig("config.json");
        HashMap<String, HashMap> configFiles = config.get("files");
        HashMap<String, Object> configFilesUptime = configFiles.get("uptime");

        int seconds = Integer.parseInt( (String) configFilesUptime.get("time_done_in_sec"));
        int minutes = 0;
        int hours = 0;

        while(true) {
            if (seconds >= 60) {
                seconds = 0;
                minutes++;
            }
            if (minutes >= 60) {
                minutes = 0;
                hours++;
            }

            String writableUptime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            writeFile(writableUptime, fileName);
            seconds++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.err(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /** Deletes the file.
     *
     * @see FileHandler
     * @see File
     *
     * @since 1.0
     */
    @Override
    public void delete() {
        deleteFile(fileName);
    }
}
