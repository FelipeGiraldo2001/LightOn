package com.example.lighton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EsperaRecuContra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera_recu_contra);
    }

    public void SiguienteRecup(View view){
        Intent siguientel = new Intent(this, RecuperarContra.class);
        startActivity(siguientel);
    }
}