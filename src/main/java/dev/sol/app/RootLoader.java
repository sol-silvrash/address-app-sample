package dev.sol.app;

import java.io.IOException;

import dev.sol.App;
import dev.sol.core.loader.Loader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class RootLoader extends Loader {

    public RootLoader(App app) {
        super("ROOT", app);
    }

    @Override
    public void load() {
        try {
            BorderPane root = loader.load();
            Scene scene = new Scene(root);
            app.getApplicationStage().setTitle("Address App");
            app.getApplicationStage().setMinWidth(1024);
            app.getApplicationStage().setMinHeight(728);
            app.getApplicationStage().setScene(scene);
            app.getApplicationStage().show();

            app.setApplicationRoot(root);
            root.setOnMouseClicked(e -> app.getApplicationRoot().requestFocus());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
