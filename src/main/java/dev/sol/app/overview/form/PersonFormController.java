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
        if (params.isEmpty())
            app.getPersonlist().add(0, person);
        else {
            int personIdx = app.getPersonlist().indexOf(person);
            app.getPersonlist().set(personIdx, person);
        }

        formStage.close();
    }

    @FXML
    private void handleCancel() {
        formStage.close();
    }

    private Person person;
    private Stage formStage;

    public void load(App app, Stage stage, List<Serializable> params) {
        formStage = stage;
        super.load(app, params);
    }

    @Override
    protected void load_fields() {
        if (params.isEmpty())
            person = new Person();
        else
            person = new Person((Person) getParameter(0));
    }

    @Override
    protected void load_bindings() {
        firstnameField.textProperty().bindBidirectional(person.firstnameProperty());
        firstnameError.visibleProperty().bind(Bindings.isEmpty(person.firstnameProperty()));

        lastnameField.textProperty().bindBidirectional(person.lastnameProperty());
        lastnameError.visibleProperty().bind(Bindings.isEmpty(person.lastnameProperty()));

        streetField.textProperty().bindBidirectional(person.streetProperty());
        streetError.visibleProperty().bind(Bindings.isEmpty(person.streetProperty()));

        cityField.textProperty().bindBidirectional(person.cityProperty());
        cityError.visibleProperty().bind(Bindings.isEmpty(person.cityProperty()));

        postalcodeField.textProperty().bindBidirectional(person.postalcodeProperty());
        postalcodeError.visibleProperty().bind(Bindings.isEmpty(person.postalcodeProperty()));

        birthdayField.valueProperty().bindBidirectional(person.birthdateProperty());
        BooleanBinding checkBirthdateBinding = Bindings.createBooleanBinding(() -> {
            LocalDate birthdate = person.getBirthdate();
            return birthdate.isAfter(LocalDate.now().minusYears(18))
                    || birthdate.isBefore(LocalDate.now().minusYears(65));
        }, person.birthdateProperty());
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
