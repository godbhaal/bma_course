package com.jon.bma_dia8;

import android.provider.BaseColumns;

/**
 * Created by jon on 03/02/16.
 */
public class Persona {
    private String nom, cognoms;
    private int edat;
    private boolean esHome;

    public Persona(String nom, String cognoms, int edat, boolean esHome) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.edat = edat;
        this.esHome = esHome;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public boolean esHome() {
        return esHome;
    }

    public boolean esDona() {
        return !esHome;
    }

    public void setEsHome(boolean esHome) {
        this.esHome = esHome;
    }

    // Interface per a la db, ajuda a no haver de recordar els noms dels camps o escriure mal querys
    // SQLite no permet booleans,
    // Aqui NO anirien les relacions entre taules
    public static abstract class PersonaEntry implements BaseColumns {
        public static final String TABLE_NAME = "persona";
        public static final String COLUMN_NAME_NAME = "nom";
        public static final String COLUMN_NAME_SURNAME = "cognom";
        public static final String COLUMN_NAME_AGE = "edat";
        public static final String COLUMN_NAME_ES_HOME = "esHome";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_NAME_NAME + " TEXT PRIMARY KEY, " +
                        COLUMN_NAME_SURNAME + " TEXT, " +
                        COLUMN_NAME_AGE + " INTEGER, " +
                        COLUMN_NAME_ES_HOME + " INTEGER " +
                        " )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
