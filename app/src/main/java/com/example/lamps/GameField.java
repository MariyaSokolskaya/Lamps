package com.example.lamps;


import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class GameField extends View {

    boolean lampsState[][];
    int fieldSizeN = 4;
    int fieldSizeM = 4;
    int r, ro; //радиус и расстояние между окружностями
    public GameField(Context context) {
        super(context);
        //TODO сформировать массив случайно
        //TODO рассчитать радиус и расстояние между окружностями
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //TODO отрисовать окружности согласно массиву состояний
    }
}
