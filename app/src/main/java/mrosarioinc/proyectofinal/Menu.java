package mrosarioinc.proyectofinal;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity implements View.OnClickListener {

    Button Sisares;
    Button Opciones;

    DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Sisares = (Button) findViewById(R.id.Sisares);
        Opciones = (Button) findViewById(R.id.Opciones);
        Sisares.setOnClickListener(this);
        Opciones.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Sisares:
                Intent menu = new Intent(this, SisarControl.class);
                startActivity(menu);
                break;
            case R.id.Opciones:
                Intent opc = new Intent(this, Opciones.class);
                startActivity(opc);
                break;
        }
    }


    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
