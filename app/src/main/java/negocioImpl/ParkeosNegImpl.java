package negocioImpl;

import android.content.Context;

import java.util.List;

import datosImpl.ParkeosDaoImpl;
import entidad.Parkeos;
import entidad.Usuario;
import negocio.IParkeosNeg;


public class ParkeosNegImpl implements IParkeosNeg {
    private ParkeosDaoImpl parDaoImpl = new ParkeosDaoImpl();

    @Override
    public List<Parkeos> obtenerTodos(Context context) {
        return parDaoImpl.obtenerTodos(context);
    }
    @Override
    public boolean insertar(Parkeos parkeos, Context context) {
        return parDaoImpl.insertar(parkeos, context);
    }
    @Override
    public boolean eliminar(String matricula, Context context) {
        return parDaoImpl.eliminar(matricula, context);
    }

}
