/*
 * Copyright (c) 2018 - 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.util;

import java.text.SimpleDateFormat;
import java.util.Date;

class Color {
    static String BLUE = "\u001b[34m";
    static String RED = "\u001b[31m";
    static String END = "\u001b[0m";
}

public class Log {

    private static String formatText(String text, String color) {

        String timestamp = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss").format(new Date());

        String coloredText = color + text + Color.END;

        return timestamp + " | " + coloredText;
    }

    public static void info(String text) {
        System.out.println(formatText(text, Color.BLUE));
    }

    public static void err(String text){
        System.out.println(formatText(text, Color.RED));
    }

}
