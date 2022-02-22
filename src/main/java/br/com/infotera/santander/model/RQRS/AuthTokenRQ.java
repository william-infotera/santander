package br.com.infotera.santander.model.RQRS;

import com.google.gson.annotations.SerializedName;

public class AuthTokenRQ {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public AuthTokenRQ() {
    }

    public AuthTokenRQ(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
