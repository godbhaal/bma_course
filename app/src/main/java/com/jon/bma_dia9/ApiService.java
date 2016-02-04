package com.jon.bma_dia9;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jon on 04/02/16.
 * Importar:    Gson is a Java library that can be used to convert Java Objects into their JSON representation
 * compile 'com.squareup.retrofit2:retrofit:2.0.0-beta3',
 *              compile 'com.squareup.retrofit2:converter-gson:2.0.0-beta3'
 *              compile 'com.google.code.gson:gson:2.5'
 */
public interface ApiService {
//    @GET("/v2/stations")
    @GET("/stations")
    public Call<JsonObject> getBikesBarcelona();
}
