package com.example.lighton;

public class Banda {
    private  String genero;
    private String urlFoto;
    private String nombreBanda;
    private String fotoPerfilbanda;

    public Banda() {
    }

    public Banda(String genero, String nombreBanda, String fotoPerfilbanda) {
        this.genero = genero;
        this.nombreBanda = nombreBanda;
        this.fotoPerfilbanda = fotoPerfilbanda;
    }

    public Banda(String genero, String urlFoto, String nombreBanda, String fotoPerfilbanda) {
        this.genero = genero;
        this.urlFoto = urlFoto;
        this.nombreBanda = nombreBanda;
        this.fotoPerfilbanda = fotoPerfilbanda;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getNombreBanda() {
        return nombreBanda;
    }

    public void setNombreBanda(String nombreBanda) {
        this.nombreBanda = nombreBanda;
    }

    public String getFotoPerfilbanda() {
        return fotoPerfilbanda;
    }

    public void setFotoPerfilbanda(String fotoPerfilbanda) {
        this.fotoPerfilbanda = fotoPerfilbanda;
    }
}
