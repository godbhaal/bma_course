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

    private static final String name = "Advanced";
    // Seria millor ferho amb una interficie.. pero
    private CalculadoraFragment calculadoraFragment;
    private View view;

    public static CalculadoraAvansadaFragment newInstance() {
        Bundle args = new Bundle();
        CalculadoraAvansadaFragment fragment = new CalculadoraAvansadaFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calculadora_avansada_fragment,container,false);
        return view;
    }

    public String getName(){
        return name;
    }

    public void setCalculadoraFragment(CalculadoraFragment calculadoraFragment) {
        this.calculadoraFragment = calculadoraFragment;
    }
}
