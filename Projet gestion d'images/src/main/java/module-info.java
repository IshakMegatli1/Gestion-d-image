module com.example.log121l5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.log121l5 to javafx.fxml;
    exports com.example.log121l5;
    exports com.example.log121l5.model;
    opens com.example.log121l5.model to javafx.fxml;
}