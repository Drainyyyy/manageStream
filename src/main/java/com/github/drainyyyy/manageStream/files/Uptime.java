/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.files;

import com.github.drainyyyy.manageStream.utils.ConfigHandler;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class Uptime extends FileHandler{
    private String path = ConfigHandler.readConfig("directories.files") + "/uptime.txt";
    private int timeDone = (int) ConfigHandler.readConfig("files.uptime.time_done");
    private int seconds, minutes, hours;

    /** Adds one to the seconds every second and if seconds is 60 or more it will set to 0 and if minutes is 60 or more the hours will get one more.<br>
     * seconds, minutes and hours will be converted to 2 digits (whether it's less than 9 or more).
     *
     * @see File
     * @see FileHandler
     *
     * @throws InterruptedException
     *
     * @since 1.0.0
     */
    @Override
    public void fileMethod() throws InterruptedException {
        while (true) {
            seconds++;
            if (seconds >= 60) {
                minutes++;
                seconds -= 60;
                if (minutes >= 60) {
                    hours++;
                    minutes -= 60;
                }
            }
            String writable = String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
            writeFile(path, writable);
            Thread.sleep(1000);
        }
    }

    /** Splits timeDone which is given in seconds into seconds, minutes and hours.
     *
     * @see File
     * @see FileHandler
     *
     * @throws InterruptedException
     *
     * @since 1.0.0
     */
    @Override
    public void start() throws InterruptedException {
        while (timeDone > 60) {
            minutes++;
            timeDone -= 60;
            if (minutes >= 60) {
                hours++;
                minutes -= 60;
            }
        }
        seconds = timeDone;
        fileMethod();
    }

    /** The file will get deleted.
     *
     * @see File
     * @see FileHandler
     *
     * @since 1.0.0
     */
    @Override
    public void delete() {
        deleteFile(path);
    }
}
