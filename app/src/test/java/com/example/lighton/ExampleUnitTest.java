package com.example.lighton;

import org.junit.Test;

import static org.junit.Assert.*;

import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ThemedSpinnerAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ExampleUnitTest {


    @Test
    public void add_isCorrect() throws Exception  {
        String nom="Felipe";
        DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").addValueEventListener(new ValueEventListener() {
            int count;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String nombre= snapshot.child("Name ").getValue().toString();
                if (nom.equals(nombre)) {
                    assertTrue(true);
                }
                else{
                    assertFalse(false);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    }


