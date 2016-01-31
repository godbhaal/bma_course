package jon.com.bma_dia2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jon on 26/01/16. Exercise 4: Build a complete interface to test diferent Views.
 */
public class InterfaceActivity extends AppCompatActivity {

    private String textToToast;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_activity);

        Button button = (Button) findViewById(R.id.button_send);
        final TextView text_name = (TextView) findViewById(R.id.tname);
        final TextView text_mail = (TextView) findViewById(R.id.tmail);
        final CheckBox check_interest1 = (CheckBox) findViewById(R.id.check_interest1);
        final CheckBox check_interest2 = (CheckBox) findViewById(R.id.check_interest2);
        final CheckBox check_interest3 = (CheckBox) findViewById(R.id.check_interest3);
        final RadioButton radio_gift1 = (RadioButton) findViewById(R.id.radio_gift1);
        final RadioButton radio_gift2 = (RadioButton) findViewById(R.id.radio_gift2);
        final RadioButton radio_gift3 = (RadioButton) findViewById(R.id.radio_gift3);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean oneSelected = false;
                Log.d("InterfaceActivity", "Clicked on button!");
                String warning;

                textToToast = text_name.getText().toString() + ", your interest are ";

                if (check_interest1.isChecked()) {
                    textToToast += check_interest1.getText().toString();
                    oneSelected = true;
                }
                if (check_interest2.isChecked()) {
                    if (oneSelected) textToToast += ", ";
                    textToToast += check_interest2.getText().toString();
                    oneSelected = true;
                }
                if (check_interest3.isChecked()) {
                    if (oneSelected) textToToast += ", ";
                    textToToast += check_interest3.getText().toString();
                    oneSelected = true;
                }

                if (!oneSelected) {
                    warning = "You have to select one Interest at least!";
                    toast = Toast.makeText(v.getContext(), warning, Toast.LENGTH_LONG);
                    toast.show();
                }

                textToToast += ". And you want to win ";
                oneSelected = false;
                if (radio_gift1.isChecked()) {
                    textToToast += radio_gift1.getText().toString();
                    oneSelected = true;
                }
                if (radio_gift2.isChecked()) {
                    if (oneSelected) textToToast += ", ";
                    textToToast += radio_gift2.getText().toString();
                    oneSelected = true;
                }
                if (radio_gift3.isChecked()) {
                    if (oneSelected) textToToast += ", ";
                    textToToast += radio_gift3.getText().toString();
                    oneSelected = true;
                }

                if (!oneSelected) {
                    warning = "You have to select one Gift!";
                    toast = Toast.makeText(v.getContext(), warning, Toast.LENGTH_LONG);
                    toast.show();
                }

                textToToast += ". If you win we will send you a notice to " + text_mail.getText().toString();
                toast = Toast.makeText(v.getContext(), textToToast, Toast.LENGTH_LONG);
                toast.show();
            }

        });


    }
}
