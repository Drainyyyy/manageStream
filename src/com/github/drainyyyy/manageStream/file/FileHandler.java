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

abstract class FileHandler implements com.github.drainyyyy.manageStream.file.File {

    private HashMap<String, String> configDirectories;
    private HashMap<String, HashMap> config = new ConfigReader("config.json").readConfig();

    FileHandler() {

        this.configDirectories = config.get("directories");

    }

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
