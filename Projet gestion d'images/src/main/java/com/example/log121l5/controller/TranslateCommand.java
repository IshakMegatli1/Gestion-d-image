package com.example.log121l5.controller;

import com.example.log121l5.model.PerspectiveModel;

public class TranslateCommand implements Command {
    private PerspectiveModel model;
    private double prevTranslationX;
    private double prevTranslationY;
    private double newTranslationX;
    private double newTranslationY;

    public TranslateCommand(PerspectiveModel model, double newTranslationX, double newTranslationY) {
        this.model = model;
        this.prevTranslationX = model.getTranslationX();
        this.prevTranslationY = model.getTranslationY();
        this.newTranslationX = newTranslationX;
        this.newTranslationY = newTranslationY;
    }

    @Override
    public void execute() {
        model.setTranslationX(newTranslationX);
        model.setTranslationY(newTranslationY);
    }

    @Override
    public void undo() {
        model.setTranslationX(prevTranslationX);
        model.setTranslationY(prevTranslationY);
    }

    @Override
    public void redo() {

    }
}

