package com.jon.bma_dia5;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by jon on 31/01/16.
 */
public class Fragment_crono  extends Fragment {
    private Handler h = new Handler();
    private int delay = 1000, counterValue = 0;
    public static final String TAG = Fragment_crono.class.getSimpleName();
    private boolean executing = false;
    private View view;

    public static Fragment_crono newInstance(){
        Bundle args = new Bundle();
        Fragment_crono fragment = new Fragment_crono();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.crono_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonStart = (Button) getActivity().findViewById(R.id.button_start);
        Button buttonStop = (Button) getActivity().findViewById(R.id.button_stop);
        Button buttonReset = (Button) getActivity().findViewById(R.id.button_reset);
        final TextView tCounter = (TextView) getActivity().findViewById(R.id.tcounter);
        final TextView tCounterLast = (TextView) getActivity().findViewById(R.id.tcounterlast);
        ToggleButton tgSpeed = (ToggleButton) getActivity().findViewById(R.id.toggleSpeed);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!executing) {
                    executing = true;
                    h.postDelayed(new Runnable() {
                        public void run() {
                            if (executing) {
                                counterValue = Integer.parseInt(tCounter.getText().toString());
                                counterValue++;
                                tCounter.setText(String.valueOf(counterValue));
                                Log.d(TAG, "Counting " + counterValue);
                                h.postDelayed(this, delay);
                            }
                        }
                    }, delay);
                }
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Will stop the counter
                executing = false;
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tCounterLast.setText("Last value: "+tCounter.getText().toString());
                tCounter.setText("0");
            }
        });

        tgSpeed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    delay = 100;
                } else {
                    delay = 1000;
                }
            }

        });

    }
}
