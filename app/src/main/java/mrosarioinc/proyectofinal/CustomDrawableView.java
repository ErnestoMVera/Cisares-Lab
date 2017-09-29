package mrosarioinc.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ernesto_2 on 30/03/2017.
 */

public class CustomDrawableView extends View implements View.OnTouchListener {

    Drawable Background;
    int color;
    Context context;
    private ArrayList<Circle> botones;
    private DatabaseHandler handler;

    String[] nomenclatura = {"C-301", "C-302", "C-201", "C-202","C-101", "C-102", "B-301", "B-302", "B-303", "B-304", "B-201", "B-202", "C-203", "C-204",
            "B-103", "B-104", "A-302", "A-303"};
    //               0    1    2   3    4   5   6     7    8   9   10   11     12   13  14   15   16   17
    int[] coordsX = {50, 130, 50, 130, 50, 130, 240, 310, 370, 430, 240, 310, 370, 430, 370, 430, 410, 410};
    //                0   1   2    3    4    5   6    7   8   9   10  11    12   13   14   15   16    17
    int[] coordsY = {80, 80, 120, 120, 160, 160, 65, 65, 65, 65, 100, 100, 100, 100, 140, 140, 270, 315};

    public CustomDrawableView(Context context) {
        super(context);
        this.context = context;
        this.setOnTouchListener(this);
        Background = context.getResources().getDrawable(R.drawable.mapa);
        color = Color.BLUE;
        handler = new DatabaseHandler(this.context,"DatosProyecto",null, 1);

        botones = new ArrayList<Circle>();
        boolean isTableEmpty = !(handler.CountTableData("circleData") > 0);
        for(int i = 0; i < 18; i++) {
            Circle c = new Circle(coordsX[i],coordsY[i],10,nomenclatura[i]);
            botones.add(c);
            if(isTableEmpty) {
                handler.addInfo(i,0);
            }
        }

        loadCircleColors();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it
        Background.setBounds(imageBounds);
        Background.draw(canvas);

        for(Circle c : botones) {
            c.Show(canvas);
        }

    }

    void loadCircleColors() {
        for(Circle c : botones) {
            c.setColor(Color.RED);
            c.active = false;
        }
        int[] activeOnes = handler.getActiveOnes();

        for(int i = 0; i < activeOnes.length; i++) {
            botones.get(activeOnes[i]).setColor(Color.GREEN);
            botones.get(activeOnes[i]).active = true;
        }
        invalidate();
    }

    public void SetCircleColor(boolean colorExp, int index) {
        if(colorExp) {
            handler.UpdateColor(index, 1);
        }
        else {
            handler.UpdateColor(index, 0);
        }

        loadCircleColors();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();

        for(int i = 0; i < botones.size(); i++) {
            if (botones.get(i).DetectOnTouch(touchX, touchY)) {
                Intent g = new Intent(context, SisarSalon.class);
                g.putExtra("Salon",botones.get(i).idSalon);
                g.putExtra("Current", i);
                g.putExtra("isOpen", botones.get(i).active);
                context.startActivity(g);

            }

            invalidate();
        }



        return true;
    }
}
