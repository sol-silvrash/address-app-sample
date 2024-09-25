package dev.sol.core.loader;

import java.io.Serializable;

import dev.sol.App;

public class LoaderFactory {
    // overview/PersonOverview Root
    public static void load(String name, App app, Serializable... params) {
        name = name.replace("/", ".");
        try {
            Class<?> loaderClass = Class.forName(Loader.PACKAGE_NAME + "." + name + "Loader");
            Loader loader = (Loader) loaderClass
                    .getConstructor(App.class)
                    .newInstance(app);

            loader.setParameters(params);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
