package com.example.myapplication.input;

public class InputController {
    private double horizontal;
    private double vertical;

    private boolean estaDisparando;

    public void OnStart() {

    }

    public void OnStop() {

    }

    public void OnPause() {

    }

    public void OnResume() {

    }

    public void OnPreUpdate() {

    }

    public double getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(double horizontal) {
        this.horizontal = horizontal;
    }

    public double getVertical() {
        return vertical;
    }

    public void setVertical(double vertical) {
        this.vertical = vertical;
    }

    public boolean isEstaDisparando() {
        return estaDisparando;
    }

    public void setEstaDisparando(boolean estaDisparando) {
        this.estaDisparando = estaDisparando;
    }
}
