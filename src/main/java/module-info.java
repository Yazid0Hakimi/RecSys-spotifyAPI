module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}