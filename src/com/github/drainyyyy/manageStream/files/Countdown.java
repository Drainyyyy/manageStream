/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.files;

import com.github.drainyyyy.manageStream.core.Settings;
import com.github.drainyyyy.manageStream.utils.ConfigHandler;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class Countdown extends FileHandler {
    private String path = (String) ConfigHandler.readConfig("directories.files");//TODO add files to config
    private int countdown = (int) ConfigHandler.readConfig("files.countdown.countdown");
    private String timeUpMessage = (String) ConfigHandler.readConfig("files.countdown.time_up_message");
    private int minute = 0;
    private int second = 0;
    private boolean timeUp = false;

    /** The minutes (if given) and seconds get printed in the respective file.<br>
     * Seconds and minutes get formatted so there is always two digits.<br>
     * As long as the countdown hasn't reached 0 (than timeUp will be set true), the seconds and minutes get updated and written into the file.<br>
     * As soon as the countdown has reached 0 the time up message (from the config) gets printed into the file and the while clause stops.
     *
     * @see File
     * @see FileHandler
     *
     * @since 1.0.0
     */
    @Override
    public void fileMethod() throws InterruptedException {
        String writable;
            while(!timeUp) {
                if (minute > 0) {
                    writable = String.format("%02d", minute) + ":" + String.format("%02d", second);
                } else {
                    writable = String.format("%02d", second);
                }
                second--;
                if (second <= 0) {
                    minute--;
                    if (minute <= 0 && second == 0) {
                        writable = timeUpMessage;
                        timeUp = true;
                    }
                }
                writeFile(path, writable);
                Thread.sleep(1000);
            }
    }

    /** If the countdown is less or equal to 0, this process will be canceled. Otherwise the countdown would instantly be over.<br>
     * If not than the countdown which is given in seconds will be split in minutes and seconds.<br>
     * After that fileMethod() will be called.
     *
     * @see File
     * @see FileHandler
     *
     * @since 1.0.0
     */
    @Override
    public void start() throws InterruptedException {
        if (countdown <= 0) {
            Settings.log.warning("[COUNTDOWN] The countdown is less or equal to 0. (COUNTDOWN: " + countdown + ")");
            return;
        }
        while (countdown > 60) {
            minute++;
            countdown = countdown - 60;
        }
        second = countdown;
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
