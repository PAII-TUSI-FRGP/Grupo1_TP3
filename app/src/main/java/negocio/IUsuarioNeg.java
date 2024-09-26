package negocio;

import android.content.Context;

import java.util.List;

import com.example.grupo1_tp3.entidad.Usuario;

public interface IUsuarioNeg {
    public List<Usuario> obtenerTodos(Context context);
    public Usuario obtenerUno(String nombre, Context context);
    public boolean insertar(Usuario usuario, Context context);
    public boolean actualizar(Usuario usuario, Context context);
    public boolean eliminar(String nombre, Context context);
}
