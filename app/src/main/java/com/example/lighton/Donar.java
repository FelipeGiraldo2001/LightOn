package com.example.lighton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Donar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar);
    }

    public void EntrarCredito(View view){
        Intent entrar = new Intent(this, Credito.class);
        startActivity(entrar);
    }
}