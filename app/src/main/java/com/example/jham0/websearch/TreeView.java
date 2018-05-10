package com.example.jham0.websearch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import java.lang.reflect.Constructor;

public class TreeView extends AppCompatActivity {
    HorizontalScrollView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HorizontalScrollView sc = new HorizontalScrollView(this);

        CanvasView cv = new CanvasView(this);


        sc.addView(cv);
        setContentView(sc);


    }


    private class CanvasView extends View {

        public CanvasView(Context ctx){
            super(ctx);
        }

        public void onDraw(Canvas canvas){
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.BLUE);
            canvas.drawRect(10, 70, getWidth() +20, getHeight(), paint);
        }

        //TODO: HEY PERRO ACABÁ EL PROYECTO

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            // Compute the height required to render the view
            // Assume Width will always be MATCH_PARENT.
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = 3000 + 50; // Since 3000 is bottom of last Rect to be drawn added and 50 for padding.
            setMeasuredDimension(width, height);
        }
    }
}
