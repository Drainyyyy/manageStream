/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.core;

import com.github.drainyyyy.manageStream.utils.ConfigHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class Controller {
//TODO documentate
    @FXML
    public Button closeButton, minimizeButton, indexNavButton, dashboardNavButton, toggleNavButton, infoNavButton, changelogButton, indexPanelButton;

    private boolean indexStarted = false;
    private String fxmlPath = "/fxml/";

    private URL getClasspath(String filename) {
        return getClass().getResource(filename);
    }

    private static void openWebsite(String link) throws Exception {
        Desktop.getDesktop().browse(new URI(link));
    }

    private Scene getScene(String filename) {
        Parent loadedFxml = null;
        try {
            loadedFxml = FXMLLoader.load(getClasspath(fxmlPath + filename));
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("--- Cause ---");
            e.getCause();
            System.out.println("--- Message ---");
            e.getMessage();
        }
        return new Scene(loadedFxml);
    }

    @FXML
    private void initialize() {
        /*
        Scene index = getScene("index.fxml");
        Scene dashboard = getScene("dashboard.fxml");
        Scene toggle = getScene("toggle.fxml");
        Scene info = getScene("info.fxml");

        Scene currentScene = closeButton.getScene();

        if (currentScene.equals(index)) {
            if (indexStarted) {
                indexPanelButton.setId("button-red");
                indexPanelButton.setText("Stop Active");
                indexStarted = false;
            } else {
                indexPanelButton.setId("button-green");
                indexPanelButton.setText("Start Enabled");
                indexStarted = true;
            }
        }
        FIXME infinite error loop
         */
    }

    @FXML
    public void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        if (ConfigHandler.readWrite("etc.delete_files_on_close", false).equals(true)) {
            FeatureHandler.deleteAll();
        }
    }

    @FXML
    public void minimizeButtonAction(ActionEvent event) {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void indexNavButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) indexNavButton.getScene().getWindow();
        Parent index = FXMLLoader.load(getClasspath(fxmlPath + "index.fxml"));
        stage.setScene(new Scene(index));
    }

    @FXML
    public void dashboardNavButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardNavButton.getScene().getWindow();
        Parent dashboard = FXMLLoader.load(getClasspath(fxmlPath + "dashboard.fxml"));
        stage.setScene(new Scene(dashboard));
    }

    @FXML
    public void toggleNavButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) toggleNavButton.getScene().getWindow();
        Parent toggle = FXMLLoader.load(getClasspath(fxmlPath + "toggle.fxml"));
        stage.setScene(new Scene(toggle));
    }

    @FXML
    public void infoNavButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) infoNavButton.getScene().getWindow();
        Parent info = FXMLLoader.load(getClasspath(fxmlPath + "info.fxml"));
        stage.setScene(new Scene(info));
    }

    @FXML
    public void changelogInfoButtonAction(ActionEvent event) throws Exception {
        openWebsite("https://github.com/Drainyyyy/manageStream/tree/master/CHANGELOG.md");
    }

    @FXML
    public void indexPanelButtonAction(ActionEvent event) {
        if (!indexStarted) {
            indexPanelButton.setId("button-red");
            indexPanelButton.setText("Stop Active");
            indexStarted = true;
        } else {
            indexPanelButton.setId("button-green");
            indexPanelButton.setText("Start Enabled");
            indexStarted = false;
        }
        System.out.println(indexStarted);
    }
}
