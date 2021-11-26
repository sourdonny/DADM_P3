package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import com.example.myapplication.motor.GameEngine;
import com.example.myapplication.motor.GameObject;

public class PuntuacionGameObject extends GameObject {
    private final TextView textView;
    private long totalMillis;

    public PuntuacionGameObject(View view, int viewResId) {
        textView = (TextView) view.findViewById(viewResId);
    }

    @Override
    public void StartGame() {
        totalMillis = 0;
    }

    @Override
    public void OnUpdate(long elapsedMillis, GameEngine gameEngine) {
        totalMillis += elapsedMillis;
    }

    @Override
    public void OnDraw() {
        textView.setText(String.valueOf(totalMillis));
    }
}
