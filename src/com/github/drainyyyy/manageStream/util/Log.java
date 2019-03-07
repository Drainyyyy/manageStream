/*
 * Copyright (c) 2018 - 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
class Color {
    static String BLUE = "\u001b[34m";
    static String RED = "\u001b[31m";
    static String END = "\u001b[0m";
}

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class Log {

    /** Adds a timestamp and color to the log text
     *
     * @param text The text that gets a timestamp and colored.
     * @param color The color the text gets colored in.
     * @return "timestamp | colored text"
     *
     * @since 1.0
     */
    private static String formatText(String text, String color) {

        String timestamp = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss").format(new Date());

        String coloredText = color + text + Color.END;

        return timestamp + " | " + coloredText;
    }

    /** Information log (blue)
     *
     * @param text The text that gets logged.
     *
     * @since 1.0
     */
    public static void info(String text) {
        System.out.println(formatText(text, Color.BLUE));
    }

    /** Error log (red)
     *
     * @param text The text that gets logged.
     *
     * @since 1.0
     */
    public static void err(String text){
        System.out.println(formatText(text, Color.RED));
    }

}
