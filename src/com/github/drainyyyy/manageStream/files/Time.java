/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.files;

import com.github.drainyyyy.manageStream.utils.ConfigHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class Time extends FileHandler {
    private String path = (String) ConfigHandler.readConfig("directories.files");//TODO add files to config

    /** Every second the current system time gets updated and written into the file.
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
        while(true) {
            String currentTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance());
            writeFile(path, currentTime);
            Thread.sleep(1000);
        }
    }

    /** start does nothing here just calling fileMethod().
     *
     * @throws InterruptedException
     *
     * @since 1.0.0
     */
    @Override
    public void start() throws InterruptedException {
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
