package com.jon.bma_dia8;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonCargar, buttonGuardar, buttonUpdate, buttonDelete;
    private EditText etName, etSurname, etAge;
    private RadioButton rbHome, rbDona;

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

                Cursor cursor = db.query(
                        Persona.PersonaEntry.TABLE_NAME,    //Table name
                        projection,   //the columns to return
                        null,   //the colums for the WHERE clause
                        null,   // the values for the WHERE clause
                        null,   // don't group the rows
                        null,   // don't filter by row groups
                        null    // the sort order
                );

                if (cursor.moveToFirst()) {
                    // s'ha d'accedir a les columnes per un Integer, no permet Strings
                    // segueix l'ordre de la 'projection'
                    String name = cursor.getString(0);
                    String surname = cursor.getString(1);
                    int age = cursor.getInt(2);
                    boolean esHome = cursor.getInt(3) == 1 ? true : false;
                    Persona persona = new Persona(name, surname, age, esHome);
                    // Posem la persona al View
                    setPersona(persona);
                }
            }
        });

        buttonGuardar = (Button) findViewById(R.id.buttonGuardar);
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Persona persona = getPersona();
                // Es crea la base de dades, s'actualitza.. el que faci falta
                PersistenciaDBHelper mDbHelper = new PersistenciaDBHelper(MainActivity.this);
                // Aconseguir la database
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put(Persona.PersonaEntry.COLUMN_NAME_NAME, persona.getNom());
                contentValues.put(Persona.PersonaEntry.COLUMN_NAME_SURNAME, persona.getCognoms());
                contentValues.put(Persona.PersonaEntry.COLUMN_NAME_AGE, persona.getEdat());
                contentValues.put(Persona.PersonaEntry.COLUMN_NAME_ES_HOME, persona.esHome());

                try {
                    /* El tercer parametre sempre a null. contentValues es la informacio de la fila
                    de la db, (hi havia un camp que era KEY) si fem servir inserOrThrow saltarà
                    una exception quan vulguem introduir una persona amb el mateix nom */
                    db.insertOrThrow(Persona.PersonaEntry.TABLE_NAME, null, contentValues);
                } catch (SQLiteConstraintException e) {
                    Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_SHORT).show();

                    /*String whereClause = Persona.PersonaEntry.COLUMN_NAME_NAME + " LIKE ?";
                    String[] whereArgs = { persona.getNom() };
                    db.update (
                        Persona.PersonaEntry.TABLE_NAME,
                        contentValues,
                        whereClause,
                        whereArgs
                    ); */
                }
            }
        });

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Persona persona = getPersona();
                // Es crea la base de dades, s'actualitza.. el que faci falta
                PersistenciaDBHelper mDbHelper = new PersistenciaDBHelper(MainActivity.this);
                // Aconseguir la database
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                // Nou valor
                ContentValues values = new ContentValues();
                values.put(Persona.PersonaEntry.COLUMN_NAME_AGE, persona.getEdat());
                values.put(Persona.PersonaEntry.COLUMN_NAME_SURNAME, persona.getCognoms());

                // Selecciona quina fila actualitzar segons la ID
                String selection = Persona.PersonaEntry.COLUMN_NAME_NAME + " LIKE ?";
                String[] selectionArgs = {persona.getNom()};

                // Fer query (Issue SQL statement)
                int count = db.update(
                        Persona.PersonaEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
            }
        });


        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Persona persona = getPersona();
                PersistenciaDBHelper mDbHelper = new PersistenciaDBHelper(MainActivity.this);
                SQLiteDatabase db = mDbHelper.getReadableDatabase();

                // Definir on
                String selection = Persona.PersonaEntry.COLUMN_NAME_NAME + " LIKE ?";
                // Especifica arguments (Specify arguments in placeholder order)
                String[] selectionArgs = {persona.getNom()};

                // Fer query (Issue SQL statement)
                int count = db.delete(
                        Persona.PersonaEntry.TABLE_NAME,
                        selection,
                        selectionArgs
                );
            }
        });


    }

    // Getter i Setter de persona, facilita la relacio Object.Persona<->View.Persona
    private Persona getPersona() {
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        int age = Integer.valueOf(etAge.getText().toString());
        boolean esHome = rbHome.isChecked();
        return new Persona(name, surname, age, esHome);
    }

    private void setPersona(Persona persona) {
        etName.setText(persona.getNom());
        etSurname.setText(persona.getCognoms());
        etAge.setText(String.valueOf(persona.getEdat()));
        rbHome.setChecked(persona.esHome());
        rbDona.setChecked(persona.esDona());
    }

}
