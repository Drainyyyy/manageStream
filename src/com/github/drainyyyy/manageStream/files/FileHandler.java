/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.files;

import com.github.drainyyyy.manageStream.core.Settings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
abstract class FileHandler implements File {

    /** Gets the file with the given path and writes the given content into it.
     *
     * @param path A string as the path for the file to write in.
     * @param content The content that will get written into the file.
     *
     * @since 1.0.0
     */
    static void writeFile(String path, Object content) {
        java.io.File file = new java.io.File(path);//TODO check if file exists and if not return and log
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(content.toString());
            writer.flush();
        } catch (IOException e) {
            Settings.log.warning(Arrays.toString(e.getStackTrace()));
        }
    }

    /** Deletes the file with the given path.
     *
     * @param path A string with the path to the file that should get deleted.
     *
     * @since 1.0.0
     */
    static void deleteFile(String path) {
        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            Settings.log.warning("[DELETE] The file [" + path + "] doesn't even exist.");
            return;
        }
        file.delete();
    }
}
