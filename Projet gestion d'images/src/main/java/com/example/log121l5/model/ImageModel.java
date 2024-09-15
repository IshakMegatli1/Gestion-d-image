package com.example.log121l5.model;

import java.util.ArrayList;
import java.util.List;

public class ImageModel extends Subject {
    private List<Observer> observers = new ArrayList<>();
    private String filePath;
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        notifyObservers();
    }
}
