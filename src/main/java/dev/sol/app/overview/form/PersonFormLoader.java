package dev.sol.app.overview.form;

import java.io.IOException;

import dev.sol.App;
import dev.sol.core.loader.Loader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PersonFormLoader extends Loader {
    public PersonFormLoader(App app) {
        super("overview/form/PERSON_FORM", app);
    }

    @Override
    public void load() {
        try {
            Parent root = loader.load();
            root.setOnMousePressed(e -> root.requestFocus());

            Scene scene = new Scene(root);
            Stage formStage = new Stage();

            PersonFormController controller = loader.getController();
            controller.load(app, formStage, params);

            formStage.setTitle("Address App - Information Form");
            formStage.initOwner(app.getApplicationStage());
            formStage.initModality(Modality.APPLICATION_MODAL);
            formStage.setResizable(false);
            formStage.setScene(scene);
            formStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
