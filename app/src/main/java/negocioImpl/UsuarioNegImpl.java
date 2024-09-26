package negocioImpl;

import android.content.Context;

import java.util.List;

import datosImpl.UsuarioDaoImpl;
import com.example.grupo1_tp3.entidad.Usuario;
import negocio.IUsuarioNeg;

public class UsuarioNegImpl implements IUsuarioNeg {
    private UsuarioDaoImpl usuDaoImpl = new UsuarioDaoImpl();

    @Override
    public List<Usuario> obtenerTodos(Context context) {
        return usuDaoImpl.obtenerTodos(context);
    }

    @Override
    public Usuario obtenerUno(String nombre, Context context) {
        return usuDaoImpl.obtenerUno(nombre, context);
    }

    @Override
    public boolean insertar(Usuario usuario, Context context) {
        return usuDaoImpl.insertar(usuario, context);
    }

    @Override
    public boolean actualizar(Usuario usuario, Context context) {
        return usuDaoImpl.actualizar(usuario, context);
    }

    @Override
    public boolean eliminar(String nombre, Context context) {
        return usuDaoImpl.eliminar(nombre, context);
    }
}
