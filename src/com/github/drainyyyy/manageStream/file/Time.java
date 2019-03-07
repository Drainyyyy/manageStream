/*
 * Copyright (c) 2018 - 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.file;
import com.github.drainyyyy.manageStream.util.Log;

import java.text.SimpleDateFormat;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class Time extends FileHandler {

    String fileName = "time.txt";

    /** Creates a time file.
     * <br>
     * Writes the current time in `HH:mm:ss` format into a file.
     *
     * @see FileHandler
     * @see File
     *
     * @since 1.0
     */
    @Override
    public void start() {

        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
        String time = timeFormatter.format(new Date());

        while(true) {

            writeFile(time, fileName);

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

    }
}
