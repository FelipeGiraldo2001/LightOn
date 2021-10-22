package com.example.lighton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

public class Login extends AppCompatActivity {

    private FloatingActionButton mButtomGoogle;
    private FloatingActionButton mButtomFacebook;
    Registrar r=new Registrar();
    FirebaseAuth at= r.mAuth;
    GoogleSignInClient mGoogleSingInClient;
    public static final int RC_SIGN_IN=0;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        at=FirebaseAuth.getInstance();
        callbackManager=CallbackManager.Factory.create();
        mButtomGoogle=(FloatingActionButton)findViewById(R.id.btn_InicioGoogle);
        mButtomFacebook=(FloatingActionButton)findViewById(R.id.btn_InicioFacebook);

        mButtomGoogle.setOnClickListener(new View.OnClickListener() {
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

        mButtomFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithPublishPermissions(Login.this, Arrays.asList("email","public_profile"));
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
    private void signInWithGoogle(){
        Intent signInIntent=mGoogleSingInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }
    public void onActivityResult1(int  requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account= task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            }catch (ApiException e){
                Toast.makeText(Login.this, "Falló Google", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken){
        AuthCredential credential= GoogleAuthProvider.getCredential(idToken,null);
        at.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(Login.this,Principal.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Login.this,"Fallo en iniciar sesión", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode,data);
        callbackManager.onActivityResult(requestCode, resultCode,data);
    }

    private void handleFacebookAccessToken(AccessToken token){
        AuthCredential credential= FacebookAuthProvider.getCredential(token.getToken());
        at.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(Login.this,Principal.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Login.this,"Fallo en iniciar sesión", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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