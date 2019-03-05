/*
 * Copyright (c) 2018 - 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.file;

import com.github.drainyyyy.manageStream.util.ConfigReader;
import com.github.drainyyyy.manageStream.util.Log;

import java.util.HashMap;

public class Countdown extends FileHandler {

    String fileName = "countdown.txt";

    @Override
    public void start() {

        HashMap<String, HashMap> config = new ConfigReader("config.json").readConfig();
        HashMap<String, HashMap> configFiles = config.get("files");
        HashMap<String, Object> configFilesCountdown = configFiles.get("countdown");

        int countdown = (int) configFilesCountdown.get("countdown_in_sec");
        String timeUpMessage = (String) configFilesCountdown.get("time_up_message");

        while (countdown >= 0) {

            if (countdown > 0) {
                writeFile(Integer.toString(countdown), fileName);
                countdown--;
            } else {
                writeFile(timeUpMessage, fileName);
            }

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
