/*
 * Copyright (c) 2018 - 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.file;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class Date extends FileHandler {

    //TODO: Readd date.txt but with formatter
    String fileName = "date.txt";

    /**
     * Unfinished
     */
    @Override
    public void start() {



    }

    /**
     * Unfinished
     */
    @Override
    public void delete() {
        deleteFile(fileName);
    }
}
