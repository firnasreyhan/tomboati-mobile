package com.android.tomboati.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BacaanSholatResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("arabic")
    @Expose
    private String arabic;
    @SerializedName("latin")
    @Expose
    private String latin;
    @SerializedName("terjemahan")
    @Expose
    private String terjemahan;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArabic() {
        return arabic;
    }

    public String getLatin() {
        return latin;
    }

    public String getTerjemahan() {
        return terjemahan;
    }

}