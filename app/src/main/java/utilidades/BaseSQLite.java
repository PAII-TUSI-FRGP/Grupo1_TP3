package utilidades;

public class BaseSQLite {
    //Constantes para crear la base de datos
    public final static String DB_NAME = "BD_TP3";
    public static int DB_VERSION = 1;

    //Constantes para crear la tabla de usuario
    public final static String TABLE_USUARIO = "Usuario";
    public final static String COL_NOMBRE = "Nombre";
    public final static String COL_EMAIL = "Email";
    public final static String COL_PASSWORD = "Password";
    public final static String SQL_DROP_TABLE_USUARIO = "DROP TABLE IF EXISTS " + TABLE_USUARIO + ";";
    public final static String SQL_CREATE_TABLE_USUARIO = "CREATE TABLE IF NOT EXISTS " + TABLE_USUARIO + " (" +
            COL_NOMBRE + " TEXT NOT NULL," +
            COL_EMAIL + " TEXT NOT NULL," +
            COL_PASSWORD + " TEXT NOT NULL," +
            "CONSTRAINT PK_Usuario PRIMARY KEY (" + COL_NOMBRE + ")" +
            ");";

    //Constantes para crear la tabla de parqueo
    public final static String TABLE_PARQUEO = "Parqueo";
    public final static String COL_MATRICULA = "Matricula";
    public final static String COL_TIEMPO = "Tiempo";
    public final static String COL_NOMBRE_PAR = "Nombre_Par";
    public final static String SQL_DROP_TABLE_PARQUEO = "DROP TABLE IF EXISTS " + TABLE_PARQUEO + ";";
    public final static String SQL_CREATE_TABLE_PARQUEO = "CREATE TABLE IF NOT EXISTS " + TABLE_PARQUEO + " (" +
            COL_MATRICULA + " INTEGER NOT NULL," +
            COL_TIEMPO + " TEXT," +
            COL_NOMBRE_PAR + " TEXT NOT NULL," +
            "CONSTRAINT PK_Parqueo PRIMARY KEY (" + COL_MATRICULA + ")," +
            "CONSTRAINT FK_Parqueo_Usuario FOREIGN KEY (" + COL_NOMBRE_PAR + ") REFERENCES " + TABLE_USUARIO + "(" + COL_NOMBRE + ")" +
            ");";
}
