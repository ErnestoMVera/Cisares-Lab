package mrosarioinc.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class SisarSalon extends AppCompatActivity implements View.OnClickListener {

    TextView salon;
    Switch estado;
    Button Activar;
    int index;
    boolean isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sisar_salon);

        salon = (TextView) findViewById(R.id.idSalon);
        Intent temp = getIntent();
        Bundle extras = temp.getExtras();

        String id = extras.getString("Salon");
        salon.setText(id);

        estado = (Switch) findViewById(R.id.Estado);

        Activar = (Button) findViewById(R.id.SetActivo);

        index = extras.getInt("Current");
        isOpen = extras.getBoolean("isOpen");

        estado.setChecked(isOpen);

        Activar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent back;
        if(estado.isChecked()) {
            back = new Intent(this, SisarControl.class);
            back.putExtra("color", true);
            back.putExtra("index", index);
            startActivity(back);

        }
        else {
            back = new Intent(this, SisarControl.class);
            back.putExtra("color", false);
            back.putExtra("index", index);
            startActivity(back);
        }
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
