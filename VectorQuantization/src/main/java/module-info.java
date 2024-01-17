module com.example.vectorquantizationgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.vectorquantizationgui to javafx.fxml;
    exports com.example.vectorquantizationgui;
}