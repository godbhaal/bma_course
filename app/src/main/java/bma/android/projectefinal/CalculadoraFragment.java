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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by jon on 02/02/16.
 */
public class CalculadoraFragment extends Fragment implements View.OnClickListener {

    private static final String name = "CalculadoraFragment";
    private ToggleButton toggleButton;
    private CalculadoraBasicaFragment calculadoraBasica;
    private CalculadoraAvansadaFragment calculadoraAvansada;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private TextView display;
    private View view;
    private Button button_0;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;

    private Button button_result;
    private String outputString;
    private String inputString;
    private String firstString, secondString;
    private int first, second;
    private final int STATE_FIRST = 1;
    private final int STATE_OPERATION = 2;
    private final int STATE_SECOND = 3;
    private final int STATE_RESULT = 4;
    private boolean clickedBefore = false;
    private int state;
    private String operation;

    public static CalculadoraFragment newInstance() {
        Bundle args = new Bundle();
        CalculadoraFragment fragment = new CalculadoraFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calculadora_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Check mode (Basic/Advanced)
        turnToBasic();
        toggleButton = (ToggleButton) view.findViewById(R.id.button_calc_change);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    turnToAdvanced();
                } else {
                    turnToBasic();
                }
            }
        });

        display = (TextView) view.findViewById(R.id.calculator_display);
        inputString = "";
        outputString = "";
        //Manage calculator keyboard
        manageNumeric();
        manageOperations();
    }

    private void manageOperations() {
        button_result = (Button) view.findViewById(R.id.button_result);

        button_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ButtonClicked: ", button_result.getText().toString());
                check(button_result.getText().toString());
            }
        });
    }

    private void manageNumeric() {
        button_0 = (Button) view.findViewById(R.id.button_0);
        button_1 = (Button) view.findViewById(R.id.button_1);
        button_2 = (Button) view.findViewById(R.id.button_2);
        button_3 = (Button) view.findViewById(R.id.button_3);
        button_4 = (Button) view.findViewById(R.id.button_4);
        button_5 = (Button) view.findViewById(R.id.button_5);
        button_6 = (Button) view.findViewById(R.id.button_6);
        button_7 = (Button) view.findViewById(R.id.button_7);
        button_8 = (Button) view.findViewById(R.id.button_8);
        button_9 = (Button) view.findViewById(R.id.button_9);

        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ButtonClicked: ", button_0.getText().toString());
                check(button_0.getText().toString());
            }
        });

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ButtonClicked: ", button_1.getText().toString());
                check(button_1.getText().toString());
            }
        });

    }

    protected void check(String newInput) {


        int number = -1;
        try {
            number = Integer.valueOf(newInput);
            Log.d("check()", String.valueOf(number));
        } catch (NumberFormatException e) {
        }

        if (!clickedBefore) {
            state = STATE_FIRST;
        }

        if (state == STATE_FIRST) { // save the number as first, change state if an operation is clicked
            // If it is a number
            if (number != -1) {
                inputString += newInput;
                firstString += newInput;
            } else {
                state = STATE_OPERATION;
                // ask to fragment to do the operation
            }

        } else if (state == STATE_SECOND) {    // save the number as first, change state if '=' is clicked
            if (number != -1) {
                inputString += newInput;
                secondString += newInput;
            } else {
                state = STATE_RESULT;
            }

        } else if (state == STATE_OPERATION) {
            inputString += " "+newInput+" ";
            operation = newInput;
            state = STATE_SECOND;

        } else if (state == STATE_RESULT){  // do the operation

        } else {
            Log.e("check()", "impossible state!");
        }

        display.setText(outputString);
        clickedBefore = true;
    }

    private void turnToBasic() {
        calculadoraAvansada = null;
        fm = getActivity().getSupportFragmentManager();
        calculadoraBasica = CalculadoraBasicaFragment.newInstance();
        calculadoraBasica.setCalculadoraFragment(this);
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container_calc, calculadoraBasica);

        TextView textView = (TextView) view.findViewById(R.id.title_calculator);
        textView.setText(R.string.calc_type_basic);
        ft.commit();
        fm = null;
        ft = null;
    }

    private void turnToAdvanced() {
        calculadoraBasica = null;
        fm = getChildFragmentManager();
        calculadoraAvansada = CalculadoraAvansadaFragment.newInstance();
        calculadoraAvansada.setCalculadoraFragment(this);
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container_calc, calculadoraAvansada);

        TextView textView = (TextView) view.findViewById(R.id.title_calculator);
        textView.setText(R.string.calc_type_advanced);
        ft.commit();
        fm = null;
        ft = null;
    }

    @Override
    public void onClick(View v) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.get
            }
        });
    }
}
