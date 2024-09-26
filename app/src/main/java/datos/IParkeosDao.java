package datos;

import android.content.Context;

import java.util.List;

import com.example.grupo1_tp3.entidad.Parkeo;


public interface IParkeosDao {
    public List<Parkeo> obtenerTodos(Context context);
    public boolean insertar(Parkeo parkeos, Context context);
}
