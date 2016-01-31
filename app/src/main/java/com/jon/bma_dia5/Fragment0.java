package com.jon.bma_dia5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jon on 29/01/16.
 */
public class Fragment0 extends Fragment {

    public static Fragment0 newInstance(){
        Bundle args = new Bundle();
        Fragment0 fragment = new Fragment0();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment0, container, false);
        return view;
    }
}
