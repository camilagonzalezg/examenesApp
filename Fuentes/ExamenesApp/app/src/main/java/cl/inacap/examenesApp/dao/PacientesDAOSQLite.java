package cl.inacap.examenesApp.dao;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.inacap.examenesApp.dto.Paciente;
import cl.inacap.examenesApp.helpers.PacientesDBOpenHelper;

public class PacientesDAOSQLite implements PacientesDAO {

    private PacientesDBOpenHelper db;

    public PacientesDAOSQLite(Context contexto){
        this.db = new PacientesDBOpenHelper(contexto
                ,"DBPacientes"
                , null
                , 1);
    };

    @Override
    public Paciente save(Paciente p) {
        int bitcovid, bittos;
        if (p.getCovid()){
            bitcovid  = 1;
        }else{
            bitcovid  = 0;
        }
        if (p.getTos()){
            bittos = 1;
        }else{
            bittos = 0;
        }
        SQLiteDatabase writer = this.db.getWritableDatabase();
        String sql = String.format("INSERT INTO pacientes(" +
                "rut,nombre,apellido,fecha,area,covid,temperatura,tos,presion) " +
                        "VALUES('%s','%s','%s',%s,'%s',%s,%d,%s,%d)"
        , p.getRut()
        , p.getNombre()
        , p.getApellido()
        , p.getFecha()
        , p.getArea()
        , bitcovid
        , p.getTemperatura()
        , bittos
        , p.getPresion());
        writer.execSQL(sql);
        writer.close();
        return p;
    }

    @Override
    public List<Paciente> getAll() {
        SQLiteDatabase reader = this.db.getReadableDatabase();

        //Se crea lista
        List<Paciente> pacientes = new ArrayList<>();

        try{
            if(reader != null){
                Cursor c = reader.rawQuery("SELECT id,rut,nombre,apellido" +
                        ",fecha,area,covid,temperatura,tos,presion FROM pacientes",null);
                if(c.moveToFirst()){
                    do{
                        Paciente p = new Paciente();
                        p.setId(c.getInt(0));
                        p.setRut(c.getString(1));
                        p.setNombre(c.getString(2));
                        p.setApellido(c.getString(3));
                        p.setFecha(c.getString(4));
                        p.setArea(c.getString(5));
                        if (c.getInt(6) == 1){
                            p.setCovid(true);
                        }else{
                            p.setCovid(false);
                        }
                        p.setTemperatura(c.getInt(7));
                        if (c.getInt(8) == 1){
                            p.setTos(true);
                        }else{
                            p.setTos(false);
                        }
                        p.setPresion(c.getInt(9));
                        pacientes.add(p);
                    }while(c.moveToNext());{

                    }
                    reader.close();
                }
            }
        } catch (Exception ex){
            pacientes = null;
        }
        return pacientes;
    }
}
