package com.example.labmacmini01.androidwebservices.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.labmacmini01.androidwebservices.R;

public class Carro {
    private int id;
    private String tipo;
    private String nome;
    private String desc;
    private String urlFoto;
    private String urlVideo;
    private String latitude;
    private String longitude;

    public Carro(int id, String tipo, String nome, String desc, String urlFoto, String urlVideo, String latitude, String longitude) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.desc = desc;
        this.urlFoto = urlFoto;
        this.urlVideo = urlVideo;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}


