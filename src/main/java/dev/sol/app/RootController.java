package dev.sol.app;

import java.io.File;

import dev.sol.App;
import dev.sol.core.controller.Controller;
import dev.sol.libs.xml.JAXBLib;
import dev.sol.models.person.PersonlistWrapper;
import dev.sol.utils.RegUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class RootController extends Controller {
    private static final String REGISTRY_KEY = "xmlFilePath";

    @FXML
    private void handleNew() {
        app.getApplicationStage().setTitle("Address App - New");
        RegUtil.setValueToRegistry(REGISTRY_KEY, null, App.class);
        app.getPersonlist().clear();
    }

    @FXML
    private void handleImport() {

    }

    @FXML
    private void handleSave() {
        if (RegUtil.getValueFromRegistry(REGISTRY_KEY, App.class) != null) {
            File personFile = new File(RegUtil.getValueFromRegistry(REGISTRY_KEY, App.class));
            _save_xmlFile(personFile);
        } else
            handleSaveAs();
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "/Documents/"));
        fc.getExtensionFilters().add(new ExtensionFilter("XML files(*.xml)", "*.xml"));
        File file = fc.showSaveDialog(app.getApplicationStage());
        if (file != null) {
            if (!file.getPath().endsWith(".xml"))
                file = new File(file.getPath() + ".xml");
            handleSave();
        }
    }

    private void _load_xmlFile(File file) {
        app.getApplicationStage().setTitle("Address App - " + file.getPath());
        PersonlistWrapper personlistWrapper = JAXBLib.deserialize(file, PersonlistWrapper.class);
        app.getPersonlist().clear();
        app.getPersonlist().addAll(personlistWrapper.getPersonlist());
        RegUtil.setValueToRegistry(REGISTRY_KEY, file.getPath(), App.class);
    }

    private void _save_xmlFile(File file) {
        app.getApplicationStage().setTitle("Address App - " + file.getPath());
        PersonlistWrapper personlistWrapper = new PersonlistWrapper(app.getPersonlist());
        if (JAXBLib.serialize(file, personlistWrapper))
            RegUtil.setValueToRegistry(REGISTRY_KEY, file.getPath(), App.class);
        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file: " + file.getPath());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleClose() {
        System.exit(0);
    }

    @FXML
    private void handleStatistics() {
        // TODO: creates a chart that will display the average age
    }

    @FXML
    private void handleRandomizeData() {
        // TODO: clears and creates a randomized dataset to personlist
    }

    @FXML
    private void handleClearData() {
        // TODO: clears all data
    }

    @FXML
    private void handleAbout() {
        // TODO: displays info about software and author
    }

    @Override
    protected void load_fields() {
        _initialize_dataset_from_xmlFile();
    }

    private void _initialize_dataset_from_xmlFile() {
        if (RegUtil.getValueFromRegistry(REGISTRY_KEY, App.class) == null)
            handleNew();
        else {
            File file = new File(RegUtil.getValueFromRegistry(REGISTRY_KEY, App.class));
            if (file.exists())
                _load_xmlFile(file);
            else
                handleNew();
        }
    }

    @Override
    protected void load_bindings() {
    }

    @Override
    protected void load_listeners() {
    }
}
