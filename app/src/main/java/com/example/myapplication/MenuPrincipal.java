package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuPrincipal extends Fragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu_principal, container, false);
        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.jugarButton).setOnClickListener((View.OnClickListener) this);
        System.out.println("MENU PRINCIPAL");

    }

    @Override
    public void onClick(View v) {
        System.out.println("JUGANDO");
        ((MainActivity)getActivity()).StartGame();
    }
}