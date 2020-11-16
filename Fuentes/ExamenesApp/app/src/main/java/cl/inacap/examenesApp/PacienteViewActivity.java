package cl.inacap.examenesApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import cl.inacap.examenesApp.dto.Paciente;

public class PacienteViewActivity extends AppCompatActivity {

    TextView rutPacienteTv;
    TextView nombrePacienteTv;
    TextView apellidoPacienteTv;
    TextView fechaPacienteTv;
    TextView areaPacienteTv;
    TextView covidPacienteTv;
    TextView temperaturaPacienteTv;
    TextView tosPacienteTv;
    TextView presionPacienteTv;
    Paciente paciente;
    Toolbar toolbar;
    TextView tituloToolbarTv;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_view);

        //Referencia a la toolbar
        this.toolbar = findViewById(R.id.toolbar);
        this.tituloToolbarTv = findViewById(R.id.titulo_toolbar_txt);

        this.rutPacienteTv = findViewById(R.id.rut_txt_view);
        this.nombrePacienteTv = findViewById(R.id.nombre_txt_view);
        this.apellidoPacienteTv = findViewById(R.id.apellido_txt_view);
        this.fechaPacienteTv = findViewById(R.id.fecha_txt_view);
        this.areaPacienteTv = findViewById(R.id.area_txt_view);
        this.covidPacienteTv = findViewById(R.id.covid_txt_view);
        this.temperaturaPacienteTv = findViewById(R.id.temperatura_txt_view);
        this.tosPacienteTv = findViewById(R.id.tos_txt_view);
        this.presionPacienteTv = findViewById(R.id.presion_txt_view);

        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(getIntent().getExtras() != null){
            //Envio por intent
            this.paciente = (Paciente) getIntent().getSerializableExtra("paciente");
            this.tituloToolbarTv.setText(this.paciente.getNombre());
            this.rutPacienteTv.setText(this.paciente.getRut());
            this.nombrePacienteTv.setText(this.paciente.getNombre());
            this.apellidoPacienteTv.setText(this.paciente.getApellido());
            this.fechaPacienteTv.setText(this.paciente.getFecha());
            this.areaPacienteTv.setText(this.paciente.getArea());
            if (this.paciente.getCovid()){
                this.covidPacienteTv.setText("Si");
            }else{
                this.covidPacienteTv.setText("No");
            }
            this.temperaturaPacienteTv.setText(this.paciente.getTemperatura() + "");
            if (this.paciente.getTos()){
                this.tosPacienteTv.setText("Si");
            }else{
                this.tosPacienteTv.setText("No");
            }
            this.presionPacienteTv.setText(this.paciente.getPresion() + "");
    }
}
}