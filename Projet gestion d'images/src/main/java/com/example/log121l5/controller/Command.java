package com.example.log121l5.controller;

public interface Command {
    void execute();
    void undo();

    void redo();
}
