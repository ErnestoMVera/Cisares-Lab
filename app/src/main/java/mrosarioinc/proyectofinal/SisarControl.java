package mrosarioinc.proyectofinal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SisarControl extends AppCompatActivity {

    CustomDrawableView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customView = new CustomDrawableView(this);
        Intent temp = getIntent();
        Bundle extras = temp.getExtras();

        if(extras != null) {
            boolean toColor = extras.getBoolean("color");
            int index = extras.getInt("index");

            customView.SetCircleColor(toColor,index);

        }

        setContentView(customView);
    }

    // You cant cant comeback to any activity from here but but the menu
    @Override
    public void onBackPressed() {
        Intent menuBack = new Intent(this, Menu.class);
        startActivity(menuBack);
    }
}
