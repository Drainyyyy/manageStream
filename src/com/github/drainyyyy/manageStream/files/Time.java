/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.files;

import com.github.drainyyyy.manageStream.utils.ConfigHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time extends FileHandler {
    private String path = (String) ConfigHandler.readConfig("directories.files");//TODO add files to config

    @Override
    public void fileMethod() throws InterruptedException {
        while(true) {
            String currentTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance());
            writeFile(path, currentTime);
            Thread.sleep(1000);
        }
    }

    @Override
    public void start() throws InterruptedException {
        fileMethod();
    }

    @Override
    public void delete() {
        deleteFile(path);
    }
}
