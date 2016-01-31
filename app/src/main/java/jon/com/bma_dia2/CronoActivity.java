package jon.com.bma_dia2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by jon on 26/01/16. Exercise 4: Chronometer application
 * Si es vol utilitzar de llarga durada -> Alarm
 */
public class CronoActivity extends AppCompatActivity {
    private Handler h = new Handler();
    private int delay = 1000, counterValue = 0;
    public static final String TAG = CronoActivity.class.getSimpleName();
    private boolean executing = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crono_activity);

        Button buttonStart = (Button) findViewById(R.id.button_start);
        Button buttonStop = (Button) findViewById(R.id.button_stop);
        Button buttonReset = (Button) findViewById(R.id.button_reset);
        final TextView tCounter = (TextView) findViewById(R.id.tcounter);
        final TextView tCounterLast = (TextView) findViewById(R.id.tcounterlast);
        ToggleButton tgSpeed = (ToggleButton) findViewById(R.id.toggleSpeed);

//        final Runnable threadCounter = new Runnable() {
//            public void run() {
//                // Starts counter
//                counterValue = Integer.parseInt(tCounter.getText().toString());
//                counterValue++;
//                tCounter.setText(String.valueOf(counterValue));
//                Log.d(TAG, "Counting " + counterValue);
//                h.postDelayed(this, delay);
//            }
//        };
//
//        buttonStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                h.removeCallbacks(threadCounter);       // to ensure that only one thread is running
//                h.postDelayed(threadCounter, delay);
//            }
//        });
//
//        buttonStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Stops counter
//                h.removeCallbacks(threadCounter);
//            }
//        });

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
