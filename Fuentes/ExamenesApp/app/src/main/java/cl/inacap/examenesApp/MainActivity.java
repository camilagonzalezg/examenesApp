package cl.inacap.examenesApp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
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
                if (nombreUsuario.length() > 0){
                    if (nombreUsuario.length() < 9 || nombreUsuario.length() > 10){
                        errores.add("Nombre de usuario debe ser un rut con 9 o 10 caracter");
                        nombreUsuario.setBackgroundResource(R.drawable.stylo_borde_editext_rojo);
                    }
                    for (int i = 0; i< nombreUsuario.length(); i++){
                        if (nombreUsuario.getText().charAt(i) == '-'){
                            contienegion = true;
                            break;
                        }
                    }
                    if (!contienegion){
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
                    if (errores.isEmpty()){
                        nombreUsuario.setBackgroundResource(R.drawable.stylo_borde_editext_negro);
                    }
                }else{
                    errores.add("Debe ingresar nombre de usuario");
                    nombreUsuario.setBackgroundResource(R.drawable.stylo_borde_editext_rojo);
                }

                if (contrasenia.getText().toString().length() >0){
                    if (nombreUsuario.getText().toString().subSequence(5,9).equals(contrasenia.getText().toString())||nombreUsuario.getText().toString().subSequence(4,8).equals(contrasenia.getText().toString())){
                    }else{
                        errores.add("La contraseña no coincide");
                        contrasenia.setBackgroundResource(R.drawable.stylo_borde_editext_rojo);
                    }
                }else {
                    errores.add("Deve ingresar una contraseña");
                    contrasenia.setBackgroundResource(R.drawable.stylo_borde_editext_rojo);
                }
                if (errores.isEmpty()){
                    Intent intent = new Intent(MainActivity.this, ListaPacienteActivity.class);
                    startActivity(intent);
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