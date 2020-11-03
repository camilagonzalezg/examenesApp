package cl.inacap.examenesApp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cl.inacap.examenesApp.R;
import cl.inacap.examenesApp.dto.Paciente;

public class PacientesListAdapter extends ArrayAdapter<Paciente> {

    //Definir lista de productos
    private List <Paciente> pacientes;

    //Definir el activity como atributo
    private Activity activity;

    public PacientesListAdapter(@NonNull Activity context, int resource, @NonNull List<Paciente> objects) {
        super(context, resource, objects);
        this.pacientes = objects;
        this.activity = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Generar un layout, construyendo la instancia
        LayoutInflater inflater = this.activity.getLayoutInflater();

        //Generar una fila del layout, fila que será obtenida del inflater
        View fila = inflater.inflate(R.layout.pacientes_list, null, true);

        //Aquí carga contenido layout
        // obteniendo referencia del texview
        TextView nombreTv = fila.findViewById(R.id.nombre_pacient_pl);
        //Acceder al paciente actual, va paciente por paciente
        Paciente actual = pacientes.get(position);
        //Definir el texto del view
        nombreTv.setText(actual.getNombre());
        //Finalmente, se retorna la fila
        return fila;
    }
}
