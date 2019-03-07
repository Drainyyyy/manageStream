/*
 * Copyright (c) 2018 - 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.core;

import com.github.drainyyyy.manageStream.file.Countdown;
import com.github.drainyyyy.manageStream.file.Uptime;
import com.github.drainyyyy.manageStream.file.Time;
import com.github.drainyyyy.manageStream.gui.Gui;
import com.github.drainyyyy.manageStream.util.ConfigReader;
import com.github.drainyyyy.manageStream.util.Log;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class Main {

    private static HashMap<String, HashMap> config = ConfigReader.readConfig("config.json");
    private static HashMap<String, String> configDirectories = config.get("directories");
    private static HashMap<String, Boolean> configToggle = config.get("toggle");

    private static Thread uptime = new Thread(() -> new Uptime().start());
    private static Thread countdown = new Thread(() -> new Countdown().start());
    private static Thread time = new Thread(() -> new Time().start());

    public static void main(String[] args) {

        new Gui("dark");
        start();

    }

    /** Calls the different methods after toggle check.
     * <br>
     * After a check if the function is enabled in the config file, the respective method gets called.<br>
     * The directories for the files, for the streaming software and the dashboard url is taken from the config.
     *
     * @see ConfigReader
     *
     * @since 1.0
     */
    public static void start() {

        if (configToggle.get("software")) {startSoftware();}
        if (configToggle.get("dashboard")) {openDashboard();}
        if (configToggle.get("uptime")) {startUptime();}
        if (configToggle.get("countdown")) {startCountdown();}
        if (configToggle.get("time")) {startTime();}
        if (configToggle.get("date")) { //TODO create Date
            System.out.println("not ready yet!");}

    }

    /** Stops all threads.
     * <br>
     * Stops the threads the different file creators are running on.
     *
     * @since 1.0
     */
    public static void stop() {

        stopCountdown();
        stopTime();
        stopUptime();

    }


    /** Opens the stream dashboard.
     * <br>
     * Opens the respective stream dashboard for the streamer (e.g. https://twitch.tv/USERNAME/dashboard/live).<br>
     * The url is taken from the config.json
     *
     * @see ConfigReader
     *
     * @since 1.0
     */
    private static void openDashboard() {
        try {
            Desktop.getDesktop().browse(new URI(configDirectories.get("dashboard")));
        } catch (Exception e) {
            Log.err(e.getMessage());
            e.printStackTrace();
        }

    }

    /** Starts the streaming software.
     * <br>
     * Opens the streaming software for the user.<br>
     * The path is taken from the config.
     *
     * @see ConfigReader
     *
     * @since 1.0
     */
    private static void startSoftware() {

        File softwareExe = new File(configDirectories.get("software"));
        if (!softwareExe.exists()) {
            Log.err("Software path does'nt exist!");
        } else {
            try {
                Runtime.getRuntime().exec(softwareExe.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                Log.err(e.getMessage());
            }
        }
    }

    /** Starts the uptime.
     * <br>
     * Starts the thread that starts creating the uptime.txt file.
     *
     * @see Uptime
     *
     * @since 1.0
     */
    private static void startUptime() {
        uptime.start();
    }

    /** Stops the uptime.
     * <br>
     * Stops the uptime thread and deletes the uptime file.
     *
     * @see Uptime
     *
     * @since 1.0
     */
    private static void stopUptime() {
        uptime.interrupt();
        new Uptime().delete();
    }

    /** Starts the countdown.
     * <br>
     * Starts the thread that starts creating the countdown.txt file.
     *
     * @see Countdown
     *
     * @since 1.0
     */
    private static void startCountdown() {
        countdown.start();
    }

    /** Stops the countdown.
     * <br>
     * Stops the countdown thread and deletes the countdown file.
     *
     * @see Countdown
     *
     * @since 1.0
     */
    private static void stopCountdown() {
        countdown.interrupt();
        new Countdown().delete();
    }

    /** Starts the time.
     * <br>
     * Starts the thread that starts creating the time.txt file.
     *
     * @see Time
     *
     * @since 1.0
     */
    private static void startTime() {
        time.start();
    }

    /** Stops the time.
     * <br>
     * Stops the time thread and deletes the time file.
     *
     * @see Time
     *
     * @since 1.0
     */
    private static void stopTime() {
        time.interrupt();
        new Time().delete();
    }

}
