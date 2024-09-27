module sol.addressapp {
    requires transitive javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;

    requires jakarta.xml.bind;

    requires java.prefs;

    requires net.datafaker;

    opens dev.sol.app to javafx.fxml;
    opens dev.sol.app.overview to javafx.fxml;
    opens dev.sol.app.overview.form to javafx.fxml;
    opens dev.sol.models.person to jakarta.xml.bind;

    exports dev.sol;
    exports dev.sol.models.person;
}
