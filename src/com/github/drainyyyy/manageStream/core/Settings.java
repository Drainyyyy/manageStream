/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.core;

import com.github.drainyyyy.minimalLog.UncoloredLog;

import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class Settings {

    //general stuff
    public static final String version = "1.0.0a";
    public static final String repository_url = "https://github.com/Drainyyyy/manageStream";
    public static final String license = "https://opensource.org/licenses/MIT";

    //directories
    private static final String defaultDir = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
    public static final String appDir = defaultDir + "/manageStream";
    public static final String themeDir = appDir + "/themes";

    //log type
    public static final UncoloredLog log = new UncoloredLog(">", new SimpleDateFormat("dd.MM.yyyy ~ HH:mm:ss"), "|");

    //fonts
    public static final Font fontSmall = new Font("monospace", Font.PLAIN, 16);
    public static final Font fontNormal = new Font("monospace", Font.PLAIN, 20);
    public static final Font fontLarge = new Font("monospace", Font.BOLD, 25);
}
