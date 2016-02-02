package bma.android.projectefinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bma.android.projectefinal.R;

/**
 * Created by Duffman on 1/2/16.
 */
public class CalculadoraAvansadaFragment extends Fragment {

    private static final String ARG_TITLE = "CalculadoraAvansadaFragment";

    public static CalculadoraAvansadaFragment newInstance() {
        Bundle args = new Bundle();
        CalculadoraAvansadaFragment fragment = new CalculadoraAvansadaFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calculadora_avansada_fragment,container,false);

        return view;
    }
}
