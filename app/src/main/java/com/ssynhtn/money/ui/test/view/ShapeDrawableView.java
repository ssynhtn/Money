package com.ssynhtn.money.ui.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Garment on 2016/7/7.
 */
public class ShapeDrawableView extends TextView {
    private ShapeDrawable mDrawable;

    public ShapeDrawableView(Context context) {
        this(context, null);
    }

    public ShapeDrawableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeDrawableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        mDrawable = new ShapeDrawable();
        mDrawable.getPaint().setStyle(Paint.Style.STROKE);
        mDrawable.getPaint().setStrokeWidth(10);
        mDrawable.getPaint().setColor(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        configureShapeDrawableBasedOnNewSize(w, h);
    }

    private void configureShapeDrawableBasedOnNewSize(int w, int h) {

        ArcShape shape = new ArcShape(-90, 90);


        int strokeOffset = (int) (mDrawable.getPaint().getStrokeWidth() / 2);
//        float radius = (Math.min(w, h) - mDrawable.getPaint().getStrokeWidth()) / 2;
//        float[] outRadii = {radius, radius, radius, radius, radius, radius, radius, radius};
//        RoundRectShape shape = new RoundRectShape(outRadii, null, null);
        mDrawable.setShape(shape);
        mDrawable.setBounds(strokeOffset, strokeOffset, w - strokeOffset, h - strokeOffset);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);

        mDrawable.getPaint().setColor(selected ? Color.GREEN: Color.RED);
        mDrawable.getPaint().setStyle(selected ? Paint.Style.FILL : Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mDrawable.draw(canvas);
    }
}
