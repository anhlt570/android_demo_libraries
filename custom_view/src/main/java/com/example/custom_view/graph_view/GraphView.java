package com.example.custom_view.graph_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.custom_view.R;

public class GraphView extends View {
    public static final String TAG = "GraphView";

    public int mScale;
    public int touchX, touchY;
    public Paint mPaint;

    public GraphView(Context context, AttributeSet attributeSet) {
        super(context);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.GraphView, 0, 0);
        mScale = typedArray.getInteger(R.styleable.GraphView_scale, 100);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        touchX = touchY = -1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: " + getX());
        Log.d(TAG, "onDraw: " + getY());
        Log.d(TAG, "onDraw: " + getPivotX());
        Log.d(TAG, "onDraw: " + getPivotY());
        Log.d(TAG, "onDraw: " + getWidth());
        Log.d(TAG, "onDraw: " + getHeight());
        drawGrid(canvas);
        drawAxises(canvas);
        drawGraph(canvas);
        drawAlignment(canvas);
    }

    public void setScale(int scale) {
        this.mScale = scale;
    }

    private void drawAxises(Canvas canvas) {
        mPaint.setColor(Color.parseColor("#ff0000"));
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(false);
        Path path = new Path();
        path.setLastPoint(0, getHeight() / 2);
        path.lineTo(getWidth(), getHeight() / 2);
        path.moveTo(getWidth() / 2, 0.0f);
        path.lineTo(getWidth() / 2, getHeight());
        canvas.drawPath(path, mPaint);
    }

    private void drawGrid(Canvas canvas) {
        mPaint.setColor(Color.parseColor("#ededed"));
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(false);
        Path path = new Path();
        path.setLastPoint(0, 0);
        for (int x = getWidth() / 2 - mScale; x > 0; x -= mScale) {
            path.lineTo(x, 0);
            path.lineTo(x, getHeight());
            path.lineTo(getWidth() - x, getHeight());
            path.lineTo(getWidth() - x, 0);
        }
        path.moveTo(0, 0);
        for (int y = getHeight() / 2 - mScale; y > 0; y -= mScale) {
            path.lineTo(0, y);
            path.lineTo(getWidth(), y);
            path.lineTo(getWidth(), getHeight() - y);
            path.lineTo(0, getHeight() - y);
        }
        canvas.drawPath(path, mPaint);
    }

    public void drawGraph(Canvas canvas) {
        Path graphPath = new Path();
        mPaint.setStrokeWidth(2.5f);
        mPaint.setColor(Color.parseColor("#00ff00"));
        mPaint.setAntiAlias(true);

        graphPath.moveTo(0.0f, 0.0f);
        float lastY = -1;
        for (float i = 0; i <= getWidth(); ++i) {
            float x = (i - getWidth() / 2) / mScale;
            if (Math.cos(x) == 0) continue;
            float y = (float) Math.tan(x) * mScale + getHeight() / 2;
//            Log.d(TAG, "onDraw point: " + i + " - " + y);
            if (lastY * y < 0) graphPath.moveTo(i, 0);
            lastY = y;
            if (y < 0) y = 0;
            if (y > getHeight()) y = getHeight();
            graphPath.lineTo(i, y);
        }
        canvas.drawPath(graphPath, mPaint);
    }

    public void drawAlignment(Canvas canvas) {
        if (touchX < 0 || touchY < 0) return;
        mPaint.setColor(Color.parseColor("#00ffff"));
        mPaint.setStrokeWidth(2);

        Path path = new Path();
        path.moveTo(0, touchY);
        path.lineTo(getWidth(), touchY);
        path.moveTo(touchX, 0);
        path.lineTo(touchX, getHeight());
        canvas.drawPath(path, mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float tX = event.getX();
                float tY= event.getY();
                if(Math.abs(tX - touchX)> 1 || Math.abs(tY - touchY )>1){
                    touchY = (int)tY;
                    touchX = (int)tX;
                    if(Math.abs(touchX - getWidth()/2)<4) touchX = getWidth()/2;
                    if(Math.abs(touchY - getHeight()/2)<4) touchY = getHeight()/2;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                touchX = touchY = -1;
                invalidate();
                break;
        }
        return true;
    }


}
