package mrosarioinc.proyectofinal;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class CrearUsuario extends AppCompatActivity implements View.OnClickListener {

    Button registrar;
    EditText nombre, pass, repeatPass;
    CustomAppClass state;
    DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        registrar = (Button) findViewById(R.id.btnRegistrar);
        nombre  = (EditText) findViewById(R.id.RegistrarNombre);

        pass = (EditText) findViewById(R.id.Pass);
        repeatPass = (EditText) findViewById(R.id.repetirPass);

        state = ((CustomAppClass) getApplicationContext());

        handler = state.handler;

        registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String newUser = nombre.getText().toString();
        String newPass = pass.getText().toString();
        String rPass = repeatPass.getText().toString();

        if(rPass.equals(newPass) && !(handler.isAnExistingUser(newUser)) && !(newPass.equals(""))) {
            handler.registrarUsuario(newUser, newPass);
            state.setHandler(handler);

            Intent exito = new Intent(this, Menu.class);
            Toast.makeText(this, "Usuario registrado exitosamente", LENGTH_SHORT).show();
            startActivity(exito);
        }
        else if((newPass.equals("") && rPass.equals(""))) {
            Snackbar.make(v, "El usuario debe tener contraseña", Snackbar.LENGTH_LONG).show();
        }
        else {
            Snackbar.make(v, "Las contraseñas no coinciden o el usuario ya existe", Snackbar.LENGTH_LONG).show();
        }
    }
}
