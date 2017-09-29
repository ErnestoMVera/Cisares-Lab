package mrosarioinc.proyectofinal;


import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by Ernesto_2 on 28/05/2017.
 */

public class CustomAppClass extends Application {

    public DatabaseHandler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new DatabaseHandler(getApplicationContext(),"UltimateData",null, 1);
        // Required initialization logic here!
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    public void setHandler(DatabaseHandler handler) {
        this.handler = handler;
    }

}
