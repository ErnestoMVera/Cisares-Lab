package mrosarioinc.proyectofinal;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button Acceder;
    EditText usuario;
    EditText contrasena;
    CustomAppClass state;

    public DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Acceder = (Button) findViewById(R.id.Acceder);
        usuario = (EditText) findViewById(R.id.editTextUserNameToLogin);
        contrasena = (EditText) findViewById(R.id.editTextPasswordToLogin);

        state = ((CustomAppClass) getApplicationContext());

        handler = state.handler;

        if(handler.isSesionInciada()) {
            Intent menu = new Intent(this, Menu.class);
            startActivity(menu);
        }
        Acceder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Acceder:
                String user = usuario.getText().toString();
                String pass = contrasena.getText().toString();

                String Realpass =  handler.getCertianPass(user);

                if(pass.equals(Realpass) && handler.isAnExistingUser(user)) {
                    handler.SetSesionIniciada(user,true);
                    Intent menu = new Intent(this, Menu.class);
                    startActivity(menu);
                    state.setHandler(handler);
                }
                else {
                    Snackbar.make(v, "Contraseña incorrecta o el usuario no existe", Snackbar.LENGTH_LONG).show();
                    state.setHandler(handler);
                }
                break;
        }
    }


    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
