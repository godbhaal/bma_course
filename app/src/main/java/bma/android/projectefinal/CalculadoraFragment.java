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
import android.widget.Toast;
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

    private String outputString = "", firstString = "", secondString = "", operation = "";
    private int first, second, result;
    private final int STATE_INITIAL = 0, STATE_FIRST = 1, STATE_SECOND = 2;
    private boolean clickedBefore = false, isNumber;
    private int state = STATE_INITIAL;
    private int countNumbers = 0;
    private String resultString = "";

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
        Log.d("STATE: ", String.valueOf(state));
        // Check if it is a number
        try {
            int number = Integer.valueOf(newInput);
            Log.d("check() is number!", String.valueOf(number));
            isNumber = true;
        } catch (NumberFormatException e) {
            isNumber = false;
        }

        switch (state) {
            case STATE_INITIAL:
                if (isNumber) {
                    firstString += newInput;
                    state = STATE_FIRST;
                } else {
                    Toast.makeText(getContext(), "Please enter a number first", Toast.LENGTH_SHORT).show();
                }
                break;

            case STATE_FIRST:
                if (isNumber) {
                    firstString += newInput;
                } else if (newInput.equals("=")){
                    Toast.makeText(getContext(), "Please enter the second number ", Toast.LENGTH_SHORT).show();
                }
                else {
                    operation = newInput;
                    state = STATE_SECOND;
                }
                break;

            case STATE_SECOND:  //afegir una clausula que aseguri que hi ha almenys un numero al SECOND per poder fer IGUAL
                if (isNumber) {
                    secondString += newInput;
                } else if (newInput.equals("=")) {
                    first = Integer.valueOf(firstString);
                    second = Integer.valueOf(secondString);
                    if (operation.equals("+")) {
                        result = first + second;
                    } else if (operation.equals("-")) {
                        result = first - second;
                    }
                    resultString = "=" + String.valueOf(result);
                    state = STATE_INITIAL;
                }
                else {
                    Toast.makeText(getContext(), "One operation at a time!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(getContext(), "check(): Something bad happened", Toast.LENGTH_SHORT).show();
                break;
        }

        outputString = firstString + operation + secondString + resultString;
        display.setText(outputString);
        if (state == STATE_INITIAL) {
            firstString = "";
            secondString = "";
            operation = "";
            resultString = "";
        }
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
//        calculadoraAvansada.manageOperations();
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
