package com.example.lighton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarContra extends AppCompatActivity {

        private EditText mEditTextEmail;
    private String email="";

        private FirebaseAuth mAuth;
        private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contra);


        mAuth=FirebaseAuth.getInstance();
        mDialog= new ProgressDialog(this);
        mEditTextEmail= (EditText) findViewById(R.id.txt_recupe);
        Button mButtonResetPass = (Button) findViewById(R.id.btn_Actualizar);

        mButtonResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=mEditTextEmail.getText().toString();
                if(!email.equals("")){
                    mDialog.setMessage("Espere un momento...");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    resetPassword();
                }else{
                    Toast.makeText(RecuperarContra.this, "Debe ingresar el email", Toast.LENGTH_SHORT).show();
                }
                mDialog.dismiss();
            }
        });
    }

    private void resetPassword(){
       mAuth.setLanguageCode("es");
       mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
              if(task.isSuccessful()){
                  Toast.makeText(RecuperarContra.this, "Se ha enviado un correo para reestablecer tu contraseña", Toast.LENGTH_SHORT).show();
              }  else{
                  Toast.makeText(RecuperarContra.this, "No se pudo reestablecer contraseña", Toast.LENGTH_SHORT).show();
              }
           }
       });
    }
}