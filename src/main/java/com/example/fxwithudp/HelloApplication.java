package com.example.fxwithudp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloApplication extends Application {

    private static final Logger LOGGER = LogManager.getLogger(HelloApplication.class);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Artsec Marvel");
            stage.setScene(scene);
            stage.show();


            stage.setOnCloseRequest(windowEvent -> {
                LOGGER.info("Закрытие приложения.");
            });
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public static void main(String[] args) {
        try {
            LOGGER.info("Открытие приложения.");
            LOGGER.trace("TRACE Открытие приложения.");
            LOGGER.debug("DEBUG Открытие приложения.");
            LOGGER.warn("WARN Открытие приложения.");
            LOGGER.error("ERROR Открытие приложения.");
            LOGGER.fatal("FATAL Открытие приложения.");

            launch();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}