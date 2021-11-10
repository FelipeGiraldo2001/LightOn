package com.example.lighton;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Ver_Perfiles_Activity extends AppCompatActivity {

    private RecyclerView rvBandas;
    private FirebaseRecyclerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);

        rvBandas=findViewById(R.id.rvUsuarios);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvBandas.setLayoutManager(linearLayoutManager);

        Query query = FirebaseDatabase.getInstance()
                .getReference();
                //.child(Constantes.NODO_USUARIOS);

        FirebaseRecyclerOptions<PerfilesDeBanda> options =
                new FirebaseRecyclerOptions.Builder<PerfilesDeBanda>()
                        .setQuery(query, PerfilesDeBanda.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<PerfilesDeBanda, PerfilesViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PerfilesViewHolder holder, int position, @NonNull PerfilesDeBanda model) {

            }


            @Override
            public PerfilesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_card_perfil_banda, parent, false);

                return new PerfilesViewHolder(view);
            }

        };
        rvBandas.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
