/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.files;

import com.github.drainyyyy.manageStream.utils.ConfigHandler;

import java.text.SimpleDateFormat;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class Date extends FileHandler {
    private String path = ConfigHandler.readConfig("directories.files") + "/date.txt";
    private String dateFormat = (String) ConfigHandler.readConfig("files.date.format");
    private SimpleDateFormat format = new SimpleDateFormat(dateFormat);
    private String currentDate = format.format(new Date());

    /** Checks the date every 5 minutes and if it has changed currentDate will get overwritten and wrote into the date.txt file and then it repeats.
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
            if (!format.format(new Date()).equals(currentDate)) {
                currentDate = format.format(new Date());
                writeFile(path, format.format(new Date()));
            }
            Thread.sleep(300000);
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
