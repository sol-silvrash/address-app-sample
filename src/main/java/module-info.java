module sol.addressapp {
    requires transitive javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;

    requires net.datafaker;

    opens dev.sol.app to javafx.fxml;
    opens dev.sol.app.overview to javafx.fxml;
    opens dev.sol.app.overview.form to javafx.fxml;

    exports dev.sol;
    exports dev.sol.models;
}
