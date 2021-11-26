package com.example.myapplication.motor;

public class UpdateThread extends Thread{
    private final GameEngine THE_GAME_ENGINE;
    private boolean gameRunning = true;
    private boolean gamePaused = false;

    private Object synchroLock = new Object();

    public UpdateThread(GameEngine gameEngine){
        THE_GAME_ENGINE = gameEngine;
    }

    @Override
    public void start() {
        gameRunning = true;
        gamePaused = false;
        super.start();
    }

    public void stopGame(){
        gameRunning = false;
        ResumeGame();
    }

    @Override
    public void run(){
        long previousTimeMillis;
        long currentTimeMillis;
        long elapsedMillis;
        previousTimeMillis = System.currentTimeMillis();

        while(gameRunning){
            currentTimeMillis = System.currentTimeMillis();
            elapsedMillis = currentTimeMillis - previousTimeMillis;

            if(gamePaused){
                while (gamePaused){
                    try{
                        synchronized (synchroLock){
                            synchroLock.wait();
                        }
                    } catch (InterruptedException e) {

                    }
                }

                currentTimeMillis = System.currentTimeMillis();
            }

            THE_GAME_ENGINE.OnUpdate(elapsedMillis);
            previousTimeMillis = currentTimeMillis;
        }
    }

    public void PauseGame(){
        gamePaused = true;
    }

    public void ResumeGame(){
        if(gamePaused){
            gamePaused = false;
            synchronized (synchroLock){
                synchroLock.notify();
            }
        }
    }

    public boolean isGameRunning(){
        return  gameRunning;
    }

    public boolean isGamePaused(){
        return  gamePaused;
    }
}
