package cl.inacap.examenesApp.dao;

import java.util.List;

import cl.inacap.examenesApp.dto.Paciente;

public interface PacientesDAO {

    // metodo agregar
    Paciente save(Paciente p);

    //metodo listar
    List<Paciente> getAll();
}
