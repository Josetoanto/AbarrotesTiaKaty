package controllers;

import com.josetoanto.abarrotesfx2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Abarrote;

import java.io.IOException;

public class AbarrotesMenuController {

    private Stage stage;

    private Abarrote abarrote = new Abarrote();

    @FXML
    private Label tittleLabel;

    @FXML
    private Button ventasButton;

    @FXML
    void OnVentasButtonClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("VentasMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abarrotes");
        stage.setScene(scene);
        stage.show();
        VentasMenuController ventasMenuController = fxmlLoader.getController();
        ventasMenuController.setPrimaryStage(stage);
        ventasMenuController.setAbarrote(abarrote);
        ventasMenuController.verReporteVentas();
        ventasMenuController.setTotalSemanal();
    }

    @FXML
    void onInventarioButtonClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("InventarioMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abarrotes");
        stage.setScene(scene);
        stage.show();
        InventarioMenuController inventarioMenuController = fxmlLoader.getController();
        inventarioMenuController.setPrimaryStage(stage);
        inventarioMenuController.setAbarrote(abarrote);
        inventarioMenuController.verReporte();
    }
    public void sendStage(Stage stage) {
        this.stage = stage;
    }
    public void setAbarrote(Abarrote abarrote){
        this.abarrote = abarrote;
    }

    @FXML
    void btm_Salir(MouseEvent event) {
        stage.close();
    }
}
