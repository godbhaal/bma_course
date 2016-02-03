package bma.android.projectefinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import bma.android.projectefinal.R;

/**
 * Created by Duffman on 1/2/16.
 */
public class CalculadoraAvansadaFragment extends Fragment {

    private static final String name = "Advanced";
    private CalculadoraFragment calculadoraFragment;        // Seria millor ferho amb una interficie.. pero..
    private View view;
    private Button button_mod, button_to2, button_sqr, button_per, button_sin, button_cos, button_tan, button_exp;
    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String st = ((Button) v).getText().toString();
            Log.d("ButtonClicked: ", st);
            if (st.equals("mod")) {
                calculadoraFragment.check(st);
            }
            else {
                Toast.makeText(getContext(), "Operation not implemented yet", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public static CalculadoraAvansadaFragment newInstance() {
        Bundle args = new Bundle();
        CalculadoraAvansadaFragment fragment = new CalculadoraAvansadaFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calculadora_avansada_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        manageOperations();
    }

    private void manageOperations() {
        button_mod = (Button) view.findViewById(R.id.button_mod);
        button_mod.setOnClickListener(buttonClickListener);
        button_to2 = (Button) view.findViewById(R.id.button_to2);
        button_to2.setOnClickListener(buttonClickListener);
        button_sqr = (Button) view.findViewById(R.id.button_sqr);
        button_sqr.setOnClickListener(buttonClickListener);
        button_per = (Button) view.findViewById(R.id.button_per);
        button_per.setOnClickListener(buttonClickListener);
        button_sin= (Button) view.findViewById(R.id.button_sin);
        button_sin.setOnClickListener(buttonClickListener);
        button_cos= (Button) view.findViewById(R.id.button_cos);
        button_cos.setOnClickListener(buttonClickListener);
        button_tan = (Button) view.findViewById(R.id.button_tan);
        button_tan.setOnClickListener(buttonClickListener);
        button_exp = (Button) view.findViewById(R.id.button_exp);
        button_exp.setOnClickListener(buttonClickListener);
    }

    public String getName() {
        return name;
    }

    public void setCalculadoraFragment(CalculadoraFragment calculadoraFragment) {
        this.calculadoraFragment = calculadoraFragment;
    }
}
