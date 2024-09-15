package com.example.log121l5.controller;

import com.example.log121l5.model.PerspectiveModel;

public class ZoomCommand implements Command {
    private PerspectiveModel model;
    private double previousZoom;
    private double newZoom;

    public ZoomCommand(PerspectiveModel model, double newZoom) {
        this.model = model;
        this.previousZoom = model.getZoomFactor();
        this.newZoom = newZoom;
    }

    @Override
    public void execute() {
        model.setZoomFactor(newZoom);
    }

    @Override
    public void undo() {
        model.setZoomFactor(previousZoom);
    }

    @Override
    public void redo() {

    }
}
