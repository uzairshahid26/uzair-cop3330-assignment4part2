module ucf.assignments {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.testng;
    requires junit;

    opens ucf.assignments to javafx.fxml;
    exports ucf.assignments;
}