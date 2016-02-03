package com.jon.bma_dia8;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jon on 03/02/16.
 */

// La db es guarda a una carpeta data que només es pot accedir si ets root.
// Aplicacio de pagament per poder veure la db: Settings Debugger  (sinó no es pot saber si es
//    guarda bé o que
public class PersistenciaDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PersistenciaDBHelper";
    private static final int DATABASE_VERSION = 1;

    public PersistenciaDBHelper(Context context) {
        //El tercer parametre sempre a null
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Persona.PersonaEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
