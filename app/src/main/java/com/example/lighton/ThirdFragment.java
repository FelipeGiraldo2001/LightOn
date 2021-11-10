package com.example.lighton;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import javax.xml.transform.Result;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    private CircleImageView fotoPerfil;
    private TextView nombre;
    private RecyclerView rvmensajes;
    private EditText txtmensaje;
    private Button btnEnviar;
    private AdapterMensajes adapter;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ImageButton btnEnviarFoto;
    private static final int PHOTO_SEND = 1;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private static final int PHOTO_PERFIL = 2;
    private String fotoPerfilCadena;
    private FirebaseAuth mAuth;
    private String NOMBRE_USUARIO;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_third, container, false);
        fotoPerfil = (CircleImageView) root.findViewById(R.id.fotoPerfil);
        nombre = (TextView) root.findViewById(R.id.nombre);
        rvmensajes = (RecyclerView) root.findViewById(R.id.rvmensajes);
        txtmensaje = (EditText) root.findViewById(R.id.txtmensajes);
        btnEnviar = (Button) root.findViewById(R.id.btnenviar);
        adapter = new AdapterMensajes(getActivity());
        LinearLayoutManager l = new LinearLayoutManager(getActivity());
        rvmensajes.setLayoutManager(l);
        rvmensajes.setAdapter(adapter);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chatV2"); //sala de chat version2
        btnEnviarFoto = (ImageButton) root.findViewById(R.id.btnEnviarFoto);
        storage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        fotoPerfilCadena = "";
        mAuth = FirebaseAuth.getInstance();





        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.push().setValue(new MensajeEnviar(txtmensaje.getText().toString()
                        , NOMBRE_USUARIO, fotoPerfilCadena, "1", ServerValue.TIMESTAMP));
                txtmensaje.setText("");
            }
        });

        btnEnviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Selecciona una imagen"), PHOTO_SEND);
            }
        });

        fotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Selecciona una imagen"), PHOTO_PERFIL);
            }
        });
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MensajeRecibir m = snapshot.getValue(MensajeRecibir.class);
                adapter.addMensaje(m);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

    private void setScrollbar() {
        rvmensajes.scrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SEND && resultCode == RESULT_OK) {
            Uri u = data.getData();
            storageReference = storage.getReference("Images_chat");//Imagenes_chat
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference.child(u.getLastPathSegment()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            MensajeEnviar m = new MensajeEnviar(NOMBRE_USUARIO + " te ha enviado una foto", task.getResult().toString(), NOMBRE_USUARIO, fotoPerfilCadena, "2", ServerValue.TIMESTAMP);
                            databaseReference.push().setValue(m);
                        }
                    });
                }
            });
        } else if (requestCode == PHOTO_PERFIL && resultCode == RESULT_OK) {
            Uri u = data.getData();
            storageReference = storage.getReference("foto_perfil");//Imagenes_chat
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference.child(u.getLastPathSegment()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            fotoPerfilCadena = u.toString();
                            MensajeEnviar m = new MensajeEnviar(NOMBRE_USUARIO + " ha actualizado su foto de perfil", task.getResult().toString(), NOMBRE_USUARIO, fotoPerfilCadena, "2", ServerValue.TIMESTAMP);
                            databaseReference.push().setValue(m);
                            Glide.with(getActivity()).load(u.toString()).into(fotoPerfil);
                        }
                    });
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            btnEnviar.setEnabled(false);
            DatabaseReference reference=database.getReference("Users/"+ currentUser.getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = snapshot.child("Name").getValue().toString();
                    NOMBRE_USUARIO=name;
                    nombre.setText(NOMBRE_USUARIO);
                    btnEnviar.setEnabled(true);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}

