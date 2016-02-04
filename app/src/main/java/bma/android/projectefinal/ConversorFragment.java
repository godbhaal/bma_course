package bma.android.projectefinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by Duffman on 1/2/16.
 */
public class ConversorFragment extends Fragment {

    private String[] avaliableRates = {"USD", "AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK",
            "DKK", "GBP", "HKD", "HRK", "HUF", "IDR", "ILS", "INR", "JPY", "KRW", "MXN", "MYR",
            "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "ZAR", "EUR"};
    private TextView vt_output_value;
    private EditText vt_input_currency, vt_input_value, vt_output_currency;
    private Button convert_button;
    private ApiService service;
    private String symbol;
    private String base;
    private Double input_value;

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

        service = retrofit.create(ApiService.class);

        vt_input_currency = (EditText) view.findViewById(R.id.input_currency);
        vt_input_value = (EditText) view.findViewById(R.id.input_value);
        vt_output_value = (TextView) view.findViewById(R.id.output_value);
        vt_output_currency = (EditText) view.findViewById(R.id.output_currency);

        vt_input_value.setText("1");
        vt_input_currency.setText("EUR");
        vt_output_currency.setText("USD");

        // FIXME: 04/02/16 NullPointer, revisar variables
        convert_button = (Button) view.findViewById(R.id.convert_button);
        convert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user inputs
                base = vt_input_currency.getText().toString();
                symbol = vt_output_currency.getText().toString();
                input_value = Double.valueOf(vt_input_value.getText().toString());

                // Do the query
                Call<JsonObject> call = service.getConversion(base);

//                Call<JsonObject> call = service.getLatestExchange();
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Response<JsonObject> response) {
                        JsonObject body = response.body();
                        Log.d("ConversorFragment", "body" + body);

                        JsonObject rates = body.get("rates").getAsJsonObject();
                        float rate = rates.get(symbol).getAsFloat();

                        double result = input_value * rate;
                        vt_output_value.setText(String.valueOf(result));
//                        listView.setAdapter(new ApiAdapter(ConversorFragment.this, array));
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.d("ConversorFragment", "error: " + t.getMessage());
                        Toast.makeText(getContext(), "Error on getting the data, try another time", Toast.LENGTH_SHORT);

                    }
                });

            }
        });




    }
}




