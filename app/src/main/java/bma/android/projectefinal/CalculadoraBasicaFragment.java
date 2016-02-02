package bma.android.projectefinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Duffman on 1/2/16.
 */
public class CalculadoraBasicaFragment extends Fragment {


    private static final String name = "Basic";

    public static CalculadoraBasicaFragment newInstance() {
        Bundle args = new Bundle();
        CalculadoraBasicaFragment fragment = new CalculadoraBasicaFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calculadora_basica_fragment,container,false);


        return view;
    }

    public String getName(){
        return name;
    }

}
