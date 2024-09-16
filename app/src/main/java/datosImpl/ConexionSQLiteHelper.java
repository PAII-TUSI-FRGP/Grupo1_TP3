package datosImpl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import utilidades.BaseSQLite;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    public ConexionSQLiteHelper(@Nullable Context context) {
        super(context, BaseSQLite.DB_NAME, null, BaseSQLite.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BaseSQLite.SQL_CREATE_TABLE_USUARIO);
        db.execSQL(BaseSQLite.SQL_CREATE_TABLE_PARQUEO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BaseSQLite.SQL_DROP_TABLE_USUARIO);
        db.execSQL(BaseSQLite.SQL_DROP_TABLE_PARQUEO);
        onCreate(db);
    }
}
