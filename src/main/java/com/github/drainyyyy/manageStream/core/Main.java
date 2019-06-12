/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.core;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class Main extends Application {
    private double guiXOffset = 0;
    private double guiYOffset = 0;

    //TODO Documentation
    //FIXME Rework log and try catch log; make equal
    //FIXME equal documentation and spaces between code
    //TODO clean up code
    //TODO on info version check
    public static void main(String[] args) {
        launch(args);
        //checkAppFiles();
    }

    //TODO documentation
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent index = FXMLLoader.load(getClass().getResource("/fxml/new.fxml"));

        primaryStage.setTitle("manageStream");
        primaryStage.setScene(new Scene(index));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }//TODO Make movable

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
