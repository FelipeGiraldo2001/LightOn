package com.example.lighton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

public class IniciarSesion extends AppCompatActivity {


    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonIngresar;
    private FloatingActionButton mButtonInicioGoogle;
    private FloatingActionButton mButtonInicioFacebook;

    private String email ="";
    private String password="";

    private FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSingInClient;
    public static final int RC_SIGN_IN=0;
    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        mAuth=FirebaseAuth.getInstance();
        callbackManager=CallbackManager.Factory.create();
        mEditTextEmail=(EditText) findViewById(R.id.txt_email);
        mEditTextPassword=(EditText) findViewById(R.id.txt_contrase);
        mButtonIngresar=(Button) findViewById(R.id.btn_ingresar);
        mButtonInicioGoogle=(FloatingActionButton) findViewById(R.id.btn_InicioConGoogle);
        mButtonInicioFacebook=(FloatingActionButton)findViewById(R.id.btn_InicioConFacebook);

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

        //Google
        mButtonInicioGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithGoogle();
            }
        });

        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();

        mGoogleSingInClient= GoogleSignIn.getClient(this,gso);

        mButtonInicioFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithPublishPermissions(IniciarSesion.this, Arrays.asList("email","public_profile"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
            }
        });
    }

    protected void onActivityResult1(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode,data);
        callbackManager.onActivityResult(requestCode, resultCode,data);
    }

    private void handleFacebookAccessToken(AccessToken token){
        AuthCredential credential= FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(IniciarSesion.this,Principal.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(IniciarSesion.this,"Fallo en iniciar sesión", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void signInWithGoogle(){
        Intent signInIntent=mGoogleSingInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account= task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            }catch (ApiException e){
                Toast.makeText(IniciarSesion.this, "Falló Google", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken){
        AuthCredential credential= GoogleAuthProvider.getCredential(idToken,null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(IniciarSesion.this,Principal.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(IniciarSesion.this,"Fallo en iniciar sesión", Toast.LENGTH_SHORT).show();
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