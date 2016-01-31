package com.jon.bma_dia5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

/**
 * Created by jon on 29/01/16.
 */
public class FragmentSpinner extends Fragment {

    private View view;
    private Spinner spinner;

    public static FragmentSpinner newInstance(){
        Bundle args = new Bundle();
        FragmentSpinner fragment = new FragmentSpinner();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_spinner, container, false);
        return view;
    }

    // NO ES POT USAR FINDVIEWBYID A LA FUNCIO ONCREATEVIEW PQ LA JERARQUIA ENCARA NO ESTA CREADA
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setAdapter(new MySpinnerAdapter(view.getContext()));
    }

}
