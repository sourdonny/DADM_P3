package com.example.myapplication.movimiento;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.motor.GameEngine;

public class Disparo extends Jugador{
    private ImageView disparoImagen;
    private double alturaImagen;
    private double anchoImagen;

    private Nave nave;

    public Disparo(View view) {
        super(view);

        velocidad = factorPixel * -300 / 1000;

        disparoImagen = new ImageView(view.getContext());
        Drawable disparoDrawable = view.getContext().getResources().getDrawable(R.drawable.bullet);
        anchoImagen = disparoDrawable.getIntrinsicWidth() * factorPixel;
        alturaImagen = disparoDrawable.getIntrinsicHeight() * factorPixel;
        disparoImagen.setLayoutParams(new ViewGroup.LayoutParams((int) (anchoImagen),(int) (alturaImagen)));
        disparoImagen.setImageDrawable(disparoDrawable);
        disparoImagen.setVisibility(View.GONE);
        ((FrameLayout) view).addView(disparoImagen);
    }

    @Override
    public void OnUpdate(long milis, GameEngine gameEngine){
        posicionY += velocidad * milis;
        if(posicionY < alturaImagen){
            gameEngine.RemoveGameObject(this);
            //nave.re
        }
    }
}
