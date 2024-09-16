package datos;

import android.content.Context;

import java.util.List;

import entidad.Usuario;

public interface IUsuarioDao {
    public List<Usuario> obtenerTodos(Context context);
    public Usuario obtenerUno(String nombre, Context context);
    public boolean insertar(Usuario usuario, Context context);
    public boolean actualizar(Usuario usuario, Context context);
    public boolean eliminar(String email, Context context);
}
