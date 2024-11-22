module com.example.stepbystepnow {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.stepbystepnow to javafx.fxml;
    exports com.example.stepbystepnow;
}