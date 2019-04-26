/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.core;

import java.io.File;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class manageStream {

    //TODO Documentation
    //FIXME Rework log and try catch log; make equal
    //FIXME equal documentation and spaces between code
    //TODO clean up code
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

        if (!appDir.exists()) {
            appDir.mkdir();
            Settings.log.notification("Created app directory");
        }
        if (!themesDir.exists()) {
            themesDir.mkdir();
            Settings.log.notification("Created themes directory");
        }
    }

}
