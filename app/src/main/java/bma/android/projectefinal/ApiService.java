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
    public Call<JsonObject> getConversion(@Query("sort") String currency_base_name);
}

