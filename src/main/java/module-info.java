module com.example.fxwithudp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;


    opens com.example.fxwithudp to javafx.fxml;
    exports com.example.fxwithudp;
}