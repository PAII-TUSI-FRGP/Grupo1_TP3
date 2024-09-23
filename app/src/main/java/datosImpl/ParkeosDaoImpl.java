package datosImpl;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import datos.IParkeosDao;
import entidad.Parkeos;
import entidad.Usuario;
import negocio.IParkeosNeg;
import utilidades.BaseSQLite;


public class ParkeosDaoImpl implements IParkeosDao {
    private SQLiteDatabase con;
    private UsuarioDaoImpl UsuDaoImpl = new UsuarioDaoImpl();
    //@Override
    public List<Parkeos> obtenerTodos(Context context) {
        List<Parkeos> lista = new ArrayList<Parkeos>();
        try {
            con = new ConexionSQLiteHelper(context).getReadableDatabase();
            Cursor cursor = con.rawQuery(
                    "SELECT " +
                            BaseSQLite.COL_MATRICULA + "," +
                            BaseSQLite.COL_TIEMPO + "," +
                            BaseSQLite.COL_NOMBRE_PAR +
                            " FROM " + BaseSQLite.TABLE_PARQUEO,
                    null);
            if (cursor.moveToFirst()) {
                do {
                    Parkeos parkeos = new Parkeos();
                    parkeos.setMatricula(cursor.getString(0));
                    parkeos.setTiempo(cursor.getString(1));
                    Usuario OUsuario = new Usuario();
                    OUsuario = UsuDaoImpl.obtenerUno(cursor.getString(2),context);

                    parkeos.setNombre_Par(OUsuario);
                    lista.add(parkeos);
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

 //   @Override
    public boolean insertar(Parkeos parkeos, Context context) {
        boolean resultado = false;
        try {
            con = new ConexionSQLiteHelper(context).getWritableDatabase();
            ContentValues nuevoParkeo = new ContentValues();
            nuevoParkeo.put(BaseSQLite.COL_NOMBRE_PAR, parkeos.getNombre_Par().getNombre());
            nuevoParkeo.put(BaseSQLite.COL_MATRICULA, parkeos.getMatricula());
            nuevoParkeo.put(BaseSQLite.COL_TIEMPO, parkeos.getTiempo());

            long id = con.insert(BaseSQLite.TABLE_PARQUEO, null, nuevoParkeo);

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

    //@Override
    public boolean eliminar(String Matricula, Context context) {
        boolean resultado = false;
        try {
            con = new ConexionSQLiteHelper(context).getWritableDatabase();
            int id = con.delete(
                    BaseSQLite.TABLE_PARQUEO,
                    BaseSQLite.COL_MATRICULA + " = ?",
                    new String[]{Matricula});
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
