package com.jon.bma_dia9;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jon on 04/02/16.  Working with APIs.
 * Sempre fer querys ASYNCRONES (per no bloquejar interface usuar)
 */
public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        // Indiquem URL
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://wservice.viabicing.cat/v2/")
                .addConverterFactory(GsonConverterFactory.create()) //Conversor a dif objectes <>
                .build();   // Construim
        // Indiquem la interficie
        ApiService service = retrofit.create(ApiService.class);
        // Recuperem el JSON (GET)
        Call<JsonObject> call = service.getBikesBarcelona();
        // fem la crida
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Response<JsonObject> response) {     // Si rebem el JSON
                Log.d("MainActivity", "body" + response.body());

                JsonObject body = response.body();
                JsonArray array = body.get("stations").getAsJsonArray();
//                listView.setAdapter(new ApiAdapter((MainActivity.this, array));
                listView.setAdapter(new ApiAdapter(MainActivity.this, array));
            }

            @Override
            public void onFailure(Throwable t) {    // Si no rebem el JSON
                Log.d("MainActivity", "error: " + t.getMessage());

            }
        });

    }

}
