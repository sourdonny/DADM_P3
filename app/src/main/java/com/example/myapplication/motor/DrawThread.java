package com.example.myapplication.motor;

import java.util.Timer;
import java.util.TimerTask;

public class DrawThread {
    private static int FPS = 30;
    private static final long TIME_BETWEEN_DRAWS = 1000 / FPS;
    private final GameEngine theGameEngine;
    private Timer timer;

    public DrawThread(GameEngine gameEngine){
        theGameEngine = gameEngine;
    }

    public void StartGame(){
        StopGame();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                theGameEngine.OnDraw();
            }
        }, 0, TIME_BETWEEN_DRAWS);
    }

    public void StopGame(){
        if(timer != null){
            timer.cancel();
            timer.purge();
        }
    }

    public void PauseGame(){
        StopGame();
    }
    public void ResumeGame(){
        StartGame();
    }
}
