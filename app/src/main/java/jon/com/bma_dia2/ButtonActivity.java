package jon.com.bma_dia2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by jon on 26/01/16. Exercise 3: Create an Activity from scratch
 */

// (Old versions) If there is a problem with the import use 'extends ActionBarActivity'
// 'extends Activity': is the official but you are not allow to use the Support Library (not recomended)
public class ButtonActivity extends AppCompatActivity {

    private Button buttonCopiar;
    private EditText et;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_activity); // Set button_activityty.xml from the Resources-Layout folder as the activity layout

        // Create elements using resources
        buttonCopiar = (Button) findViewById(R.id.buttonCopiarText);
        buttonCopiar.setBackgroundColor(Color.LTGRAY);
        et =  (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);

//        buttonCopiar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // copy the text in an EditText to a TextView when click on a Button
//                tv.setText(et.getText().toString());
//            }
//        });

        buttonCopiar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    buttonCopiar.setBackgroundColor(Color.LTGRAY);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonCopiar.setBackgroundColor(Color.BLUE);
                    tv.setText(et.getText().toString());
                }
                return false;
            }

        });

    }
}
