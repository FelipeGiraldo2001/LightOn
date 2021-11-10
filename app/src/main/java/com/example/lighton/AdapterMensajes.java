package com.example.lighton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdapterMensajes extends RecyclerView.Adapter<HolderMensaje>{
    private List<MensajeRecibir> listMensaje = new ArrayList<>();
    private Context c;

    public AdapterMensajes(Context c) {
        this.c = c;
    }

    public AdapterMensajes(ThirdFragment thirdFragment) {
    }

    public void addMensaje(MensajeRecibir m){
        listMensaje.add(m);
        notifyItemInserted(listMensaje.size());
    }

    @NonNull
    @Override
    public HolderMensaje onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        /*if(viewType==1){
            view=LayoutInflater.from(c).inflate(R.layout.fragment_card_view_emisor,parent,false);
        } else{
            view=LayoutInflater.from(c).inflate(R.layout.fragment_card_view_receptor,parent,false);
        }*/
        view=LayoutInflater.from(c).inflate(R.layout.fragment_card_view_emisor,parent,false);
        return new HolderMensaje(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMensaje holder, int position) {
        holder.getNombre().setText(listMensaje.get(position).getNombre());
        holder.getMensaje().setText(listMensaje.get(position).getMensaje());


        if (listMensaje.get(position).getType_mensaje().equals("2")){
            holder.getFotoMensaje().setVisibility(View.VISIBLE);
            holder.getMensaje().setVisibility(View.VISIBLE);
            Glide.with(c).load(listMensaje.get(position).getUrlFoto()).into(holder.getFotoMensaje());
        } else if(listMensaje.get(position).getType_mensaje().equals("1")){
            holder.getFotoMensaje().setVisibility(View.GONE);
            holder.getMensaje().setVisibility(View.VISIBLE);
        }
        if(listMensaje.get(position).getFotoPerfil().isEmpty()){
            holder.getFotomensajePerfil().setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(c).load(listMensaje.get(position).getFotoPerfil()).into(holder.getFotomensajePerfil());
        }
        Long codigoHora=listMensaje.get(position).getHora();
        Date d=new Date(codigoHora);
        //PrettyTime prettyTime=new PrettyTime(new Date(), Locale.getDefault());
        //prettyTime.format(d);
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss a" , Locale.getDefault());
        holder.getHora().setText(sdf.format(d));
    }

    @Override
    public int getItemCount() {
        return listMensaje.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);

    }
}

