/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.core;

import com.github.drainyyyy.manageStream.utils.ConfigHandler;

import java.io.File;
import java.io.IOException;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class manageStream {

    //TODO Documentation
    public static void main(String[] args) {
        checkAppFiles();
    }

    /** Checks if the folders and needed files of the app exist
     * <br>
     * Missing folders and files get created
     *
     * @see Settings
     *
     * @since 1.0.0
     */
    private static void checkAppFiles() {
        File appDir = new File(Settings.appDir);
        File themesDir = new File(Settings.themeDir);
        File config = new File(Settings.appDir + "/config.json");

        if (!appDir.exists()) {
            appDir.mkdir();
            Settings.log.notification("Created app directory");
        }
        if (!themesDir.exists()) {
            themesDir.mkdir();
            Settings.log.notification("Created themes directory");
        }
        if (!config.exists()) {
            try {
                config.createNewFile();
                Settings.log.notification("Created config file");
            } catch (IOException e) {
                e.printStackTrace();
                Settings.log.warning(e.getMessage());
            }
        }
        try {
            ConfigHandler.checkConfig();
        } catch (Exception e) {
            Settings.log.error(e.getMessage());
            e.printStackTrace();
        }
    }

}
