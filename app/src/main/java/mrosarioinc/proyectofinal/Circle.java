package mrosarioinc.proyectofinal;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.Serializable;

/**
 * Created by Lab-B-HP-01 on 04/04/2017.
 */

public class Circle implements Serializable {

    String idSalon;
    public int color;
    private Paint p;
    boolean active = false;
    int x,y,r;

    public Circle(int x, int y, int r, String idSalon) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.idSalon = idSalon;
        p = new Paint();
        p.setColor(Color.RED);
    }

    public void Show(Canvas canvas) {
        canvas.drawCircle(x, y, r, p);
    }


    public void setColor(int color) {
        p.setColor(color);
    }

    public int ChangeOnTouch(int touchX, int touchY,int signal) {
        if(touchX >= (x - r) && touchX <=  (x + r) && touchY <= (y + r) && touchY >= (y - r)) {
            return signal;
        }
        return 0;
    }


    public boolean DetectOnTouch(int touchX, int touchY) {
        if(touchX >= (x - r) && touchX <=  (x + r) && touchY <= (y + r) && touchY >= (y - r)) {
            return true;
        }
        return false;
    }


}
