module com.banklia {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.banklia to javafx.fxml;
    exports com.banklia;
}
