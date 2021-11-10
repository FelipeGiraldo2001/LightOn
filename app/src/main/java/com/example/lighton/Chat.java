package com.example.lighton;

public class Chat {
    private String mName;
    private String mMessage;
    private String mUid;
    private String fotoPerfilURL;

    public Chat(String key, Chat model) {}  // Needed for Firebase

    public Chat(String mName, String mMessage, String mUid, String fotoPerfilURL) {
        this.mName = mName;
        this.mMessage = mMessage;
        this.mUid = mUid;
        this.fotoPerfilURL = fotoPerfilURL;
    }

    public String getName() { return mName; }

    public void setName(String name) { mName = name; }

    public String getMessage() { return mMessage; }

    public void setMessage(String message) { mMessage = message; }

    public String getUid() { return mUid; }

    public void setUid(String uid) { mUid = uid; }

    public String getFotoPerfilURL() {
        return fotoPerfilURL;
    }

    public void setFotoPerfilURL(String fotoPerfilURL) {
        this.fotoPerfilURL = fotoPerfilURL;
    }
}
