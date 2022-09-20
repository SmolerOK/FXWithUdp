package com.example.fxwithudp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.lang.Object.*;
import java.lang.module.Configuration;

import org.apache.logging.log4j.*;

public class HelloApplication extends Application {

    private static final Logger LOGGER = LogManager.getLogger(HelloApplication.class);

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Artsec Marvel");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}