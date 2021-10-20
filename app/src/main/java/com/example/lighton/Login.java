package com.example.lighton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Registrar r=new Registrar();
    FirebaseAuth at= r.mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        at=FirebaseAuth.getInstance();
    }

    public void Siguiente(View view){
        Intent siguiente = new Intent(this, Registrar.class);
        startActivity(siguiente);
    }
    public void SiguienteLogin(View view){
        Intent siguientel = new Intent(this, IniciarSesion.class);
        startActivity(siguientel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (at.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, PerfilesBanda.class));
            finish();
        }
    }

    public void EntrarHome(View view){
        Intent entrar = new Intent(this, Home.class);
        startActivity(entrar);
    }

}