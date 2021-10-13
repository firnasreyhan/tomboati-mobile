package com.tomboati.tour.api.response;

import com.google.gson.annotations.SerializedName;

public class PoinResponse {
    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("poin")
    private Object poin;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Object getPoin() {
        return poin;
    }
}
