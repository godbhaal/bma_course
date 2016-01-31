package com.jon.bma_dia5;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jon on 29/01/16.
 */
public class Fragment_textview extends Fragment {

    private Boolean st;
    private SharedPreferences sharedPref;
    private TextView textView;

    public static Fragment_textview newInstance(){
        Bundle args = new Bundle();
        Fragment_textview fragment = new Fragment_textview();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment1, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        textView = (TextView) getActivity().findViewById(R.id.textView3);
        st = sharedPref.getBoolean("pref_1", true);
        textView.setText(st.toString());
    }
}
