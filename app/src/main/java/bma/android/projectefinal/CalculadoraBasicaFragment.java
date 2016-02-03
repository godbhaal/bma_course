package bma.android.projectefinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Duffman on 1/2/16.
 */
public class CalculadoraBasicaFragment extends Fragment {
    private static final String name = "Basic";
    private View view;
    private CalculadoraFragment calculadoraFragment;
    private Button button_sum, button_sub, button_div, button_mul;
    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String st = ((Button) v).getText().toString();
            Log.d("ButtonClicked: ", st);
            calculadoraFragment.check(st);
        }
    };

    public static CalculadoraBasicaFragment newInstance() {
        Bundle args = new Bundle();
        CalculadoraBasicaFragment fragment = new CalculadoraBasicaFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calculadora_basica_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        manageOperations();
    }

    public String getName() {
        return name;
    }

    public void setCalculadoraFragment(CalculadoraFragment calculadoraFragment) {
        this.calculadoraFragment = calculadoraFragment;
    }

    private void manageOperations() {
        button_sum = (Button) view.findViewById(R.id.button_sum);
        button_sub = (Button) view.findViewById(R.id.button_sub);
        button_mul = (Button) view.findViewById(R.id.button_mul);
        button_div = (Button) view.findViewById(R.id.button_div);
        button_sum.setOnClickListener(buttonClickListener);
        button_sub.setOnClickListener(buttonClickListener);
        button_mul.setOnClickListener(buttonClickListener);
        button_div.setOnClickListener(buttonClickListener);
    }
}
