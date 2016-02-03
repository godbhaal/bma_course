package com.jon.bma_dia7;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

// Per comparar..     st.equals("a) donara nullpointerExecep si f es null
//                      "a".equals(st) no dona null

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "personaldata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Lloc optim per carregar valors

        // El 0 indica 'mode public' osigui que altres aplicacions podran accedir (no recommanable
        // mai). Si es vol comunicacio entre apps es poden fer amb Intents o ContentProviders.
        // MODE_PRIVATE indicara que sols la nostra aplicacio podra accedir.
//        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedName = settings.getString("Name", "");
        int savedAge = settings.getInt("Age", 0);
        boolean savedCheckbox = settings.getBoolean("Check", false);

        // Sempre sota de setContentView: sinó no sap on buscar
        CheckBox checkbox = (CheckBox) findViewById(R.id.checkbox);
        EditText nameText = (EditText) findViewById(R.id.name);
        EditText ageText = (EditText) findViewById(R.id.age);
        checkbox.setChecked(savedCheckbox);
        nameText.setText(savedName);
        if (savedAge != 0) {
            ageText.setText((CharSequence) String.valueOf(savedAge));
        }
        Log.d("[MainActivity]", "Activity Created");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Lloc optim per guardar valors
        CheckBox checkbox = (CheckBox) findViewById(R.id.checkbox);
        EditText nameText = (EditText) findViewById(R.id.name);
        EditText ageText = (EditText) findViewById(R.id.age);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        String name = nameText.getText().toString();
        boolean check = checkbox.isChecked();
        Editable ageEditable = ageText.getText();
        int age;
        if (ageEditable.length() != 0) {
            age = Integer.valueOf(ageEditable.toString());
            editor.putInt("Age", age);
        }
        else {
            // Per eliminar valors antics
            editor.remove("Age");
        }
        editor.putBoolean("Check", check);
        editor.putString("Name", name);
        editor.commit();

        Log.d("[MainActivity]", "Activity Stopped");
    }
}
