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
public class FeatureHandler {
    private static Thread uptime = new Thread("uptime"),
            countdown = new Thread("countdown"),
            time = new Thread("time"),
            date = new Thread("date");

    private static String softwarePath = (String) ConfigHandler.readWrite("directories.software", null);
    private static String dashboardUrl = (String) ConfigHandler.readWrite("directories.dashboard", null);

    private static ArrayList<Thread> activeThreads = new ArrayList<>();
    private static ArrayList<Thread> allThreads = new ArrayList<>(Arrays.asList(uptime, countdown, time, date));

    private static void startFileCreator(String name, Thread thread, Runnable runnable) {
        thread = new Thread(runnable);
        thread.start();
        Settings.log.information("[start" + name.toUpperCase() + "] Started " + thread.getName() + " thread");
        activeThreads.add(thread);
    }

    /** TODO documentation
     *
     * @param name
     * @param thread
     */
    private static void stopFileCreator(String name, Thread thread) {
        thread.interrupt();
        activeThreads.remove(thread);
        Settings.log.information("[stop" + name.toUpperCase() + "] Stopped " + thread.getName() + " thread");
    }

    /** Checks if the software file exists and opens it if so.
     *
     * @since 1.0.0
     */
    public static void startSoftware() {
        File software = new File(softwarePath);
        if (!software.exists()) {
            return;
        }
        try {
            Runtime.getRuntime().exec(software.getAbsolutePath());
        } catch (IOException e) {
            Settings.log.exceptionHandler(e);
        }
    }

    /** Opens the dashboard.
     *
     * @since 1.0.0
     */
    public static void openDashboard() {
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
    public static void startUptime() {
        startFileCreator("uptime", uptime, () -> {
            try {
                new Uptime().start();
                Settings.log.information("[STATUS] started file creation");
            } catch (InterruptedException e) {
                Settings.log.exceptionHandler(e);
            }
        });
    }

    /** Lets the countdown thread execute Countdown.start();
     *
     * @see Countdown
     *
     * @since 1.0.0
     */
    public static void startCountdown() {
        startFileCreator("countdown", countdown, () -> {
            try {
                new Countdown().start();
                Settings.log.information("[STATUS] started file creation");
            } catch (InterruptedException e) {
                Settings.log.exceptionHandler(e);
            }
        });
    }

    /** Lets the tine thread execute Time.start();
     *
     * @see Time
     *
     * @since 1.0.0
     */
    public static void startTime() {
        startFileCreator("time", time, () -> {
            try {
                new Time().start();
                Settings.log.information("[STATUS] started file creation");
            } catch (InterruptedException e) {
                Settings.log.exceptionHandler(e);
            }
        });
    }

    /** Lets the date thread execute Date.start();
     *
     * @see Date
     *
     * @since 1.0.0
     */
    public static void startDate() {
        startFileCreator("date", date, () -> {
            try {
                new Date().start();
                Settings.log.information("[STATUS] started file creation");
            } catch (InterruptedException e) {
                Settings.log.exceptionHandler(e);
            }
        });
    }

    /** Stops the uptime thread and deletes the file.
     *
     * @see Uptime
     *
     * @since 1.0.0
     */
    public static void stopUptime() {
        stopFileCreator("uptime", uptime);
    }

    /** Stops the countdown thread and deletes the file.
     *
     * @see Countdown
     *
     * @since 1.0.0
     */
    public static void stopCountdown() {
        stopFileCreator("countdown", countdown);
    }

    /** Stops the time thread and deletes the file.
     *
     * @see Time
     *
     * @since 1.0.0
     */
    public static void stopTime() {
        stopFileCreator("time", time);
    }

    /** Stops the date thread and deletes the file.
     *
     * @see Date
     *
     * @since 1.0.0
     */
    public static void stopDate() {
        stopFileCreator("date", date);
    }

    public static void deleteAll() {
        new Date().delete();
        new Time().delete();
        new Countdown().delete();
        new Uptime().delete();
    }

    /** Starts all functions (if they are enabled).
     *
     * @see ConfigHandler
     *
     * @since 1.0.0
     */
    public static void startAll() {
        for (Thread func : allThreads) {
            String funcName = func.getName();
            boolean toggled = ConfigHandler.readWrite("toggle." + funcName, false).equals(true);

            if (toggled) {
                func.start();
            }
        }
    }

    /** Stops all active Threads.
     *
     * @since 1.0.0
     */
    public static void stopAll() {
        for (Thread active : activeThreads) {
            stopFileCreator(active.getName(), active);
        }
    }
}
