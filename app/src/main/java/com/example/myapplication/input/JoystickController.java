package com.example.myapplication.input;

import android.view.MotionEvent;
import android.view.View;

import com.example.myapplication.R;

public class JoystickController extends InputController{
    private double startPosX;
    private double startPosY;

    private double distMax;

    public  JoystickController(View view){
        view.findViewById(R.id.joystick).setOnTouchListener(new JoystickTouchController());
        view.findViewById(R.id.pulsador).setOnTouchListener(new DispararTouchListener());
        double factorPixel = view.getHeight() / 400;
        distMax = 50 * factorPixel;
    }

    private class JoystickTouchController implements  View.OnTouchListener{

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int accion = motionEvent.getActionMasked();
            if(accion == MotionEvent.ACTION_DOWN){
                startPosX = motionEvent.getX(0);
                startPosY = motionEvent.getY(0);
            }
            else if(accion == MotionEvent.ACTION_UP){
                horizontal = 0;
                vertical = 0;
            }
            else if(accion == MotionEvent.ACTION_MOVE){
                horizontal = (motionEvent.getX(0) - startPosX) / distMax;
                if(horizontal > 1){
                    horizontal = 1;
                }
                else if(horizontal < -1){
                    horizontal = -1;
                }

                vertical = (motionEvent.getY(0) - startPosY) / distMax;
                if(vertical > 1){
                    vertical = 1;
                }
                else if(vertical < -1){
                    vertical = -1;
                }
            }

            return true;
        }
    }

    private class DispararTouchListener implements  View.OnTouchListener{

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int accion = motionEvent.getActionMasked();
            if(accion == MotionEvent.ACTION_DOWN){
                estaDisparando = true;
            }
            else if(accion == MotionEvent.ACTION_UP){
                estaDisparando = false;
            }

            return true;
        }
    }
}
