package com.example.lamps;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Random;

public class GameField extends View {
    //радиус лампочек и расстояния между ними
    int r, ro;
    //массив состояний кругов-ламп
    boolean isLight[][];
    //инструмент рисования
    Paint paint;
    //признак создания массива
    boolean isEmpty = true;
    public GameField(Context context) {
        super(context);
        r = 70;
        ro = r;
        paint = new Paint();
        paint.setColor(Color.rgb(154, 205, 50));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isEmpty){
            int n = canvas.getWidth()/(2 * r + ro);
            isLight = new boolean[n][n];
            Random random = new Random();
            for (int i = 0; i < isLight.length; i++) {
                for (int j = 0; j < isLight[i].length; j++) {
                    isLight[i][j] = random.nextBoolean();
                }
            }
            isEmpty = !isEmpty;
            //if(isEmpty == true) isEmpty = false;
        }
        float x = r + ro;
        float y = r + ro;
        for (int i = 0; i < isLight.length; i++) {
            for (int j = 0; j < isLight[i].length; j++) {
                if(isLight[i][j])
                    paint.setStyle(Paint.Style.FILL_AND_STROKE);
                else
                    paint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(x, y, r, paint);
                x += 2 * r + ro;
            }
            y += 2 * r + ro;
            x = r + ro;
        }
    }
}
