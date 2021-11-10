package com.example.lighton;

public class PerfilesDeBanda {
    private String mNameBanda;
    private String mGenero;
    private String mUid;
    private String fotoPerfilURL;

    public PerfilesDeBanda(String key, Chat model) {}  // Needed for Firebase

    public PerfilesDeBanda(String mName, String mMessage, String mUid, String fotoPerfilURL) {
        this.mNameBanda = mName;
        this.mGenero = mMessage;
        this.mUid = mUid;
        this.fotoPerfilURL = fotoPerfilURL;
    }

    public String getName() { return mNameBanda; }

    public void setName(String name) { mNameBanda = name; }

    public String getMessage() { return mGenero; }

    public void setMessage(String message) { mGenero = message; }

    public String getUid() { return mUid; }

    public void setUid(String uid) { mUid = uid; }

    public String getFotoPerfilURL() {
        return fotoPerfilURL;
    }

    public void setFotoPerfilURL(String fotoPerfilURL) {
        this.fotoPerfilURL = fotoPerfilURL;
    }
}
