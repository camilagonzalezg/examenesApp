package cl.inacap.examenesApp.dto;

import android.app.DatePickerDialog;

import java.io.Serializable;

//Para transferir objetos por red, implementamos Serializable
//haciendo que los objetos de esta clase sean transportados

public class Paciente implements Serializable {

    private int id;
    private String rut;
    private String nombre;
    private String apellido;
    private DatePickerDialog fecha;
    private String area;
    private boolean covid;
    private int temperatura;
    private boolean tos;
    private int presion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public DatePickerDialog getFecha() {
        return fecha;
    }

    public void setFecha(DatePickerDialog fecha) {
        this.fecha = fecha;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isCovid() {
        return covid;
    }

    public void setCovid(boolean covid) {
        this.covid = covid;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public boolean isTos() {
        return tos;
    }

    public void setTos(boolean tos) {
        this.tos = tos;
    }

    public int getPresion() {
        return presion;
    }

    public void setPresion(int presion) {
        this.presion = presion;
    }
}
