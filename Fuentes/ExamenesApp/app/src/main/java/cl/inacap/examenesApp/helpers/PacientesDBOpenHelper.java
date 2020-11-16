package cl.inacap.examenesApp.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PacientesDBOpenHelper extends SQLiteOpenHelper {

    //Atributo de creacion en SQL
    private final String sqlCreate = "CREATE TABLE pacientes("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + "rut TEXT,"
            + "nombre TEXT,"
            + "apellido TEXT,"
            + "fecha TEXT,"
            + "area TEXT,"
            + "covid BIT,"
            + "temperatura INTEGER,"
            + "tos BIT,"
            + "presion INTEGER)";

    public PacientesDBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Crear solo cuando base de datos no exista:
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(this.sqlCreate);
        sqLiteDatabase.execSQL(
                "INSERT INTO pacientes(rut,nombre,apellido,fecha," +
                        "area,covid,temperatura,tos,presion)" +
                        "VALUES ('16484235-5'" +
                        ", 'Camila'" +
                        ", 'Gonzalez'" +
                        ", '10/01/2020'" +
                        ", 'Atencion a publico'" +
                        ",1" +
                        ",36" +
                        ",1" +
                        ",90)");
    }

    //Este cod se ejecuta solo cuando hay un cambio en la version de la bd
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pacientes");
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
