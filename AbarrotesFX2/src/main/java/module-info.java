module com.josetoanto.abarrotesfx2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.josetoanto.abarrotesfx2 to javafx.fxml;
    exports com.josetoanto.abarrotesfx2;
    exports controllers;
    opens controllers to javafx.fxml;
}