package com.example.lighton;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class Ver_Usuarios_Activity extends AppCompatActivity {

    private RecyclerView rvUsuario;
    private FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ver_usuarios);

       rvUsuario=(RecyclerView) findViewById(R.id.rvUsuarios);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvUsuario.setLayoutManager(linearLayoutManager);

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child(Constantes.NODO_USUARIOS);

        FirebaseRecyclerOptions<Chat> options =
                new FirebaseRecyclerOptions.Builder<Chat>()
                        .setQuery(query, Chat.class)
                        .build();

         adapter = new FirebaseRecyclerAdapter<Chat,UsuarioViewHolder>(options) {
            @Override
            public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_card_view_usuario, parent, false);

                return new UsuarioViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(UsuarioViewHolder holder, int position, Chat model) {
                Glide.with(Ver_Usuarios_Activity.this).load(model.getFotoPerfilURL()).into(holder.getCivFotoPerfil());
                holder.getTxtNombreDeusuario().setText(model.getName());

                Chat chat=new Chat(getSnapshots().getSnapshot(position).getKey(), model);
                holder.getLayoutPrincipal().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(Ver_Usuarios_Activity.this, "Key:"+ chat.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        rvUsuario.setAdapter(adapter);
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
