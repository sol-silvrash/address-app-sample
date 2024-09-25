package dev.sol.app.overview.form;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import dev.sol.App;
import dev.sol.core.controller.Controller;
import dev.sol.models.Person;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonFormController extends Controller {
    @FXML
    private TextField firstnameField;
    @FXML
    private Label firstnameError;

    @FXML
    private TextField lastnameField;
    @FXML
    private Label lastnameError;

    @FXML
    private TextField streetField;
    @FXML
    private Label streetError;

    @FXML
    private TextField cityField;
    @FXML
    private Label cityError;

    @FXML
    private TextField postalcodeField;
    @FXML
    private Label postalcodeError;

    @FXML
    private DatePicker birthdayField;
    @FXML
    private Label birthdayError;

    @FXML
    private Button submitButton;

    @FXML
    private void handleSubmit() {
        formStage.close();
    }

    @FXML
    private void handleCancel() {
        formStage.close();
    }

    private Person person;
    private Person temp_person;
    private Stage formStage;

    public void load(App app, Stage stage, List<Serializable> params) {
        formStage = stage;
        super.load(app, params);
    }

    @Override
    protected void load_fields() {
        person = (Person) getParameter(0);
        temp_person = new Person(person);
    }

    @Override
    protected void load_bindings() {
        firstnameField.textProperty().bindBidirectional(temp_person.firstnameProperty());
        firstnameError.visibleProperty().bind(Bindings.isEmpty(temp_person.firstnameProperty()));

        lastnameField.textProperty().bindBidirectional(temp_person.lastnameProperty());
        lastnameError.visibleProperty().bind(Bindings.isEmpty(temp_person.lastnameProperty()));

        streetField.textProperty().bindBidirectional(temp_person.streetProperty());
        streetError.visibleProperty().bind(Bindings.isEmpty(temp_person.streetProperty()));

        cityField.textProperty().bindBidirectional(temp_person.cityProperty());
        cityError.visibleProperty().bind(Bindings.isEmpty(temp_person.cityProperty()));

        postalcodeField.textProperty().bindBidirectional(temp_person.postalcodeProperty());
        postalcodeError.visibleProperty().bind(Bindings.isEmpty(temp_person.postalcodeProperty()));

        birthdayField.valueProperty().bindBidirectional(temp_person.birthdateProperty());
        BooleanBinding checkBirthdateBinding = Bindings.createBooleanBinding(() -> {
            LocalDate birthdate = temp_person.getBirthdate();
            return birthdate.isAfter(LocalDate.now().minusYears(18));
        }, temp_person.birthdateProperty());
        birthdayError.visibleProperty().bind(checkBirthdateBinding);

        BooleanBinding checkErrorBinding = Bindings
                .or(firstnameError.visibleProperty(),
                        lastnameError.visibleProperty())
                .or(streetError.visibleProperty())
                .or(cityError.visibleProperty())
                .or(postalcodeError.visibleProperty())
                .or(birthdayError.visibleProperty());
        submitButton.disableProperty().bind(checkErrorBinding);
    }

    @Override
    protected void load_listeners() {
        birthdayField.getEditor().setOnMouseClicked(e -> {
            birthdayField.show();
        });
    }
}
