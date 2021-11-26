package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.example.myapplication.input.InputController;
import com.example.myapplication.motor.GameEngine;
import com.example.myapplication.movimiento.Jugador;

public class Gameplay extends Fragment implements View.OnClickListener{
    private GameEngine gameEngine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gameplay, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_play_pause).setOnClickListener(this);

        final ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                observer.removeOnGlobalLayoutListener(this);
                gameEngine = new GameEngine(getActivity());
                gameEngine.setTheInputcontroller(new InputController());
                //gameEngine.AddGameObject(new PuntuacionGameObject(view, R.id.txt_score));
                gameEngine.AddGameObject(new Jugador(getView()));
                gameEngine.StartGame();
            }
        });
    }

    @Override
    public void onClick(View view) {
        playOrPause();
    }

    public void playOrPause() {
        Button button = (Button) getView().findViewById(R.id.btn_play_pause);
        if (gameEngine.IsPaused()) {
            gameEngine.ResumeGame();
            button.setText("PAUSA");
        }
        else {
            gameEngine.PauseGame();
            button.setText("PLAY");
        }
    }
}