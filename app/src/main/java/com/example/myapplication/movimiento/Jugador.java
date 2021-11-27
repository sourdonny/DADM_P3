package com.example.myapplication.movimiento;

import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.input.InputController;
import com.example.myapplication.motor.GameEngine;
import com.example.myapplication.motor.GameObject;

public class Jugador extends GameObject {
    protected int maxX;
    protected int maxY;

    protected double posicionX;
    protected double posicionY;

    protected double velocidad;
    protected double factorPixel;

    protected TextView textView;

    public Jugador(View view){
        factorPixel = view.getHeight() / 400;
        maxX = view.getWidth() - view.getPaddingRight() - view.getPaddingRight();
        maxY = view.getHeight() - view.getPaddingTop() - view.getPaddingBottom();
        velocidad = factorPixel * 100 / 1000;

        textView = (TextView) view.findViewById(R.id.txt_score);
    }

    @Override
    public void StartGame() {
        posicionX = maxX / 2;
        posicionY = maxY / 2;
    }

    @Override
    public void OnUpdate(long milis, GameEngine gameEngine) {
        ActualizarPosicion(milis, gameEngine.inputController);
    }

    @Override
    public void OnDraw() {
        textView.setText("POSICION X: "+(int) (posicionX)+"\nPOSICION Y: "+(int) (posicionY));
    }

    public void ActualizarPosicion(long millis, InputController inputController){
        posicionX += velocidad * inputController.getHorizontal() * millis;
        if(posicionX < 0){
            posicionX = 0;
        }

        if(posicionX > maxX){
            posicionX = maxX;
        }

        posicionY += velocidad * inputController.getVertical() * millis;
        if(posicionY < 0){
            posicionY = 0;
        }

        if(posicionY > maxY){
            posicionY = maxY;
        }

    }
}
