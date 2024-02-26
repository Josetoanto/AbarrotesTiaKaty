package controllers;
import com.josetoanto.abarrotesfx2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Abarrote;
import models.Producto;
import models.Venta;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;


public class VentasMenuController {

        @FXML
        private Label lbl_totalSemanal;

        @FXML
        private TextArea txta_ReporteVentas;
        private Stage stage;

        private Abarrote abarrote;

        @FXML
        void btm_AgregarVenta(MouseEvent event) {
                if (!abarrote.getInventario().isEmpty()) {
                        TextInputDialog dialog = new TextInputDialog();
                        dialog.setTitle("Agregar venta");
                        dialog.setHeaderText("Nombre del producto:");
                        Optional<String> result = dialog.showAndWait();
                        result.ifPresent(productName -> {
                                int iterator = 0;
                                int iteratorAux = 0;
                                // Buscar el producto en el inventario
                                Producto productoEncontrado = null;
                                for (Producto producto : abarrote.getInventario()) {
                                        if (producto.getName().equalsIgnoreCase(productName)) {
                                                productoEncontrado = producto;
                                                iteratorAux = iterator;
                                        }
                                        iterator++;
                                }
                                if (productoEncontrado != null) {
                                        // El producto está en stock
                                        dialog.setTitle("Agregar venta");
                                        dialog.setHeaderText("Ingrese cantidad de producto");
                                        Optional<String> cantidadAVender = dialog.showAndWait();
                                        Producto finalProductoEncontrado = productoEncontrado;
                                        int finalIteratorAux = iteratorAux;
                                        cantidadAVender.ifPresent(productQuantity -> {
                                                if (abarrote.getInventario().get(finalIteratorAux).getQuantity()>=Integer.parseInt(cantidadAVender.orElse("0"))) {
                                                        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                                                        ButtonType botonSi = new ButtonType("Sí");
                                                        ButtonType botonNo = new ButtonType("No");
                                                        alert1.getButtonTypes().setAll(botonSi, botonNo);
                                                        alert1.setTitle("Agregar venta");
                                                        alert1.setHeaderText("¿El total es: " + (finalProductoEncontrado.getPriece()*Integer.parseInt(cantidadAVender.orElse("0"))) + " .¿Confirma la venta?");
                                                        alert1.showAndWait().ifPresent(response -> {
                                                                if (response == botonSi) {
                                                                        LocalDate todayDate = LocalDate.now();
                                                                        Venta newVenta = new Venta();
                                                                        newVenta.setFechaDeVenta(todayDate);
                                                                        newVenta.setTotal((int) (finalProductoEncontrado.getPriece()*Integer.parseInt(cantidadAVender.orElse("0"))));
                                                                        abarrote.getInventario().get(finalIteratorAux).restarCantidad(Integer.parseInt(cantidadAVender.orElse("0")));
                                                                        abarrote.agregarVenta(newVenta);
                                                                        abarrote.setTotalSemanal(newVenta.getTotal());
                                                                        verReporteVentas();
                                                                        setTotalSemanal();
                                                                } else {
                                                                        Alert cancel = new Alert(Alert.AlertType.INFORMATION);
                                                                        cancel.setTitle("Agregar venta");
                                                                        cancel.setHeaderText("Venta cancelada");
                                                                        cancel.show();
                                                                }
                                                        });
                                                } else {
                                                        Alert cancel = new Alert(Alert.AlertType.INFORMATION);
                                                        cancel.setTitle("Agregar venta");
                                                        cancel.setHeaderText("Venta cancelada, cantidad insuficiente en stock");
                                                        cancel.show();
                                                }
                                        });
                                } else {
                                        // No se encontró el producto
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Agregar venta");
                                        alert.setHeaderText("No se encontró el producto en stock.");
                                        alert.show();
                                }
                        });
                } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("No hay inventario en stock.");
                        alert.show();
                }
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

        public void verReporteVentas(){
                txta_ReporteVentas.setText(abarrote.devolverVentas());
        }

        @FXML
        void btm_ReiniciarSemana(MouseEvent event) {
                this.abarrote.borrarListaVentas();
                this.abarrote.borrarTotalSemanal();
                verReporteVentas();
                setTotalSemanal();
        }

        public void setPrimaryStage(Stage stage) {
                this.stage = stage;
        }

        public void setAbarrote(Abarrote abarrote) {
                this.abarrote = abarrote;
        }

        public void setTotalSemanal() {
                lbl_totalSemanal.setText("Total:" + abarrote.getTotalSemanal());
        }
}