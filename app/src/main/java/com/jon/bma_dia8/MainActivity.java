package com.jon.bma_dia8;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    Button buttonCargar, buttonGuardar, buttonUpdate, buttonDelete;
    EditText etName, etSurname, etAge;
    RadioButton rbHome, rbDona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Informació entrada per l'usuari
        etName = (EditText) findViewById(R.id.editTextName);
        etSurname = (EditText) findViewById(R.id.editTextSurname);
        etAge = (EditText) findViewById(R.id.editTextAge);
        rbHome = (RadioButton) findViewById(R.id.radioButton_home);
        rbDona = (RadioButton) findViewById(R.id.radioButton_dona);

        // Gestionar botons
        buttonCargar = (Button) findViewById(R.id.buttonCargar);
        buttonCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersistenciaDBHelper perDB = new PersistenciaDBHelper(MainActivity.this);
                SQLiteDatabase db = perDB.getReadableDatabase();

                String[] projection = new String[]{
                        Persona.PersonaEntry.COLUMN_NAME_NAME,
                        Persona.PersonaEntry.COLUMN_NAME_SURNAME,
                        Persona.PersonaEntry.COLUMN_NAME_AGE,
                        Persona.PersonaEntry.COLUMN_NAME_ES_HOME
                };

//                Cursor cursor = db.query(
//                        Persona.PersonaEntry.TABLE_NAME,
//                        projection,   //the columns to return
//                        null,
//                )
            }
        });
    }

    // Getter i Setter de persona, facilita la relacio Object.Persona<->View.Persona
    private Persona getPersona(){
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        int age = Integer.valueOf(etAge.getText().toString());
        boolean esHome = rbHome.isChecked();
        return new Persona(name, surname, age, esHome);
    }

    private void setPersona(Persona persona){
        etName.setText(persona.getNom());
        etSurname.setText(persona.getCognoms());
        etAge.setText(persona.getEdat());
        rbHome.setChecked(persona.esHome());
        rbDona.setChecked(persona.esDona());
    }

}
