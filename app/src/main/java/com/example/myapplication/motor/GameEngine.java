package com.example.myapplication.motor;

import android.app.Activity;

import com.example.myapplication.input.InputController;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private List<GameObject> gameObjects = new ArrayList<>();
    private List<GameObject> objectsToAdd = new ArrayList<>();
    private List<GameObject> objectsToRemove = new ArrayList<>();

    private UpdateThread updateThread;
    private DrawThread drawThread;
    public InputController inputController;

    private Runnable drawRunnable = new Runnable() {
        @Override
        public void run() {
            int numGameObjects = gameObjects.size();
            for(int i = 0; i < numGameObjects; i++){
                gameObjects.get(i).OnDraw();
            }
        }
    };

    private Activity mainActivity;

    public GameEngine(Activity activity){
        mainActivity = activity;
    }

    public void StartGame(){
        StopGame();

        int numGameObjects = gameObjects.size();
        for(int i = 0; i < numGameObjects; i++){
            gameObjects.get(i).StartGame();
        }

        updateThread = new UpdateThread(this);
        updateThread.start();

        drawThread = new DrawThread(this);
        drawThread.StartGame();
    }

    public void StopGame(){
        if(updateThread != null){
            updateThread.stopGame();
        }

        if(drawThread != null){
            drawThread.StopGame();
        }
    }

    public void PauseGame(){
        if(updateThread != null){
            updateThread.ResumeGame();
        }

        if(drawThread != null){
            drawThread.ResumeGame();
        }
    }

    public void ResumeGame() {
        if (updateThread != null) {
            updateThread.ResumeGame();
        }
        if (drawThread != null) {
            drawThread.ResumeGame();
        }
    }

    public void AddGameObject(GameObject gameObject){
        if(IsRunning()){
            objectsToAdd.add(gameObject);
        } else {
            gameObjects.add(gameObject);
        }

        mainActivity.runOnUiThread(gameObject.onAddedRunnable);
    }
    public void RemoveGameObject(GameObject gameObject){
        gameObjects.remove(gameObject);
        mainActivity.runOnUiThread(gameObject.onRemovedRunnable);
    }

    public void OnUpdate(long elapsedMillis){
        int numGameObjects = gameObjects.size();
        for(int i = 0; i < numGameObjects; i++){
            gameObjects.get(i).OnUpdate(elapsedMillis, this);
        }

        synchronized (gameObjects){
            while(!objectsToRemove.isEmpty()){
                gameObjects.remove(objectsToRemove.remove(0));
            }

            while(!objectsToAdd.isEmpty()){
                gameObjects.add(objectsToAdd.remove(0));
            }
        }
    }

    public void setTheInputcontroller(InputController inputController) {
        this.inputController = inputController;
    }

    public void OnDraw(){
        mainActivity.runOnUiThread(drawRunnable);
    }
    public boolean IsRunning(){
        return updateThread != null && updateThread.isGameRunning();
    }
    public boolean IsPaused(){
        return updateThread != null && updateThread.isGamePaused();
    }
}
