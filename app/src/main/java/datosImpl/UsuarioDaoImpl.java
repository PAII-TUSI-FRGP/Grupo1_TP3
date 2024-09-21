package datosImpl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import datos.IUsuarioDao;
import entidad.Usuario;
import utilidades.BaseSQLite;

public class UsuarioDaoImpl implements IUsuarioDao {
    private SQLiteDatabase con;

    @Override
    public List<Usuario> obtenerTodos(Context context) {
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            con = new ConexionSQLiteHelper(context).getReadableDatabase();
            Cursor cursor = con.rawQuery(
                    "SELECT " +
                            BaseSQLite.COL_NOMBRE + "," +
                            BaseSQLite.COL_EMAIL + "," +
                            BaseSQLite.COL_PASSWORD +
                            " FROM " + BaseSQLite.TABLE_USUARIO,
                    null);
            if (cursor.moveToFirst()) {
                do {
                    Usuario usuario = new Usuario();
                    usuario.setNombre(cursor.getString(0));
                    usuario.setEmail(cursor.getString(1));
                    usuario.setPassword(cursor.getString(2));
                    lista.add(usuario);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return lista;
    }

    @Override
    public Usuario obtenerUno(String nombre, Context context) {
        Usuario usuario = new Usuario();
        try {
            con = new ConexionSQLiteHelper(context).getReadableDatabase();
            Cursor cursor = con.rawQuery(
                    "SELECT " +
                            BaseSQLite.COL_NOMBRE + "," +
                            BaseSQLite.COL_EMAIL + "," +
                            BaseSQLite.COL_PASSWORD +
                            " FROM " + BaseSQLite.TABLE_USUARIO +
                            " WHERE " + BaseSQLite.COL_NOMBRE + " = ?",
                    new String[]{nombre});
            if (cursor.moveToFirst()) {
                do {
                    usuario.setNombre(cursor.getString(0));
                    usuario.setEmail(cursor.getString(1));
                    usuario.setPassword(cursor.getString(2));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return usuario;
    }





    @Override
    public boolean insertar(Usuario usuario, Context context) {
        boolean resultado = false;
        try {
            con = new ConexionSQLiteHelper(context).getWritableDatabase();
            ContentValues nuevoUsuario = new ContentValues();
            nuevoUsuario.put(BaseSQLite.COL_NOMBRE, usuario.getNombre());
            nuevoUsuario.put(BaseSQLite.COL_EMAIL, usuario.getEmail());
            nuevoUsuario.put(BaseSQLite.COL_PASSWORD, usuario.getPassword());

            long id = con.insert(BaseSQLite.TABLE_USUARIO, null, nuevoUsuario);

            if (id > 0) {
                resultado = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return resultado;
    }

    @Override
    public boolean actualizar(Usuario usuario, Context context) {
        boolean resultado = false;
        try {
            con = new ConexionSQLiteHelper(context).getWritableDatabase();
            ContentValues nuevoUsuario = new ContentValues();
            nuevoUsuario.put(BaseSQLite.COL_NOMBRE, usuario.getNombre());
            nuevoUsuario.put(BaseSQLite.COL_EMAIL, usuario.getEmail());
            nuevoUsuario.put(BaseSQLite.COL_PASSWORD, usuario.getPassword());
            int id = con.update(
                    BaseSQLite.TABLE_USUARIO,
                    nuevoUsuario,
                    BaseSQLite.COL_NOMBRE + " = ?",
                    new String[]{usuario.getNombre()});
            if (id > 0) {
                resultado = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return resultado;
    }

    @Override
    public boolean eliminar(String nombre, Context context) {
        boolean resultado = false;
        try {
            con = new ConexionSQLiteHelper(context).getWritableDatabase();
            int id = con.delete(
                    BaseSQLite.TABLE_USUARIO,
                    BaseSQLite.COL_NOMBRE + " = ?",
                    new String[]{nombre});
            if (id > 0) {
                resultado = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return resultado;
    }
}
