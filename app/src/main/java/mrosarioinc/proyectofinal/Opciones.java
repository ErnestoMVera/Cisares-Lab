package mrosarioinc.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Opciones extends AppCompatActivity {

    DatabaseHandler handler;
    CustomAppClass state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        state = ((CustomAppClass) getApplicationContext());
        handler = state.handler;
    }

    public void onTableRowClick(View view) {
        switch(view.getId()) {
            case R.id.one:
                Intent registrar = new Intent(this, CrearUsuario.class);
                startActivity(registrar);
                break;
            case R.id.two:
                String last = handler.getLastUser();
                handler.SetSesionIniciada(last,false);
                state.setHandler(handler);
                Intent iniciarSesion = new Intent(this, MainActivity.class);
                startActivity(iniciarSesion);
                break;
        }
    }

    public void onBackPressed() {
        Intent menuBack = new Intent(this, Menu.class);
        startActivity(menuBack);
    }
}
