package com.example.lighton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Principal extends AppCompatActivity {

    FirstFragment firstFragment=new FirstFragment();
    SecondFragment secondFragment=new SecondFragment();
    ThirdFragment thirdFragment=new ThirdFragment();
    FourFragment fourFragment = new FourFragment();
    Configuracion configfragment=new Configuracion();
    Menu menuFragment=new Menu();
    VerUsuarios verUser=new VerUsuarios();
    DonarFragment donar=new DonarFragment();
    Ver_Usuarios_Activity verusers= new Ver_Usuarios_Activity();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        BottomNavigationView navigation= findViewById(R.id.bottom_navigation);
        BottomNavigationView navigation2= findViewById(R.id.bottom_perfil);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation2.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(firstFragment);
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    loadFragment(firstFragment);
                    return true;
                case R.id.secondFragment:
                    loadFragment(secondFragment);
                    return true;
                case R.id.verUsers:
                    loadFragment(thirdFragment);
                    return true;
                case R.id.fourFragment:
                    loadFragment(fourFragment);
                    return true;
                case R.id.seeting:
                    loadFragment(configfragment);
            }
            return false;
        }
    };


    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();

    }



}