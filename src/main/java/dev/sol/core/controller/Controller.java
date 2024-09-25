package dev.sol.core.controller;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import dev.sol.App;

public abstract class Controller {
    protected App app;
    protected List<Serializable> params;

    public void load(App application) {
        load(application, new LinkedList<>());
    }

    public void load(App application, List<Serializable> params) {
        this.app = application;
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