package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String TAG_FRAGMENT = "content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mainContainer, new MenuPrincipal(), TAG_FRAGMENT)
                    .commit();
        }
    }

    public void StartGame(){
        CambiarFragment(new Gameplay());
    }

    private void CambiarFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, fragment, TAG_FRAGMENT).addToBackStack(null).commit();
    }
}