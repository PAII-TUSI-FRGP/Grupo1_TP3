package com.example.grupo1_tp3.daoSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.grupo1_tp3.entidad.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DaoHelperUsuario {
    public static boolean insertar(Usuario usuario, Context context) {
        boolean resultado = false;
        try {
            SQLiteDatabase con = new ConectSQLiteHelperDB(context).getWritableDatabase();
            ContentValues nuevoUsuario = new ContentValues();
            nuevoUsuario.put(ConectSQLiteHelperDB.COL_USUARIO_NOMBRE, usuario.getNombre());
            nuevoUsuario.put(ConectSQLiteHelperDB.COL_USUARIO_EMAIL, usuario.getEmail());
            nuevoUsuario.put(ConectSQLiteHelperDB.COL_USUARIO_PASSWORD, usuario.getPassword());

            long id = con.insert(ConectSQLiteHelperDB.TABLE_USUARIO, null, nuevoUsuario);
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

    public static Usuario obtenerUno(String nombre, Context context) {
        Usuario usuario = new Usuario();
        try {
            SQLiteDatabase con = new ConectSQLiteHelperDB(context).getReadableDatabase();
            String[] projection = null;
            String selection = ConectSQLiteHelperDB.COL_USUARIO_NOMBRE + " = ?";
            String[] selectionArgs = {nombre};
            String sortOrder = null;
            Cursor cursor = con.query(
                    ConectSQLiteHelperDB.TABLE_USUARIO,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            );

            while (cursor.moveToNext()) {
                usuario.setNombre(cursor.getString(cursor.getColumnIndexOrThrow(ConectSQLiteHelperDB.COL_USUARIO_NOMBRE)));
                usuario.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(ConectSQLiteHelperDB.COL_USUARIO_EMAIL)));
                usuario.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(ConectSQLiteHelperDB.COL_USUARIO_PASSWORD)));
            }

            cursor.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return usuario;
        }
    }

    /**
     * Obtiene todos los usuarios de la base de datos
     *
     * @param context contexto de la aplicacion
     * @return lista de usuarios
     * @see <a href="https://developer.android.com/training/data-storage/sqlite?hl=es-419">documentacion api</a>
     */
    public static List<Usuario> obtenerTodos(Context context) {
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            SQLiteDatabase con = new ConectSQLiteHelperDB(context).getReadableDatabase();
            String[] projection = null;
            String selection = null;
            String[] selectionArgs = null;
            String sortOrder = null;

            Cursor cursor = con.query(
                    ConectSQLiteHelperDB.TABLE_USUARIO,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            );

            while (cursor.moveToNext()) {
                Usuario usuario = new Usuario();
                usuario.setNombre(cursor.getString(cursor.getColumnIndexOrThrow(ConectSQLiteHelperDB.COL_USUARIO_NOMBRE)));
                usuario.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(ConectSQLiteHelperDB.COL_USUARIO_EMAIL)));
                usuario.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(ConectSQLiteHelperDB.COL_USUARIO_PASSWORD)));
                lista.add(usuario);
            }

            cursor.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return lista;
        }
    }

    public static boolean actualizar(Usuario usuario, Context context) {
        boolean resultado = false;
        try {
            SQLiteDatabase con = new ConectSQLiteHelperDB(context).getWritableDatabase();
            ContentValues nuevoUsuario = new ContentValues();
            nuevoUsuario.put(ConectSQLiteHelperDB.COL_USUARIO_NOMBRE, usuario.getNombre());
            nuevoUsuario.put(ConectSQLiteHelperDB.COL_USUARIO_EMAIL, usuario.getEmail());
            nuevoUsuario.put(ConectSQLiteHelperDB.COL_USUARIO_PASSWORD, usuario.getPassword());

            String selection = ConectSQLiteHelperDB.COL_USUARIO_NOMBRE + " LIKE ?";
            String[] selectionArgs = {usuario.getNombre()};
            int count = con.update(
                    ConectSQLiteHelperDB.TABLE_USUARIO,
                    nuevoUsuario,
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

    public static boolean eliminar(String nombre, Context context) {
        boolean resultado = false;
        try {
            SQLiteDatabase con = new ConectSQLiteHelperDB(context).getWritableDatabase();
            String selection = ConectSQLiteHelperDB.COL_USUARIO_NOMBRE + " LIKE ?";
            String[] selectionArgs = {nombre};
            int count = con.delete(
                    ConectSQLiteHelperDB.TABLE_USUARIO,
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

}
