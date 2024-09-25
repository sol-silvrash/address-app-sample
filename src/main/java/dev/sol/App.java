package dev.sol;

import dev.sol.core.loader.LoaderFactory;
import dev.sol.models.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    private Stage applicationStage;
    private BorderPane applicationRoot;

    private ObservableList<Person> personlist;

    @Override
    public void start(Stage stage) throws Exception {
        applicationStage = stage;
        personlist = FXCollections.observableArrayList(Person.GENERATE_LIST());
        _init();
    }

    private void _init() {
        LoaderFactory.load("Root", this);
        LoaderFactory.load("overview/PersonOverview", this);
    }

    public Stage getApplicationStage() {
        return applicationStage;
    }

    public void setApplicationRoot(Parent root) {
        applicationRoot = (BorderPane) root;
    }

    public BorderPane getApplicationRoot() {
        return applicationRoot;
    }

    public ObservableList<Person> getPersonlist() {
        return personlist;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
