/*
 * Copyright (c) 2018 - 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.file;

import com.github.drainyyyy.manageStream.util.ConfigReader;
import com.github.drainyyyy.manageStream.util.Log;

import java.util.HashMap;

public class Uptime extends FileHandler {

    String fileName = "uptime.txt";

    @Override
    public void start() {

        HashMap<String, HashMap> config = new ConfigReader("config.json").readConfig();
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

    @Override
    public void delete() {
        deleteFile(fileName);
    }
}
