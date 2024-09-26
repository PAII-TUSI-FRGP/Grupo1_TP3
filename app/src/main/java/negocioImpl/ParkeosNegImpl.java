package negocioImpl;

import android.content.Context;

import java.util.List;

import datosImpl.ParkeosDaoImpl;
import com.example.grupo1_tp3.entidad.Parkeo;
import negocio.IParkeosNeg;


public class ParkeosNegImpl implements IParkeosNeg {
    private ParkeosDaoImpl parDaoImpl = new ParkeosDaoImpl();

    @Override
    public List<Parkeo> obtenerTodos(Context context) {
        return parDaoImpl.obtenerTodos(context);
    }
    @Override
    public boolean insertar(Parkeo parkeos, Context context) {
        return parDaoImpl.insertar(parkeos, context);
    }
    @Override
    public boolean eliminar(String matricula, Context context) {
        return parDaoImpl.eliminar(matricula, context);
    }

}
