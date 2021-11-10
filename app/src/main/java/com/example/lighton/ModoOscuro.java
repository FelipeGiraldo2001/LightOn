package com.example.lighton;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class ModoOscuro extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setDayNight();
    }

    public void setDayNight(){
        SharedPreferences sp=getSharedPreferences("SP", MODE_PRIVATE);
        int theme = sp.getInt("Theme", 1);
        if (theme==0) {
            getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }
}
