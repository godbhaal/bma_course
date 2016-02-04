package bma.android.projectefinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import bma.android.projectefinal.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

//    {"USD","AUD","BGN","BRL","CAD","CHF","CNY","CZK","DKK","GBP","HKD","HRK","HUF","IDR","ILS","INR","JPY","KRW","MXN","MYR","NOK","NZD","PHP","PLN","RON","RUB","SEK","SGD","THB","TRY","ZAR","EUR"};

/**
 * Created by Duffman on 1/2/16.
 */
public class ConversorFragment extends Fragment {

    public static ConversorFragment newInstance() {
        Bundle args = new Bundle();
        ConversorFragment fragment = new ConversorFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.conversor_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<JsonObject> call = service.getLatestExchange();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Response<JsonObject> response) {
                Log.d("ConversorFragment", "body" + response.body());

                JsonObject body = response.body();
                JsonArray array = body.get("rates").getAsJsonArray();
//                listView.setAdapter(new ApiAdapter(ConversorFragment.this, array));
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("ConversorFragment", "error: " + t.getMessage());

            }
        });

    }
}




