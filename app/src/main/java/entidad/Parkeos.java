package entidad;


public class Parkeos {
    private Usuario Nombre_Par;
    private String Matricula;
    private String Tiempo;
    // Constructor, getters y setters
    public Parkeos() {
    }

    public Parkeos(Usuario nombre, String matricula, String tiempo) {
        this.Nombre_Par = nombre;
        this.Matricula = matricula;
        this.Tiempo = tiempo;
    }

    public Usuario getNombre_Par() {
        return Nombre_Par;
    }

    public void setNombre_Par(Usuario nombre) {
        this.Nombre_Par = nombre;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String matricula) {
        this.Matricula = matricula;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public void setTiempo(String tiempo) {
        this.Tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Parkeos{" +
                "Nombre_Par=" + Nombre_Par +
                ", Matricula='" + Matricula + '\'' +
                ", Tiempo='" + Tiempo + '\'' +
                '}';
    }
}
