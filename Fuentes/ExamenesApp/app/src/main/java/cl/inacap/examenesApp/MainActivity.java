package cl.inacap.examenesApp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nombreUsuario, contrasenia;
    private Button ingresarBtn;
    private List<String> errores = new ArrayList<>();
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.nombreUsuario = findViewById(R.id.Login_nombre_usuario);
        this.contrasenia = findViewById(R.id.Login_contrasenia_usuario);
        this.ingresarBtn = findViewById(R.id.Login_btn_ingresar);
        this.ingresarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean contienegion = false;
                if (nombreUsuario.length() < 9 || nombreUsuario.length() > 10){
                    System.out.println("pase por aqui numeros");
                    errores.add("Nombre de usuario debe ser un rut con 9 o 10 caracter");
                }
                for (int i = 0; i< nombreUsuario.length(); i++){
                    if (nombreUsuario.getText().charAt(i) == '-'){
                        contienegion = true;
                        break;
                    }
                }
                if (!contienegion){
                    System.out.println("pase por aqui guion");
                    errores.add("Nombre de usuario debe contener guion ( - )");
                }
                if (nombreUsuario.length() > 0) {
                    switch (nombreUsuario.getText().charAt(nombreUsuario.length() - 1)) {
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                        case '0':
                        case 'K':
                        case 'k':
                            break;
                        default:
                            errores.add("Digito verificador debe ser 0-9 o K");
                            break;
                    }
                }else{
                    errores.add("Digito verificador debe ser 0-9 o K");
                }

                for (int i = 0; i< errores.size(); i++) {
                    toast = Toast.makeText(getApplicationContext(), errores.get(i), Toast.LENGTH_SHORT);
                    toast.show();
                }
                errores.clear();
            }
        });
    }
}