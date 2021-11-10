package com.example.lighton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.StrictMode;
import android.se.omapi.Session;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.youtube.player.internal.m;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.Objects;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Configuracion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Configuracion extends Fragment {

    private TextView pasar, ayuda, acerca, notificacion, mButtomSingOut, buttomAddAccount;
    private ImageView t1,t2,t3,t4;
    private Switch st1;


    FirebaseAuth mAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Configuracion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Configuracion.
     */
    // TODO: Rename and change types and number of parameters
    public static Configuracion newInstance(String param1, String param2) {
        Configuracion fragment = new Configuracion();
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
        View root = inflater.inflate(R.layout.fragment_configuracion, container, false);
        pasar = (TextView) root.findViewById(R.id.textCuenta);
        ayuda = (TextView) root.findViewById(R.id.textAyuda);
        acerca = (TextView) root.findViewById(R.id.textAcerca);
        mButtomSingOut = (TextView) root.findViewById(R.id.btn_CerrarSesion);
        buttomAddAccount = (TextView) root.findViewById(R.id.btn_AgregarCuent);
        notificacion = (TextView) root.findViewById(R.id.textNotificacion);
        t1 = (ImageView) root.findViewById(R.id.Imagenocultar1);
        t2 = (ImageView) root.findViewById(R.id.Imagenocultar2);
        t3 = (ImageView) root.findViewById(R.id.Imagenocultar3);
        t4 = (ImageView) root.findViewById(R.id.Imagenocultar4);
        st1=(Switch)root.findViewById(R.id.ModoOscuro);
        mAuth = FirebaseAuth.getInstance();

        pasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.pasarConfig, Internacional.class, null);
                transaction.commit();
                pasar.setVisibility(View.GONE);
                ayuda.setVisibility(View.GONE);
                acerca.setVisibility(View.GONE);
                notificacion.setVisibility(View.GONE);
                buttomAddAccount.setVisibility(View.GONE);
                mButtomSingOut.setVisibility(View.GONE);
                t1.setVisibility(View.GONE);
                t2.setVisibility(View.GONE);
                t3.setVisibility(View.GONE);
                t4.setVisibility(View.GONE);
                st1.setVisibility(View.GONE);

            }
        });
        ayuda.setOnClickListener(new View.OnClickListener() {
            //inicio código correo
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.pasarConfig, Ayuda.class, null);
                transaction.commit();
                pasar.setVisibility(View.GONE);
                ayuda.setVisibility(View.GONE);
                acerca.setVisibility(View.GONE);
                notificacion.setVisibility(View.GONE);
                buttomAddAccount.setVisibility(View.GONE);
                mButtomSingOut.setVisibility(View.GONE);
                t1.setVisibility(View.GONE);
                t2.setVisibility(View.GONE);
                t3.setVisibility(View.GONE);
                t4.setVisibility(View.GONE);
                st1.setVisibility(View.GONE);
            }
        });
        acerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.pasarConfig, AcercaDe.class, null);
                transaction.commit();
                pasar.setVisibility(View.GONE);
                ayuda.setVisibility(View.GONE);
                acerca.setVisibility(View.GONE);
                notificacion.setVisibility(View.GONE);
                buttomAddAccount.setVisibility(View.GONE);
                mButtomSingOut.setVisibility(View.GONE);
                t1.setVisibility(View.GONE);
                t2.setVisibility(View.GONE);
                t3.setVisibility(View.GONE);
                t4.setVisibility(View.GONE);
                st1.setVisibility(View.GONE);

            }
        });
        notificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.pasarConfig, Notificaciones.class, null);
                transaction.commit();
                pasar.setVisibility(View.GONE);
                ayuda.setVisibility(View.GONE);
                acerca.setVisibility(View.GONE);
                notificacion.setVisibility(View.GONE);
                buttomAddAccount.setVisibility(View.GONE);
                mButtomSingOut.setVisibility(View.GONE);
                t1.setVisibility(View.GONE);
                t2.setVisibility(View.GONE);
                t3.setVisibility(View.GONE);
                t4.setVisibility(View.GONE);
                st1.setVisibility(View.GONE);

            }
        });
        buttomAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }

            private void alertDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getLayoutInflater();

                View view = inflater.inflate(R.layout.dialog_personalizado, null);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();
                TextView txt1 = view.findViewById(R.id.text_Dialog);
                TextView txt2 = view.findViewById(R.id.text_Dialog2);

                txt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), Registrar.class);
                        startActivity(intent);
                    }
                });
                txt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), IniciarSesion.class);
                        startActivity(intent);
                    }
                });

            }
        });
        mButtomSingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(getActivity(), Login.class));
                try {
                    finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });

        return root;
    }
}