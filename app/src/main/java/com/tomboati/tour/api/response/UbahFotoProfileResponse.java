package com.tomboati.tour.api.response;

import com.google.gson.annotations.SerializedName;

public class UbahFotoProfileResponse {
    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("file_foto")
    private String foto;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getFoto() {
        return foto;
    }
}
