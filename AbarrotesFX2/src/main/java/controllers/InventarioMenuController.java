package controllers;

import com.josetoanto.abarrotesfx2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Abarrote;
import models.Comida;
import models.Producto;

import java.io.IOException;
import java.util.Optional;

public class InventarioMenuController {
    @FXML
    private TextArea txa_InventarioenStock;
    private Stage stage;
    private Abarrote abarrote;

    @FXML
    void btm_AgregarProducto(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType botonSi = new ButtonType("Sí");
        ButtonType botonNo = new ButtonType("No");
        alert.getButtonTypes().setAll(botonSi, botonNo);
        alert.setTitle("Tipo de producto");
        alert.setHeaderText("¿El producto es una comida?");
        alert.showAndWait().ifPresent(response -> {
            if (response == botonSi) {
                Comida newComida = getComida();
                abarrote.agregarProducto(newComida);
                verReporte();
            } else {
                Producto newProducto = new Producto();
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Agregar Producto");

                // Ingresar el nombre del producto
                dialog.setHeaderText("Nombre del producto:");
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(newProducto::setName);

                // Ingresar la cantidad de producto
                dialog.setHeaderText("Cantidad de producto:");
                Optional<String> cantidad = dialog.showAndWait();
                cantidad.ifPresent(cantidad1 -> newProducto.setQuantity(Integer.parseInt(cantidad1))); // Convertir a entero

                // Ingresar el precio del producto
                dialog.setHeaderText("Precio del producto en enteros:");
                Optional<String> precio = dialog.showAndWait();
                precio.ifPresent(texto -> newProducto.setPriece(Integer.parseInt(texto))); // Convertir a entero

                // Aquí puedes agregar el producto a tu lista o realizar la acción deseada
                 abarrote.agregarProducto(newProducto);
                 verReporte();
            }
        });
    }

    private static Comida getComida() {
        Comida newComida = new Comida();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Comida");

        // Ingresar el nombre del producto
        dialog.setHeaderText("Nombre del producto:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newComida::setName);

        // Ingresar el tipo de comida
        dialog.setHeaderText("Tipo de comida:");
        Optional<String> tipo = dialog.showAndWait();
        tipo.ifPresent(newComida::setTipoDeComida);

        // Ingresar la fecha de caducidad del producto
        dialog.setHeaderText("Fecha de caducidad del producto:");
        Optional<String> fecha = dialog.showAndWait();
        fecha.ifPresent(newComida::setFechaCaducidad);

        // Ingresar la cantidad de producto
        dialog.setHeaderText("Cantidad de producto:");
        Optional<String> cantidad = dialog.showAndWait();
        cantidad.ifPresent(texto -> newComida.setQuantity(Integer.parseInt(texto))); // Convertir a entero

        // Ingresar el precio del producto
        dialog.setHeaderText("Precio del producto en enteros:");
        Optional<String> precio = dialog.showAndWait();
        precio.ifPresent(texto -> newComida.setPriece(Integer.parseInt(texto))); // Convertir a entero

        // Aquí puedes devolver el objeto newComida o realizar la acción deseada
        return newComida;
    }

    public void verReporte(){
        txa_InventarioenStock.setText(abarrote.devolverInventario());
    }

    @FXML
    void btm_Salir(MouseEvent event) throws IOException {
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("abarrotesMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abarrotes");
        stage.setScene(scene);
        stage.show();
        AbarrotesMenuController controllerAbarrotesMenu = fxmlLoader.getController();
        controllerAbarrotesMenu.sendStage(stage);
        controllerAbarrotesMenu.setAbarrote(abarrote);
    }

    public void setPrimaryStage(Stage stage) {
        this.stage = stage;
    }

    public void setAbarrote(Abarrote abarrote) {
        this.abarrote = abarrote;
    }
    @FXML
    void btm_LimpiarProducto(MouseEvent event) {
        this.abarrote.borrarInventario();
        verReporte();
    }
}
