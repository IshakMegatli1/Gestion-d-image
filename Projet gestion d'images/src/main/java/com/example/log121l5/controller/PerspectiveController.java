package com.example.log121l5.controller;


import com.example.log121l5.model.PerspectiveModel;

public class PerspectiveController {
    private PerspectiveModel model;
    private CommandManager commandManager;

    public PerspectiveController(PerspectiveModel model) {
        this.model = model;
        this.commandManager = CommandManager.getInstance();
    }

    public void zoom(double factor) {
        Command zoomCommand = new ZoomCommand(model, factor);
        commandManager.executeCommand(zoomCommand);
    }

    public void translate(double deltaX, double deltaY) {
        Command translateCommand = new TranslateCommand(model,
                model.getTranslationX() + deltaX,
                model.getTranslationY() + deltaY
        );
        commandManager.executeCommand(translateCommand);
    }

    public void undo() {
        commandManager.undo();
    }

    public void redo() {
        commandManager.redo();
    }
}
