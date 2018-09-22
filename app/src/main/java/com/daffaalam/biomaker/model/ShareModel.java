package com.daffaalam.biomaker.model;

import com.google.gson.annotations.SerializedName;

public class ShareModel {

    @SerializedName("pesan")
    private String pesan;

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getPesan() {
        return pesan;
    }
}