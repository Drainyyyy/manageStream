/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.files;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
interface File {

    /** The actual task the method will do.
     *
     * @since 1.0.0
     */
    void fileMethod() throws InterruptedException;

    /** Will be called to start the respective process.<br>
     * Here some basic things like getting values from the config or other things can be done.
     *
     * @since 1.0.0
     */
    void start() throws InterruptedException;

    /** Deletes the file.
     *
     * @since 1.0.0
     */
    void delete();
}
