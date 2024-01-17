module com.example.huffmanstandard {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.huffmanstandard to javafx.fxml;
    exports com.example.huffmanstandard;
}