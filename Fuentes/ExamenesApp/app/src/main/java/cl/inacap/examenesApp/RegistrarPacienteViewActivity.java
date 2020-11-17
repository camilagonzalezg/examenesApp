package cl.inacap.examenesApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import cl.inacap.examenesApp.dao.PacientesDAO;
import cl.inacap.examenesApp.dao.PacientesDAOSQLite;
import cl.inacap.examenesApp.dto.Paciente;

public class RegistrarPacienteViewActivity extends AppCompatActivity {

    private PacientesDAO pacientDAO = new PacientesDAOSQLite(this);
    private EditText rutTxt, nombreTxt, apellidoTxt, temperaturaTxt, presionTxt, fechaTxt;
    private Spinner areaTxt;
    private Switch covidTxt, tosTxt;
    private Toolbar toolbar;
    private Button agregarBtn;
    private Calendar myCalendar = Calendar.getInstance();
    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_paciente_view);

        this.toolbar = findViewById(R.id.toolbar);
        this.rutTxt = findViewById(R.id.rut_txt);
        this.nombreTxt = findViewById(R.id.nombre_txt);
        this.apellidoTxt = findViewById(R.id.apellido_txt);
        this.fechaTxt = findViewById(R.id.fecha_txt);
        this.areaTxt = findViewById(R.id.area_spinner);
        String[] areas = {"Atencion a público", "Otro"};
        areaTxt.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, areas));
        this.covidTxt = findViewById(R.id.covid_switch_si);
        this.temperaturaTxt = findViewById(R.id.temperatura_txt);
        this.tosTxt = findViewById(R.id.tos_switch_si);
        this.presionTxt = findViewById(R.id.presion_txt);
        this.agregarBtn = findViewById(R.id.registrar_btn);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        fechaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegistrarPacienteViewActivity.this, date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Crear el paciente

                Paciente p = new Paciente();

                p.setRut(rutTxt.getText().toString());
                p.setNombre(nombreTxt.getText().toString());
                p.setApellido(apellidoTxt.getText().toString());
                p.setFecha(fechaTxt.getText().toString());
                p.setArea(areaTxt.getSelectedItem().toString());
                if (covidTxt.isChecked()){
                    p.setCovid(true);
                }else{
                    p.setCovid(false);
                }
                p.setTemperatura(Integer.parseInt(temperaturaTxt.getText().toString()));
                if (tosTxt.isChecked()){
                    p.setTos(true);
                }else{
                    p.setTos(false);
                }
                p.setPresion(Integer.parseInt(presionTxt.getText().toString()));
                //Llamar al dao
                pacientDAO.save(p);
                //Enviar al activity de lista
                startActivity(new Intent(RegistrarPacienteViewActivity.this
                        , ListaPacienteActivity.class));
            }
        });


    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //if (myCalendar.getTimeInMillis() > Calendar.getInstance().getTimeInMillis()){
            //if (myCalendar.before(Calendar.getInstance().getTime())){
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                actualizarInput();

            //}else{
            //    toast = Toast.makeText(getApplicationContext(), "No Puede ser una fecha anterior a hoy", Toast.LENGTH_SHORT);
            //    toast.show();
            //}

        }
    };
    private void actualizarInput() {
        String formatoDeFecha = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);
        fechaTxt.setText(sdf.format(myCalendar.getTime()));
    }
}