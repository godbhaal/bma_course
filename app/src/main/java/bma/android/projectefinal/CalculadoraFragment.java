package bma.android.projectefinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by jon on 02/02/16.
 */
public class CalculadoraFragment extends Fragment {

    private static final String name = "CalculadoraFragment";
    private ToggleButton toggleButton;
    private CalculadoraBasicaFragment calculadoraBasica;
    private CalculadoraAvansadaFragment calculadoraAvansada;
    private FragmentManager fm;
    private FragmentTransaction ft;

    public static CalculadoraFragment newInstance() {
        Bundle args = new Bundle();
        CalculadoraFragment fragment = new CalculadoraFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calculadora_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        turnToBasic();
        toggleButton = (ToggleButton) getActivity().findViewById(R.id.button_calc_change);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    turnToAdvanced();
                } else {
                    turnToBasic();
                }
            }
        });
    }
    private void turnToBasic(){
        calculadoraAvansada = null;
        fm = getActivity().getSupportFragmentManager();
        calculadoraBasica = CalculadoraBasicaFragment.newInstance();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container_calc, calculadoraBasica);

        TextView textView = (TextView) getActivity().findViewById(R.id.title_calculator);
        textView.setText(R.string.calc_type_basic);
        ft.commit();
        fm = null;
        ft = null;
    }

    private void turnToAdvanced(){
        calculadoraBasica = null;
        fm = getActivity().getSupportFragmentManager();
        calculadoraAvansada = CalculadoraAvansadaFragment.newInstance();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container_calc, calculadoraAvansada);

        TextView textView = (TextView) getActivity().findViewById(R.id.title_calculator);
        textView.setText(R.string.calc_type_advanced);
        ft.commit();
        fm = null;
        ft = null;
    }
}
