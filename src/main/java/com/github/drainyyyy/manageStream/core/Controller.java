/*
 * Copyright (c) 2019. Drainyyy
 * This project is covered by MIT License
 * https://opensource.org/licenses/MIT
 */

package com.github.drainyyyy.manageStream.core;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * @author Drainyyy
 * https://github.com/Drainyyyy
 */
public class Controller {

    @FXML
    public Button closeButton, minimizeButton, indexNavButton, dashboardNavButton, toggleNavButton, infoNavButton, changelogButton, indexPanelButton;

    boolean indexStarted = false;

    private String path = "src/gui/fxml/";

    private static void openWebsite(String link) throws Exception {
        Desktop.getDesktop().browse(new URI(link));
    }

    @FXML
    public void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void minimizeButtonAction(ActionEvent event) {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void indexNavButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) indexNavButton.getScene().getWindow();
        URL file = new File(path + "index.fxml").toURI().toURL();
        Parent index = FXMLLoader.load(file);
        stage.setScene(new Scene(index));
    }

    @FXML
    public void dashboardNavButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardNavButton.getScene().getWindow();
        URL file = new File(path + "dashboard.fxml").toURI().toURL();
        Parent dashboard = FXMLLoader.load(file);
        stage.setScene(new Scene(dashboard));
    }

    @FXML
    public void toggleNavButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) toggleNavButton.getScene().getWindow();
        URL file = new File(path + "toggle.fxml").toURI().toURL();
        Parent toggle = FXMLLoader.load(file);
        stage.setScene(new Scene(toggle));
    }

    @FXML
    public void infoNavButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) infoNavButton.getScene().getWindow();
        URL file = new File(path + "info.fxml").toURI().toURL();
        Parent info = FXMLLoader.load(file);
        stage.setScene(new Scene(info));
    }

    @FXML
    public void changelogInfoButtonAction(ActionEvent event) throws Exception {
        openWebsite("https://github.com/Drainyyyy/manageStream/tree/master/CHANGELOG.md");
    }

    @FXML
    public void indexPanelButtonAction(ActionEvent event) {
        if (indexStarted) {
            indexPanelButton.setId("button-green");
            indexPanelButton.setText("Start Enabled");
            indexStarted = false;
        } else {
            indexPanelButton.setId("button-red");
            indexPanelButton.setText("Stop Active");
            indexStarted = true;
        }
    }
}
