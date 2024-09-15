package com.example.log121l5.model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class PerspectiveModel extends Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    private double zoomFactor = 1.0;
    private double translationX = 0;
    private double translationY = 0;


    public double getZoomFactor() {
        return zoomFactor;
    }


    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
        notifyObservers();
    }





    public double getTranslationX() {
        return translationX;
    }


    public void setTranslationX(double translationX) {
        this.translationX = translationX;
        notifyObservers();
    }


    public double getTranslationY() {
        return translationY;
    }


    public void setTranslationY(double translationY) {
        this.translationY = translationY;
        notifyObservers();
    }



}
