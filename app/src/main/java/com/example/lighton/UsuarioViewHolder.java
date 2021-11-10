package com.example.lighton;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsuarioViewHolder extends RecyclerView.ViewHolder {

    private CircleImageView civFotoPerfil;
    private TextView txtNombreDeusuario;
    private LinearLayout layoutPrincipal;

    public UsuarioViewHolder(@NonNull View itemView) {
        super(itemView);
        civFotoPerfil=itemView.findViewById(R.id.civFotoPerfil);
        txtNombreDeusuario=itemView.findViewById(R.id.txtNombreUsuario);
        layoutPrincipal=itemView.findViewById(R.id.layoutPrincipal);
    }

    public CircleImageView getCivFotoPerfil() {
        return civFotoPerfil;
    }

    public void setCivFotoPerfil(CircleImageView civFotoPerfil) {
        this.civFotoPerfil = civFotoPerfil;
    }

    public TextView getTxtNombreDeusuario() {
        return txtNombreDeusuario;
    }

    public void setTxtNombreDeusuario(TextView txtNombreDeusuario) {
        this.txtNombreDeusuario = txtNombreDeusuario;
    }

    public LinearLayout getLayoutPrincipal() {
        return layoutPrincipal;
    }

    public void setLayoutPrincipal(LinearLayout layoutPrincipal) {
        this.layoutPrincipal = layoutPrincipal;
    }
}
