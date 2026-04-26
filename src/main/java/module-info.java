module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;


    opens com.example.app to javafx.fxml, com.fasterxml.jackson.databind;
    exports com.example.app;
}