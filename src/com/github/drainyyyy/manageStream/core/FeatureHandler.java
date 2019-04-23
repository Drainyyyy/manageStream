/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.core;

import com.github.drainyyyy.manageStream.files.*;
import com.github.drainyyyy.manageStream.utils.ConfigHandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
class FeatureHandler {
    private static Thread uptime, countdown, time, date;
    private static String softwarePath = (String) ConfigHandler.readConfig("directories.software");
    private static String dashboardUrl = (String) ConfigHandler.readConfig("directories.dashboard");
    private static ArrayList<Thread> activeThreads = new ArrayList<>();


    /** Checks if the software file exists and opens it if so.
     *
     * @since 1.0.0
     */
    static void startSoftware() {
        File software = new File(softwarePath);
        if (!software.exists()) {
            Settings.log.error("[startSoftware] Couldn't follow path (Typo?) | {" + software.getAbsolutePath() + "}");
            return;
        }
        try {
            Runtime.getRuntime().exec(software.getAbsolutePath());
            Settings.log.information("[startSoftware] Started software");
        } catch (IOException e) {
            Settings.log.error(Arrays.toString(e.getStackTrace()));
        }
    }

    /** Opens the dashboard.
     *
     * @since 1.0.0
     */
    static void openDashboard() {
        try {
            Desktop.getDesktop().browse(new URI(dashboardUrl));
            Settings.log.information("[openDashboard] Opened Dashboard");
        } catch (Exception e) {
            Settings.log.error(Arrays.toString(e.getStackTrace()));
        }
    }

    /** Lets the uptime thread execute Uptime.start();
     *
     * @see Uptime
     *
     * @since 1.0.0
     */
    static void startUptime() {
        uptime = new Thread(() -> {
            try {
                new Uptime().start();
                Settings.log.information("[startUptime] Started uptime");
            } catch (InterruptedException e) {
                Settings.log.error(Arrays.toString(e.getStackTrace()));
            }
        });
        uptime.start();
        Settings.log.information("[startUptime] Started uptime thread");
        activeThreads.add(uptime);
    }

    /** Lets the countdown thread execute Countdown.start();
     *
     * @see Countdown
     *
     * @since 1.0.0
     */
    static void startCountdown() {
        countdown = new Thread(() -> {
            try {
                new Countdown().start();
                Settings.log.information("[startCountdown] Started countdown");
            } catch (InterruptedException e) {
                Settings.log.error(Arrays.toString(e.getStackTrace()));
            }
        });
        countdown.start();
        Settings.log.information("[startCountdown] Started countdown thread");
        activeThreads.add(countdown);
    }

    /** Lets the tine thread execute Time.start();
     *
     * @see Time
     *
     * @since 1.0.0
     */
    static void startTime() {
        time = new Thread(() -> {
            try {
                new Time().start();
                Settings.log.information("[startTime] Started time");
            } catch (InterruptedException e) {
                Settings.log.error(Arrays.toString(e.getStackTrace()));
            }
        });
        time.start();
        Settings.log.information("[startTime] Started time thread");
        activeThreads.add(time);
    }

    /** Lets the date thread execute Date.start();
     *
     * @see Date
     *
     * @since 1.0.0
     */
    static void startDate() {
        date = new Thread(() -> {
            try {
                new Date().start();
                Settings.log.information("[startDate] Started date");
            } catch (InterruptedException e) {
                Settings.log.error(Arrays.toString(e.getStackTrace()));
            }
        });
        date.start();
        Settings.log.information("[startDate] Started date thread");
        activeThreads.add(date);
    }

    /** Stops the uptime thread and deletes the file.
     *
     * @see Uptime
     *
     * @since 1.0.0
     */
    static void stopUptime() {
        new Uptime().delete();
        uptime.interrupt();
        Settings.log.information("[stopUptime] Stopped uptime thread");
        activeThreads.remove(uptime);
    }

    /** Stops the countdown thread and deletes the file.
     *
     * @see Countdown
     *
     * @since 1.0.0
     */
    static void stopCountdown() {
        new Countdown().delete();
        countdown.interrupt();
        Settings.log.information("[stopCountdown] Stopped countdown thread");
        activeThreads.remove(countdown);
    }

    /** Stops the time thread and deletes the file.
     *
     * @see Time
     *
     * @since 1.0.0
     */
    static void stopTime() {
        new Time().delete();
        time.interrupt();
        Settings.log.information("[stopTime] Stopped time thread");
        activeThreads.remove(time);
    }

    /** Stops the date thread and deletes the file.
     *
     * @see Date
     *
     * @since 1.0.0
     */
    static void stopDate() {
        new Date().delete();
        date.interrupt();
        Settings.log.information("[stopDate] Stopped date thread");
        activeThreads.remove(date);
    }

    /** Starts all functions (if they are enabled).
     *
     * @see ConfigHandler
     *
     * @since 1.0.0
     */
    public static void startAll() {
        Settings.log.information("[startAll] Trying to start everything enabled");
        if ((boolean) ConfigHandler.readConfig("toggle.countdown")) {
            startCountdown();
        }
        if ((boolean) ConfigHandler.readConfig("toggle.date")) {
            startDate();
        }
        if ((boolean) ConfigHandler.readConfig("toggle.time")) {
            startTime();
        }
        if ((boolean) ConfigHandler.readConfig("toggle.uptime")) {
            startUptime();
        }
    }

    /** Stops all active Threads.
     *
     * @since 1.0.0
     */
    public static void stopAll() {
        Settings.log.information("[stopAll] Trying to stop every thread");
        for (Thread active : activeThreads) {
            active.interrupt();
            Settings.log.information("[stopAll] Stopped " + active.getName());
        }
    }
}
