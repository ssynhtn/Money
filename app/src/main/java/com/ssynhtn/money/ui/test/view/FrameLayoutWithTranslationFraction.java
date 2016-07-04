package com.ssynhtn.money.ui.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Garment on 2016/7/4.
 */
public class FrameLayoutWithTranslationFraction extends FrameLayout {
    public FrameLayoutWithTranslationFraction(Context context) {
        super(context);
    }

    public FrameLayoutWithTranslationFraction(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FrameLayoutWithTranslationFraction(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public float getTranslationXFraction() {
        return getTranslationX() / getWidth();
    }

    public void setTranslationXFraction(float translationXFraction) {
        setTranslationX(translationXFraction * getWidth());
    }

    public float getTranslationYFraction() {
        return getTranslationY() / getHeight();
    }

    public void setTranslationYFraction(float translationYFraction) {
        setTranslationY(translationYFraction * getHeight());
    }
}
