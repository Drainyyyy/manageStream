/*
 * Copyright (c) 2018 - 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.core;

import com.github.drainyyyy.manageStream.file.Countdown;
import com.github.drainyyyy.manageStream.file.Uptime;
import com.github.drainyyyy.manageStream.file.Time;
import com.github.drainyyyy.manageStream.util.ConfigReader;
import com.github.drainyyyy.manageStream.util.Log;

import java.awt.*;
import java.net.URI;
import java.util.HashMap;

public class Main {

    ConfigReader cr = new ConfigReader("config.json");
    private HashMap<String, HashMap> config = cr.readConfig();
    private HashMap<String, String> configDirectories = config.get("directories");

    Thread uptime = new Thread(() -> new Uptime().start());
    Thread countdown = new Thread(() -> new Countdown().start());
    Thread time = new Thread(() -> new Time().start());

    public static void main(String[] args) {

        //TODO only if enabled start

    }

    void openDashboard() {
        try {
            Desktop.getDesktop().browse(new URI(configDirectories.get("dashboard")));
        } catch (Exception e) {
            Log.err(e.getMessage());
            e.printStackTrace();
        }
    }

    void startUptime() {
        uptime.start();
    }

    void stopUptime() {
        uptime.interrupt();
        new Uptime().delete();
    }

    void startCountdown() {
        countdown.start();
    }

    void stopCountdown() {
        countdown.interrupt();
        new Countdown().delete();
    }

    void startTime() {
        time.start();
    }

    void stopTime() {
        time.interrupt();
        new Time().delete();
    }

}
