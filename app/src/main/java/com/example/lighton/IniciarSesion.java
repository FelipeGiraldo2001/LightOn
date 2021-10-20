package com.example.lighton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class IniciarSesion extends AppCompatActivity {


    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonIngresar;

    private String email ="";
    private String password="";

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        mAuth=FirebaseAuth.getInstance();
        mEditTextEmail=(EditText) findViewById(R.id.txt_email);
        mEditTextPassword=(EditText) findViewById(R.id.txt_contrase);
        mButtonIngresar=(Button) findViewById(R.id.btn_ingresar);

        mButtonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=mEditTextEmail.getText().toString();
                password=mEditTextPassword.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()){
                    loginUser();
                } else {
                    Toast.makeText(IniciarSesion.this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void SiguienteRecuperar(View view){
        Intent siguientel = new Intent(this, RecuperarContra.class);
        startActivity(siguientel);
    }

    private void loginUser(){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(IniciarSesion.this,Principal.class));
                    finish();
                } else {
                    Toast.makeText(IniciarSesion.this, "No se pudo iniciar sesión, compruebe los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void EntrarHome(View view){
        Intent entrar = new Intent(this, Principal.class);
        startActivity(entrar);
    }
}