package negocio;

import android.content.Context;

import java.util.List;

import entidad.Parkeos;

public interface IParkeosNeg {
    public List<Parkeos> obtenerTodos(Context context);
    public boolean insertar(Parkeos parkeos, Context context);
    public boolean eliminar(String nombre, Context context);

}
