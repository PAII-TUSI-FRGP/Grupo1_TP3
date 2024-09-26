package com.example.grupo1_tp3.daoSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @see <a href="https://developer.android.com/training/data-storage/sqlite?hl=es-419">documentacion api</a>
 */
public class ConectSQLiteHelperDB extends SQLiteOpenHelper {
    /* BD */
    // Constantes para crear la base de datos
    public static final String DB_NAME = "BD_TP3";
    public static final int DB_VERSION = 1;

    /* TABLAS */
    //Constantes para crear la tabla de usuario
    public static final String TABLE_USUARIO = "Usuario";
    public static final String COL_USUARIO_NOMBRE = "Nombre";
    public static final String COL_USUARIO_EMAIL = "Email";
    public static final String COL_USUARIO_PASSWORD = "Password";
    private static final String SQL_DROP_TABLE_USUARIO = "DROP TABLE IF EXISTS " + TABLE_USUARIO + ";";
    private static final String SQL_CREATE_TABLE_USUARIO = "CREATE TABLE IF NOT EXISTS " + TABLE_USUARIO + " (" +
            COL_USUARIO_NOMBRE + " TEXT NOT NULL," +
            COL_USUARIO_EMAIL + " TEXT NOT NULL," +
            COL_USUARIO_PASSWORD + " TEXT NOT NULL," +
            "CONSTRAINT PK_Usuario PRIMARY KEY (" + COL_USUARIO_NOMBRE + ")" +
            ");";

    //Constantes para crear la tabla de parqueo
    public static final String TABLE_PARQUEO = "Parqueo";
    public static final String COL_PARQUEO_MATRICULA = "Matricula";
    public static final String COL_PARQUEO_TIEMPO = "Tiempo";
    public static final String COL_PARQUEO_NOMBRE_PAR = "Nombre_Par";
    private static final String SQL_DROP_TABLE_PARQUEO = "DROP TABLE IF EXISTS " + TABLE_PARQUEO + ";";
    private static final String SQL_CREATE_TABLE_PARQUEO = "CREATE TABLE IF NOT EXISTS " + TABLE_PARQUEO + " (" +
            COL_PARQUEO_MATRICULA + " TEXT NOT NULL," +
            COL_PARQUEO_TIEMPO + " TEXT NOT NULL," +
            COL_PARQUEO_NOMBRE_PAR + " TEXT NOT NULL," +
            "CONSTRAINT PK_Parqueo PRIMARY KEY (" + COL_PARQUEO_MATRICULA + ")," +
            "CONSTRAINT FK_Parqueo_Usuario FOREIGN KEY (" + COL_PARQUEO_NOMBRE_PAR + ") REFERENCES " + TABLE_USUARIO + "(" + COL_USUARIO_NOMBRE + ")" +
            ");";

    public ConectSQLiteHelperDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_USUARIO);
        db.execSQL(SQL_CREATE_TABLE_PARQUEO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE_PARQUEO);
        db.execSQL(SQL_DROP_TABLE_USUARIO);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onDowngrade(db, oldVersion, newVersion);
    }
}
