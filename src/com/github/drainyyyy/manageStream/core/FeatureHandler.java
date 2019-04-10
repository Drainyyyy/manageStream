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
import java.util.Arrays;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
class FeatureHandler {
    private static Thread uptime, countdown, time, date;
    private static String softwarePath = (String) ConfigHandler.readConfig("directories.software");
    private static String dashboardUrl = (String) ConfigHandler.readConfig("directories.dashboard");


    /** Checks if the software file exists and opens it if so.
     *
     * @since 1.0.0
     */
    static void startSoftware() {
        File software = new File(softwarePath);
        if (!software.exists()) {
            Settings.log.error("Software file doesn't exist. [" + software + "]");
            return;
        }
        try {
            Runtime.getRuntime().exec(software.getAbsolutePath());
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
            } catch (InterruptedException e) {
                Settings.log.error(Arrays.toString(e.getStackTrace()));
            }
        });
        uptime.start();
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
            } catch (InterruptedException e) {
                Settings.log.error(Arrays.toString(e.getStackTrace()));
            }
        });
        countdown.start();
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
            } catch (InterruptedException e) {
                Settings.log.error(Arrays.toString(e.getStackTrace()));
            }
        });
        time.start();
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
            } catch (InterruptedException e) {
                Settings.log.error(Arrays.toString(e.getStackTrace()));
            }
        });
        date.start();
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
    }
}
