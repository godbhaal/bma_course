package com.jon.bma_dia9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ApiAdapter extends BaseAdapter{

    Context context;
    JsonArray array;

    public ApiAdapter(Context context, JsonArray array) {
        this.context = context;
        this.array = array;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position).getAsJsonObject();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.api_adapter_element, parent, false);
        TextView textView_streetName = (TextView) view.findViewById(R.id.streetName);
        TextView textView_bikes = (TextView) view.findViewById(R.id.numberOfBikes);
        TextView textView_status = (TextView) view.findViewById(R.id.status);
        JsonObject station = array.get(position).getAsJsonObject();
        String stationName = station.get("streetName").getAsString();
        String numberOfBikes = station.get("bikes").getAsString();
        String status = station.get("status").getAsString();

        textView_streetName.setText(stationName);
        textView_bikes.setText(numberOfBikes);
        textView_status.setText(status);
        return view;
    }
}