/*
 * Copyright (c) 2018 - 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.file;

import java.io.BufferedOutputStream;
import java.io.File;
import com.github.drainyyyy.manageStream.util.ConfigReader;
import com.github.drainyyyy.manageStream.util.Log;

import java.io.FileOutputStream;
import java.util.HashMap;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
abstract class FileHandler implements com.github.drainyyyy.manageStream.file.File {

    private HashMap<String, String> configDirectories;
    private HashMap<String, HashMap> config = ConfigReader.readConfig("config.json");

    FileHandler() {

        this.configDirectories = config.get("directories");

    }

    /** The writer for the files.
     * <br>
     * The directory where to write gets taken from the config.<br>
     * For the content that's getting written a buffered output stream gets created.
     *
     * @param content The content that's getting written in the file.
     * @param filename The name of the file.
     *
     * @see ConfigReader
     *
     * @since 1.0
     */
    void writeFile(String content, String filename) {

        String directory = this.configDirectories.get("app_dir");

        try {

            File file = new File(directory + filename);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            bos.write(content.getBytes());
            bos.flush();
            bos.close();

        } catch(Exception e) {
            Log.err(e.getMessage());
            e.printStackTrace();
        }

    }

    /** Deletes the respective file.
     *
     * If a file is no longer required it gets deleted by this function.
     *
     * @param filename The name of the file that will get deleted.
     *
     * @see ConfigReader
     *
     * @since 1.0
     */
    void deleteFile(String filename) {

        String directory = this.configDirectories.get("app_dir");
        File file = new File(directory + filename);

        if (file.delete()) {
            Log.info("Deleted " + filename + ". | [" + directory + "]");
        } else {
            Log.err("Couldn't delete " + filename + ". | [" + directory + "]");
        }

    }

}
