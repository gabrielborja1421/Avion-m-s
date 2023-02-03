module com.example.aviones {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aviones to javafx.fxml;
    exports com.example.aviones;
    exports com.example.aviones.controllers;
    opens com.example.aviones.controllers to javafx.fxml;
}