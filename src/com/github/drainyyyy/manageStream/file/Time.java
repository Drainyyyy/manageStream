/*
 * Copyright (c) 2018 - 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.file;

import com.github.drainyyyy.manageStream.util.Log;

import java.text.SimpleDateFormat;

public class Time extends FileHandler {

    String fileName = "time.txt";

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

    @Override
    public void delete() {

    }
}
