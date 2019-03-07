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
public class Countdown extends FileHandler {

    String fileName = "countdown.txt";

    /** Creates a countdown file.
     * <br>
     * A countdown in seconds is taken from the config. As long as the countdown is more or same as 0, it's getting written to a countdown.txt file.<br>
     * When the time's up a special message (also taken from the config) is written into the file.
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
