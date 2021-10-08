package com.tomboati.tour.api;

import com.tomboati.tour.api.response.MasjidResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterfaceMasjid {
    @GET("geocoding/v5/mapbox.places/{query}.json")
    Call<MasjidResponse> getMasjidTerdekat(
            @Path("query") String query,
            @Query("proximity") String proximity,
            @Query("limit") int limit,
            @Query("access_token") String access_token
    );
}
