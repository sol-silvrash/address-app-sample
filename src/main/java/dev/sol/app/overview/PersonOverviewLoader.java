package dev.sol.app.overview;

import java.io.IOException;

import dev.sol.App;
import dev.sol.core.loader.Loader;
import javafx.scene.Parent;

public class PersonOverviewLoader extends Loader {

    public PersonOverviewLoader(App app) {
        super("overview/PERSON_OVERVIEW", app);
    }

    @Override
    public void load() {
        try {
            Parent root = loader.load();
            app.getApplicationRoot().setCenter(root);
            root.setOnMousePressed(e -> app.getApplicationRoot().requestFocus());

            PersonOverviewController controller = loader.getController();
            controller.load(app);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
