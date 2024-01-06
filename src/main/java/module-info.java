module ma.enset.recsys {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;
    requires jbcrypt;


    opens ma.enset.recsys to javafx.fxml;
    exports ma.enset.recsys;
    exports ma.enset.recsys.controller;
    opens ma.enset.recsys.controller to javafx.fxml;
}