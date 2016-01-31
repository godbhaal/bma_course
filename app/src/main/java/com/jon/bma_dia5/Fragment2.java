package com.jon.bma_dia5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by jon on 29/01/16.
 */
public class Fragment2 extends Fragment {

    private View view;
    private Spinner spinner;
    Boolean st;
    SharedPreferences sharedPref;
    TextView textView;

    public static Fragment2 newInstance(){
        Bundle args = new Bundle();
        Fragment2 fragment = new Fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment2, container, false);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        return view;
    }

    // NO ES POT USAR FINDVIEWBYID A LA FUNCIO ONCREATEVIEW PQ LA JERARQUIA ENCARA NO ESTA CREADA
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setAdapter(new MySpinnerAdapter(view.getContext()));
        textView = (TextView) getActivity().findViewById(R.id.textView3);
    }

    @Override
    public void onResume() {
        super.onResume();
        st = sharedPref.getBoolean("pref_1", true);
        textView.setText(st.toString());
    }
}
