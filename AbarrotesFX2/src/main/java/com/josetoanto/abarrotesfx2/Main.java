package com.josetoanto.abarrotesfx2;

import controllers.AbarrotesMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Abarrote;

import java.io.IOException;

public class Main extends Application {
    Abarrote abarroteTiaKaty = new Abarrote();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("abarrotesMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abarrotes");
        stage.setScene(scene);
        stage.show();
        AbarrotesMenuController controllerAbarrotesMenu = fxmlLoader.getController();
        // Pasar la referencia de la ventana principal al controlador
        controllerAbarrotesMenu.sendStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}