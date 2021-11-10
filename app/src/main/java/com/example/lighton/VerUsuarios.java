package com.example.lighton;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerUsuarios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerUsuarios extends Fragment {
    private RecyclerView rvUsuario;
    private FirebaseRecyclerAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public VerUsuarios() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VerUsuarios.
     */
    // TODO: Rename and change types and number of parameters
    public static VerUsuarios newInstance(String param1, String param2) {
        VerUsuarios fragment = new VerUsuarios();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ver_usuarios, container, false);
         rvUsuario=(RecyclerView) root.findViewById(R.id.rvUsuarios);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        rvUsuario.setLayoutManager(linearLayoutManager);

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child(Constantes.NODO_USUARIOS);

        FirebaseRecyclerOptions<Chat> options =
                new FirebaseRecyclerOptions.Builder<Chat>()
                        .setQuery(query, Chat.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Chat, UsuarioViewHolder>(options) {
            @Override
            public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_card_view_usuario, parent, false);

                return new UsuarioViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(UsuarioViewHolder holder, int position, Chat model) {
                Glide.with(VerUsuarios.this).load(model.getFotoPerfilURL()).into(holder.getCivFotoPerfil());
                holder.getTxtNombreDeusuario().setText(model.getName());

                Chat chat=new Chat(getSnapshots().getSnapshot(position).getKey(), model);
                holder.getLayoutPrincipal().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        };
        rvUsuario.setAdapter(adapter);
        return root;
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