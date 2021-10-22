package com.example.lighton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAssignedNumbers;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RecuperarContra extends AppCompatActivity {

    private EditText mEditCorreo;
    private Button mButtonResetPass;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contra);


        mAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        mEditCorreo= (EditText) findViewById(R.id.txt_recupe);
        mButtonResetPass = (Button) findViewById(R.id.btn_Actualizar);


        mButtonResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

    }

    public void validate(){
        String email=mEditCorreo.getText().toString().trim();
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEditCorreo.setError("Correo Invalido");
            return;
        }
            sendEmail(email);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(RecuperarContra.this, IniciarSesion.class);
        startActivity(intent);
        finish();
    }

    public void sendEmail(String email){
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String emailAdress= email;

        auth.sendPasswordResetEmail(emailAdress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                     if(task.isSuccessful()){
                         Toast.makeText(RecuperarContra.this,"Correo enviado", Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(RecuperarContra.this,EsperaRecuContra.class);
                         startActivity(intent);
                         finish();
                     } else {
                         Toast.makeText(RecuperarContra.this, "El correo fue invalido", Toast.LENGTH_SHORT).show();
                     }
                    }
                });
    }

}