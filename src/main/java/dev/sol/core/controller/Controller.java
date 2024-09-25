package dev.sol.core.controller;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;

public abstract class Controller {
    protected Application application;
    protected List<Serializable> params;

    public void load(Application application) {
        load(application, new LinkedList<>());
    }

    public void load(Application application, List<Serializable> params) {
        this.application = application;
        this.params = params;

        load_fields();
        load_bindings();
        load_listeners();
    }

    protected Serializable getParameter(int index) {
        return params.get(index);
    }

    protected abstract void load_fields();

    protected abstract void load_bindings();

    protected abstract void load_listeners();
}