/*
 * Copyright (c) 2018 - 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.file;

public class Date extends FileHandler {

    //TODO: Readd date.txt but with formatter
    String fileName = "date.txt";

    @Override
    public void start() {



    }

    @Override
    public void delete() {
        deleteFile(fileName);
    }
}
