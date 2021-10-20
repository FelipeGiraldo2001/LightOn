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
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registrar extends AppCompatActivity {

    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private EditText mEditTextRepeatPass;
    private Button mButtonRegister;

    //Variables de los datos a registrar
    private String name="";
    private String email="";
    private String password="";
    private String repeatpass="";

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        mAuth=FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mEditTextName=(EditText) findViewById(R.id.txt_Nombre);
        mEditTextEmail=(EditText) findViewById(R.id.txt_Correo);
        mEditTextPassword=(EditText) findViewById(R.id.txt_Contas);
        mEditTextRepeatPass=(EditText) findViewById(R.id.txt_RpetirContas);
        mButtonRegister=(Button) findViewById(R.id.btn_Crearcuenta);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=mEditTextName.getText().toString();
                email=mEditTextEmail.getText().toString();
                password=mEditTextPassword.getText().toString();
                repeatpass=mEditTextRepeatPass.getText().toString();

                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !repeatpass.isEmpty() ){

                    if(password.length()>=6 && password==repeatpass){
                        registerUser();
                    }else {
                        Toast.makeText(Registrar.this, "La contraseña debe tener al menos 6 caracteres y coincidir", Toast.LENGTH_SHORT).show();
                    }
                    registerUser();
                }else {
                    Toast.makeText(Registrar.this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("email", email);
                    map.put("password", password);
                    map.put("confirm_password", repeatpass);
                    String id= mAuth.getCurrentUser().getUid();
                   mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task2) {
                           if(task2.isSuccessful()){
                                startActivity(new Intent(Registrar.this,IniciarSesion.class));
                                finish();
                           } else {
                               Toast.makeText(Registrar.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
                }else{
                    Toast.makeText(Registrar.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}