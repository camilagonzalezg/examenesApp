package cl.inacap.examenesApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class PacienteViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_view);

        //Acceder a referencia de toolbar para que funcione
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

    }
}