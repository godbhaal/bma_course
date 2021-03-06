package bma.android.projectefinal;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jon on 04/02/16.
 */
public interface ApiService {
    @GET("latest")
    public Call<JsonObject> getLatestExchange();

    @GET("latest")
    public Call<JsonObject> getConversion(@Query("base") String currency_base_name);

    // Més òptim desde el punt de vista de descàrrega de dades
    @GET("latest")
    public Call<JsonObject> getConversion2(@Query("base") String currency_base_name, @Query("symbols") String currency_wanted_name);
}

