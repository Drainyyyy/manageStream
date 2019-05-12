/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class Main extends Application {

    //TODO Documentation
    //FIXME Rework log and try catch log; make equal
    //FIXME equal documentation and spaces between code
    //TODO clean up code
    public static void main(String[] args) {
        launch(args);
        checkAppFiles();
    }

    //TODO documentation
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL indexPath = getClass().getResource("/fxml/index.fxml");
        Parent index = FXMLLoader.load(indexPath);

        primaryStage.setScene(new Scene(index));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

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
