package dev.sol.app.overview;

import dev.sol.core.controller.Controller;
import dev.sol.core.loader.LoaderFactory;
import dev.sol.models.Person;
import dev.sol.utils.DateUtil;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

public class PersonOverviewController extends Controller {
    @FXML
    private TableView<Person> personTableview;
    @FXML
    private TableColumn<Person, String> firstnameColumn;
    @FXML
    private TableColumn<Person, String> lastnameColumn;

    @FXML
    private Label firstnameLabel;
    @FXML
    private Label lastnameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalcodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    @FXML
    private void handleNew() {
        LoaderFactory.load("overview/form/PersonForm", app);
    }

    @FXML
    private void initialize() {
        firstnameColumn.setCellValueFactory(cell -> cell.getValue().firstnameProperty());
        lastnameColumn.setCellValueFactory(cell -> cell.getValue().lastnameProperty());
    }

    private Person selectedPerson;

    @Override
    protected void load_fields() {
        personTableview.setItems(app.getPersonlist());
        personTableview.setRowFactory(rowItem -> {
            TableRow<Person> row = new TableRow<>();
            ContextMenu rowMenu = new ContextMenu();

            MenuItem editMenu = new MenuItem("Edit Record");
            editMenu.setOnAction(e -> {
                LoaderFactory.load("overview/form/PersonForm",
                        app,
                        selectedPerson);
            });

            MenuItem deleteMenu = new MenuItem("Delete Record");
            deleteMenu.setOnAction(e -> {
                app.getPersonlist().remove(selectedPerson);
            });

            MenuItem clearMenu = new MenuItem("Clear All Records");
            rowMenu.getItems().addAll(editMenu, deleteMenu, clearMenu);
            clearMenu.setOnAction(e -> {
                app.getPersonlist().clear();
                personTableview.getSelectionModel().select(null);
            });

            row.contextMenuProperty().bind(Bindings.createObjectBinding(() -> {
                if (row.getItem() != null)
                    return rowMenu;
                return null;
            }, row.itemProperty()));

            return row;
        });
    }

    @Override
    protected void load_bindings() {
        _bind_labelProperties();
    }

    @Override
    protected void load_listeners() {
        personTableview.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedPerson = newValue;
                    _bind_labelProperties();
                });
    }

    private void _bind_labelProperties() {
        if (selectedPerson != null) {
            firstnameLabel.textProperty().bind(selectedPerson.firstnameProperty());
            lastnameLabel.textProperty().bind(selectedPerson.lastnameProperty());
            streetLabel.textProperty().bind(selectedPerson.streetProperty());
            cityLabel.textProperty().bind(selectedPerson.cityProperty());
            postalcodeLabel.textProperty().bind(selectedPerson.postalcodeProperty());
            birthdayLabel.textProperty().bind(Bindings.createStringBinding(() -> {
                return DateUtil.format(selectedPerson.getBirthdate());
            }, selectedPerson.birthdateProperty()));
        } else {
            firstnameLabel.textProperty().unbind();
            lastnameLabel.textProperty().unbind();
            streetLabel.textProperty().unbind();
            cityLabel.textProperty().unbind();
            postalcodeLabel.textProperty().unbind();
            birthdayLabel.textProperty().unbind();

            firstnameLabel.setText("---");
            lastnameLabel.setText("---");
            streetLabel.setText("---");
            cityLabel.setText("---");
            postalcodeLabel.setText("---");
            birthdayLabel.setText("---");
        }
    }
}
