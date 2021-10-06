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

    public BacaanSholatResponse(Integer id, String name, String arabic, String latin, String terjemahan) {
        this.id = id;
        this.name = name;
        this.arabic = arabic;
        this.latin = latin;
        this.terjemahan = terjemahan;
    }

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