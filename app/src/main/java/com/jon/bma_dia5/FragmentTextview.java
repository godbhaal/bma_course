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
public class FragmentTextview extends Fragment {

    private Boolean st;
    private SharedPreferences sharedPref;
    private TextView textView;

    public static FragmentTextview newInstance(){
        Bundle args = new Bundle();
        FragmentTextview fragment = new FragmentTextview();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_textview, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        textView = (TextView) getActivity().findViewById(R.id.textview_fragment_textview1);
        st = sharedPref.getBoolean("pref_1", true);
        textView.setText(st.toString());
    }
}
