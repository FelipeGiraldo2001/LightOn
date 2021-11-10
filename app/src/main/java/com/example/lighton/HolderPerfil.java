package com.example.lighton;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class HolderPerfil {
    private TextView nombreBanda;
    private TextView genero;
    private CircleImageView fotoPerfilBanda;
    private ImageView fotoBanda;

    public HolderPerfil(@NonNull View itemView){
        super();
        nombreBanda=(TextView) itemView.findViewById(R.id.nombreBanda);
        genero=(TextView) itemView.findViewById(R.id.mensajeGenero);
        fotoPerfilBanda =(CircleImageView) itemView.findViewById(R.id.fotoPerfilBanda);
        fotoBanda=(ImageView) itemView.findViewById(R.id.bandaFoto);
    }

    public TextView getNombreBanda() {
        return nombreBanda;
    }

    public void setNombreBanda(TextView nombreBanda) {
        this.nombreBanda = nombreBanda;
    }

    public TextView getGenero() {
        return genero;
    }

    public void setGenero(TextView genero) {
        this.genero = genero;
    }

    public CircleImageView getFotoPerfilBanda() {
        return fotoPerfilBanda;
    }

    public void setFotoPerfilBanda(CircleImageView fotoPerfilBanda) {
        this.fotoPerfilBanda = fotoPerfilBanda;
    }

    public ImageView getFotoBanda() {
        return fotoBanda;
    }

    public void setFotoBanda(ImageView fotoBanda) {
        this.fotoBanda = fotoBanda;
    }
}
