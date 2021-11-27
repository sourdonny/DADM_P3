package com.example.myapplication.movimiento;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.myapplication.R;

public class Nave extends Jugador{
    private ImageView naveImage;

    public Nave(View view) {
        super(view);

        naveImage = new ImageView(view.getContext());
        Drawable naveDrawable = view.getContext().getResources().getDrawable(R.drawable.ship);
        naveImage.setLayoutParams(new ViewGroup.LayoutParams(
                (int) (naveDrawable.getIntrinsicWidth() * factorPixel),
                (int)(naveDrawable.getIntrinsicHeight() * factorPixel)
        ));

        naveImage.setImageDrawable(naveDrawable);
        ((FrameLayout) view).addView(naveImage);

        maxX -= (naveDrawable.getIntrinsicWidth() * factorPixel);
        maxY -= (naveDrawable.getIntrinsicHeight() * factorPixel);
    }

    @Override
    public void OnDraw() {
        textView.setText("POSICION X: "+(int) (posicionX)+"\nPOSICION Y: "+(int) (posicionY));
        naveImage.setTranslationX((int)posicionX);
        naveImage.setTranslationY((int)posicionY);
    }
}
