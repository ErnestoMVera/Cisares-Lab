package mrosarioinc.proyectofinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ernesto_2 on 27/05/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String CircleData = "circleData";
    private static final String CircleIndex = "Id";
    private static final String ColorValue = "colorValue";

    private static final String LoginTB = "Login";
    private static final String idUser = "IDUser";
    private static final String nombre = "Nombre";
    private static final String pass = "Contrasena";
    private static final String isIn = "isInciada";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTable = "CREATE TABLE " + CircleData + "("
                + CircleIndex + " INTEGER PRIMARY KEY, " + ColorValue + " INTEGER" + ")";
        db.execSQL(CreateTable);

        String login = "CREATE TABLE " + LoginTB + "(" + idUser + " INTEGER PRIMARY KEY AUTOINCREMENT, " + nombre + " TEXT NOT NULL, "
                + pass + " TEXT NOT NULL, " + isIn + " INTEGER)" ;
        db.execSQL(login);
        String DefaultUser = "INSERT INTO " + LoginTB + "(" + idUser + "," + nombre + "," + pass + "," + isIn + ") VALUES('1','Admin','1234','0')";
        db.execSQL(DefaultUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CircleData);
        db.execSQL("DROP TABLE IF EXISTS " + LoginTB);
        onCreate(db);
    }

    void addInfo(int index, int color) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO " + CircleData + "(" + CircleIndex + "," + ColorValue + ") VALUES('" + index + "','" + color  + "')";
        db.execSQL(query);
    }


    void UpdateColor(int index, int colorBool) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "UPDATE " + CircleData + " SET " + ColorValue + " = " + colorBool + " WHERE " + CircleIndex + " = " + index;
        db.execSQL(Query);
    }

    public int CountTableData(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String count = "SELECT count(*) FROM " + tableName;

        Cursor c = db.rawQuery(count, null);

        c.moveToFirst();
        int Count = c.getInt(0);

        return Count;
    }

    int[] getActiveOnes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT " + CircleIndex +  " FROM " + CircleData + " WHERE " + ColorValue + " = 1";

        Cursor c = db.rawQuery(Query, null);
        int[] activeOnes = new int[c.getCount()];

        int i = 0;
        if (c.moveToFirst()) {
            do {
                int a = c.getInt(0);
                activeOnes[i] = a;
                i++;
            }
            while (c.moveToNext());
        }

        return activeOnes;
    }


    String getCertianPass(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT " + pass +  " FROM " + LoginTB + " WHERE " + nombre + " = '" + user + "'";
        Cursor c = db.rawQuery(Query, null);
        String pass = "";

        if (c.moveToFirst()) {
            do {
                pass = c.getString(0);
            }
            while(c.moveToNext());
        }

        return pass;
    }

    boolean isAnExistingUser(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT " + nombre +  " FROM " + LoginTB;
        Cursor c = db.rawQuery(Query, null);

        String Temp = "";

        if (c.moveToFirst()) do {
            Temp = c.getString(0);
            if (Temp.equals(user)) {
                return true;
            }
        }
        while (c.moveToNext());
        return false;
    }


    boolean isSesionInciada() {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT " + isIn +  " FROM " + LoginTB;

        Cursor c = db.rawQuery(Query, null);

        int Temp = -1;

        if (c.moveToFirst()) do {
            Temp = c.getInt(0);
            if (Temp == 1) {
                return true;
            }
        }
        while (c.moveToNext());

        return false;
    }

    void SetSesionIniciada(String user, boolean isInciada) {
        int temp;
        if(isInciada) {
            temp = 1;
        }
        else {
            temp = 0;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "UPDATE " + LoginTB + " SET " + isIn + " = " + temp + " WHERE "  + nombre + " = '" + user +"'";
        db.execSQL(Query);
    }

    public String getLastUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT " + nombre + " FROM " + LoginTB + " WHERE " + isIn + " = 1";

        String lastUser = "";
        Cursor c = db.rawQuery(Query, null);
        if (c.moveToFirst()) {
            do {
                lastUser = c.getString(0);
            }
            while (c.moveToNext());
        }

        return lastUser;
    }


    void registrarUsuario(String newUser, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO " + LoginTB + "(" + nombre + "," + this.pass + ") VALUES('" + newUser + "','" + pass  + "')";
        db.execSQL(query);
    }

}
