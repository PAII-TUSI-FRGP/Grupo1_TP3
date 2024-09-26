package com.example.grupo1_tp3.daoSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.grupo1_tp3.entidad.Parkeo;

import java.util.ArrayList;
import java.util.List;

public class DaoHelperParkeo {
    public static boolean insertar(Parkeo parkeo, Context context) {
        boolean resultado = false;
        try {
            SQLiteDatabase con = new ConectSQLiteHelperDB(context).getWritableDatabase();
            ContentValues nuevoParkeo = new ContentValues();
            nuevoParkeo.put(ConectSQLiteHelperDB.COL_PARQUEO_MATRICULA, parkeo.getMatricula());
            nuevoParkeo.put(ConectSQLiteHelperDB.COL_PARQUEO_TIEMPO, parkeo.getTiempo());
            nuevoParkeo.put(ConectSQLiteHelperDB.COL_PARQUEO_NOMBRE_PAR, parkeo.getNombre_Par().getNombre());

            long id = con.insert(ConectSQLiteHelperDB.TABLE_PARQUEO, null, nuevoParkeo);
            if (id > 0) {
                resultado = true;
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return resultado;
        }
    }

    public static Parkeo obtenerUno(String matricula, Context context) {
        Parkeo parkeo = new Parkeo();
        try {
            SQLiteDatabase con = new ConectSQLiteHelperDB(context).getReadableDatabase();
            String[] projection = null;
            String selection = ConectSQLiteHelperDB.COL_PARQUEO_MATRICULA + " = ?";
            String[] selectionArgs = {matricula};
            String sortOrder = null;
            Cursor cursor = con.query(
                    ConectSQLiteHelperDB.TABLE_PARQUEO,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            );

            while (cursor.moveToNext()) {
                parkeo.setMatricula(cursor.getString(cursor.getColumnIndexOrThrow(ConectSQLiteHelperDB.COL_PARQUEO_MATRICULA)));
                parkeo.setTiempo(cursor.getString(cursor.getColumnIndexOrThrow(ConectSQLiteHelperDB.COL_PARQUEO_TIEMPO)));
                parkeo.setNombre_Par(DaoHelperUsuario.obtenerUno(cursor.getString(cursor.getColumnIndexOrThrow(ConectSQLiteHelperDB.COL_PARQUEO_NOMBRE_PAR)), context));
            }

            cursor.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return parkeo;
        }
    }

    public static List<Parkeo> obtenerTodos(Context context) {
        List<Parkeo> lista = new ArrayList<Parkeo>();
        try {
            SQLiteDatabase con = new ConectSQLiteHelperDB(context).getReadableDatabase();
            String[] projection = null;
            String selection = null;
            String[] selectionArgs = null;
            String sortOrder = null;

            Cursor cursor = con.query(
                    ConectSQLiteHelperDB.TABLE_PARQUEO,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            );

            while (cursor.moveToNext()) {
                Parkeo parkeo = new Parkeo();
                parkeo.setMatricula(cursor.getString(cursor.getColumnIndexOrThrow(ConectSQLiteHelperDB.COL_PARQUEO_MATRICULA)));
                parkeo.setTiempo(cursor.getString(cursor.getColumnIndexOrThrow(ConectSQLiteHelperDB.COL_PARQUEO_TIEMPO)));
                parkeo.setNombre_Par(DaoHelperUsuario.obtenerUno(cursor.getString(cursor.getColumnIndexOrThrow(ConectSQLiteHelperDB.COL_PARQUEO_NOMBRE_PAR)), context));
                lista.add(parkeo);
            }

            cursor.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return lista;
        }
    }

    public static boolean actualizar(Parkeo parkeo, Context context) {
        boolean resultado = false;
        try {
            SQLiteDatabase con = new ConectSQLiteHelperDB(context).getWritableDatabase();
            ContentValues nuevoParkeo = new ContentValues();
            nuevoParkeo.put(ConectSQLiteHelperDB.COL_PARQUEO_MATRICULA, parkeo.getMatricula());
            nuevoParkeo.put(ConectSQLiteHelperDB.COL_PARQUEO_TIEMPO, parkeo.getTiempo());
            nuevoParkeo.put(ConectSQLiteHelperDB.COL_PARQUEO_NOMBRE_PAR, parkeo.getNombre_Par().getNombre());

            String selection = ConectSQLiteHelperDB.COL_PARQUEO_MATRICULA + " LIKE ?";
            String[] selectionArgs = {parkeo.getMatricula()};
            int count = con.update(
                    ConectSQLiteHelperDB.TABLE_PARQUEO,
                    nuevoParkeo,
                    selection,
                    selectionArgs);
            if (count > 0) {
                resultado = true;
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return resultado;
        }
    }

    public static boolean eliminar(String matricula, Context context) {
        boolean resultado = false;
        try {
            SQLiteDatabase con = new ConectSQLiteHelperDB(context).getWritableDatabase();
            String selection = ConectSQLiteHelperDB.COL_PARQUEO_MATRICULA + " LIKE ?";
            String[] selectionArgs = {matricula};
            int count = con.delete(
                    ConectSQLiteHelperDB.TABLE_PARQUEO,
                    selection,
                    selectionArgs);
            if (count > 0) {
                resultado = true;
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return resultado;
        }
    }

    public static List<Parkeo> obtenerPorNombre(String nombre, Context context) {
        List<Parkeo> lista = new ArrayList<Parkeo>();
        try {
            SQLiteDatabase con = new ConectSQLiteHelperDB(context).getReadableDatabase();
            String[] projection = null;
            String selection = ConectSQLiteHelperDB.COL_PARQUEO_NOMBRE_PAR + " = ?";
            String[] selectionArgs = {nombre};
            String sortOrder = null;

            Cursor cursor = con.query(
                    ConectSQLiteHelperDB.TABLE_PARQUEO,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            );

            while (cursor.moveToNext()) {
                Parkeo parkeo = new Parkeo();
                parkeo.setMatricula(cursor.getString(cursor.getColumnIndexOrThrow(ConectSQLiteHelperDB.COL_PARQUEO_MATRICULA)));
                parkeo.setTiempo(cursor.getString(cursor.getColumnIndexOrThrow(ConectSQLiteHelperDB.COL_PARQUEO_TIEMPO)));
                parkeo.setNombre_Par(DaoHelperUsuario.obtenerUno(cursor.getString(cursor.getColumnIndexOrThrow(ConectSQLiteHelperDB.COL_PARQUEO_NOMBRE_PAR)), context));
                lista.add(parkeo);
            }

            cursor.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return lista;
        }
    }
}
