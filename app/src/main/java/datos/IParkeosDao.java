package datos;

import android.content.Context;

import java.util.List;

import entidad.Parkeos;
import entidad.Usuario;


public interface IParkeosDao {
    public List<Parkeos> obtenerTodos(Context context);
    public boolean insertar(Parkeos parkeos, Context context);
}
