package com.example.myapplication.motor;

public abstract class GameObject {
    public abstract void StartGame();
    public abstract void OnUpdate(long milis, GameEngine gameEngine);
    public abstract void OnDraw();

    public final Runnable onAddedRunnable = new Runnable() {
        @Override
        public void run() {
            onAddedToGameUiThread();
        }
    };

    public void onAddedToGameUiThread(){
    }

    public final Runnable onRemovedRunnable = new Runnable() {
        @Override
        public void run() {
            onRemovedFromGameUiThread();
        }
    };

    public void onRemovedFromGameUiThread(){
    }
}
