module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.example.app to javafx.fxml;
    exports com.example.app;
}