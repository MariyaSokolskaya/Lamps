package com.example.lamps;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
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
    //координаты точки касания
    float touchX, touchY;

    public GameField(Context context) {
        super(context);
        r = 70;
        ro = r;
        paint = new Paint();
        paint.setStrokeWidth(3);
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
//                canvas.drawText("Win", 50, 400, paint);
                x += 2 * r + ro;
            }
            y += 2 * r + ro;
            x = r + ro;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            touchX = event.getX();
            touchY = event.getY();
            calculate();
        }
        return true;
    }

    private void calculate(){
        //координаты центра ближайшей окружности
        float x, y;

        //номера строки и столбца
        int i, j;
        i = (int)touchY/(2 * r + ro);
        j = (int)touchX/(2 * r + ro);
        if(i < isLight.length) {
            x = (2 * r + ro) * j + r + ro;
            y = (2 * r + ro) * i + r + ro;

            if(Math.pow(x - touchX, 2) + Math.pow(y - touchY, 2) <= r * r){
                isLight[i][j] = !isLight[i][j];
//                isLight[i-1][j] = !isLight[i-1][j];
//                isLight[i+1][j] = !isLight[i+1][j];
//                isLight[i][j-1] = !isLight[i][j-1];
//                isLight[i][j+1] = !isLight[i][j+1];

                invalidate();
            }
        }

    }
}
