package cl.inacap.examenesApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import cl.inacap.examenesApp.adapters.PacientesListAdapter;
import cl.inacap.examenesApp.dao.PacientesDAO;
import cl.inacap.examenesApp.dao.PacientesDAOSQLite;
import cl.inacap.examenesApp.dto.Paciente;

public class ListaPacienteActivity extends AppCompatActivity {

    //Lista de pacientes
    private List<Paciente> pacientes;
    //Referencia al DAO + SQLite
    private PacientesDAO pacientDAO = new PacientesDAOSQLite(this);
    //Referencia al Listview
    private ListView pacientesLV;
    //Referencia al boton
    //private FloatingActionButton agregarBtn;
    //Referencia al adaptador
    private PacientesListAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        //Ir a buscar los productos a la lista
        this.pacientes = this.pacientDAO.getAll();
        //Referencia ListView
        this.pacientesLV = findViewById(R.id.pacientes_lv);
        //Construir un list view al adapter
        this.adapter = new PacientesListAdapter(this
                ,R.layout.pacientes_list,this.pacientes);
        //Hacer que el adaptador sea parte del listview
        this.pacientesLV.setAdapter(this.adapter);

        //Agregar listener a los elementos de la lista, clase que implementa interfaz
        this.pacientesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Hacer click y que diga qué producto es
                Paciente paciente = pacientes.get(i);
                //Provocar que, luego del click, vaya a otro activity, mediante un intent
                //Intent-Desde donde viene (contexto), hacia donde va (contexto), en este caso
                //irá a clase PListaPaciente
                Intent intent = new Intent(ListaPacienteActivity.this
                , PacienteViewActivity.class);
                //Pasar el elemento para que esté en el activity
                intent.putExtra("paciente", paciente);
                //Para que se ejecute la acción, agregándolo al stack
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_paciente);

        //Acceder a referencia de toolbar para que funcione
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        //this.agregarBtn = findViewById(R.id.agregar_btn_fb);

    }
}